/*
 * Control.java
 * 
 * Documentado en Mayo 19, 2020. 17:24.
 */
package conexion.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conexion.rest.RecursoAccesoExpediente_Client;
import conexion.rest.RecursoUsuario_Client;
import java.util.ArrayList;
import java.util.Collections;
import negocio.AccesoExpediente;
import negocio.Usuario;

/**
 * Clase que maneja las conexiones al servidor.
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Control {

    private String nickname;
    private String password;
    private String token;
    private RecursoAccesoExpediente_Client raec;
    private RecursoUsuario_Client ruc;
    private Gson gson;

    /**
     * Constructor que inicializa las variables de la clase.
     */
    public Control(String nickname, String password) {
        this.raec = new RecursoAccesoExpediente_Client();
        this.ruc = new RecursoUsuario_Client();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        token = ruc.validar(new Usuario(nickname, password, 0, false)).readEntity(String.class);
    }

    /**
     * Método que solicita los accesos a expedientes del paciente cuyo id
     * concuerde con el id del parámetro.
     *
     * @param id Id del paciente
     * @return Acceso a expedientes solicitado
     */
    public ArrayList<AccesoExpediente> getAccesoExpedientes(String id) {
        String token = ruc.validar(new Usuario(nickname, password, 0, false)).readEntity(String.class);
        ArrayList<AccesoExpediente> listaAccesoExpediente = new ArrayList<>();
        Collections.addAll(listaAccesoExpediente, raec.getAccesoExpediente(AccesoExpediente[].class, id, token));
        return listaAccesoExpediente;
    }

    /**
     * Método que solicita los accesos a expedientes pendientes del paciente
     * cuyo id concuerde con el id del parámetro.
     *
     * @param id Id del paciente
     * @return Acceso a expedientes pendientes solicitado
     */
    public ArrayList<AccesoExpediente> getAccesoExpedientesPendientes(String id) {
        ArrayList<AccesoExpediente> listaAccesoExpediente = new ArrayList<>();
        Collections.addAll(listaAccesoExpediente, raec.getAccesoExpedientePendiente(AccesoExpediente[].class, id, token));
        return listaAccesoExpediente;
    }

    /**
     * Autoriza el acceso de un expediente solicitado por un médico.
     *
     * @param idExpediente Id de expediente del que se solicita una autorización
     * @param idMedico Id del médico que solicita el acceso
     */
    public void autorizarAccesoExpediente(String idExpediente, String idMedico) {
        AccesoExpediente ae = new AccesoExpediente(new Integer(idExpediente), new Integer(idMedico), false);
        raec.putAccesoExpediente(ae, token);
    }
}
