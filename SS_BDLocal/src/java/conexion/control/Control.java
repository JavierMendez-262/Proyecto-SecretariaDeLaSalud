/*
 * Control.java
 * 
 * Creado en Abril 18, 2020. 20:20
 */
package conexion.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conexion.puertosRabbit.MainConsumer;
import conexion.puertosRabbit.MainProducer;
import conexion.websockets.ServerEndpointAnnotated;
import objetosnegocio.Expediente;
import persistencia.ListaExpedientes;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Control {
    
    private ListaExpedientes listaExpedientes;
    private ServerEndpointAnnotated sea;

    public Control(ServerEndpointAnnotated sea) {
        this.listaExpedientes = new ListaExpedientes();
        this.sea = sea;
    }

    public void buscaExpediente(String expedienteId, String sessionId) {
        Expediente expediente = listaExpedientes.getExpediente(expedienteId);
        if (expediente == null) {
            
            // Solicitar recursos através de API Rest a la BDRemota
        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String expedienteGson = gson.toJson(expediente);
            
            enviaExpediente(expedienteGson, sessionId);
        }
    }
    
    public void enviaExpediente(String expedienteGson, String sessionId) {
        System.out.println("Enviando...");
        sea.sendMessage(expedienteGson, sessionId);
    }

}
