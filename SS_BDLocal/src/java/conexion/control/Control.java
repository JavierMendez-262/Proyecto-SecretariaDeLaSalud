/*
 * Control.java
 * 
 * Creado en Abril 18, 2020. 20:20
 */
package conexion.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conexion.rest.RecursoExpediente_Client;
import conexion.websockets.ServerEndpointAnnotated;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.NotFoundException;
import negocio.Expediente;
import persistencia.PersistenciaListas;

/**
 * Clase que maneja las conexiones.
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Control {

    private PersistenciaListas persistenciaListas;
    private ServerEndpointAnnotated sea;
    private RecursoExpediente_Client rec;
    private Gson gson;

    /**
     * Constructor que inicializa las variables de la clase.
     *
     * @param sea instancia del ServerEndpointAnnotated por el cual se realizan
     * las conexiones.
     */
    public Control(ServerEndpointAnnotated sea) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            this.persistenciaListas = new PersistenciaListas();
        } catch (SQLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.sea = sea;
        this.rec = new RecursoExpediente_Client();
    }

    /**
     * Método que busca un expediente por su Id en el server remoto.
     *
     * @param expedienteId Id del expediente a buscar.
     * @param sessionId Id de la sesión que realizó la solicitud.
     */
    public void buscaExpediente(String expedienteId, String sessionId) {
        Expediente expediente = null;
        try {
            expediente = persistenciaListas.obtenExpediente(new Integer(expedienteId)); // Se obtiene de la lista de expedientes el expediente con el Id solicitado.
        } catch (SQLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (expediente == null) {// Si no lo encuentra, se le solicita al servidor remoto.
            System.out.println("No se encontró... Solicitando al server remoto...");

            try {
                expediente = rec.getExpediente(expedienteId);
            } catch (NotFoundException NFE) {
                enviaExpediente(null, sessionId);// Si no se encontró en el servidor remoto, envia un nulo.
                return;// Y aquí acaba el método.
            }
            
            try {
                persistenciaListas.agregaExpediente(expediente);// Si fue encontrado se añade a la lista de expedientes del servidor local.
            } catch (SQLException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String expedienteGson = gson.toJson(expediente);
        enviaExpediente(expedienteGson, sessionId);
    }

    /**
     * Método que envía el expediente a la sesión que lo solicitó.
     *
     * @param expedienteGson Expediente en formato Json.
     * @param sessionId Id de la sesión que realizó la solicitud.
     */
    private void enviaExpediente(String expedienteGson, String sessionId) {
        System.out.println("Enviando...");
        sea.sendMessage(expedienteGson, sessionId);
    }
}
