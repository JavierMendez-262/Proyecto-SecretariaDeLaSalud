/*
 * Control.java
 * 
 * Creado en Abril 18, 2020. 20:20
 */
package conexion.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    public void buscaExpediente(String sessionId, String expedienteId) {
        Expediente expediente = listaExpedientes.getExpedienteId(expedienteId);
        if (expediente == null) {
            MainProducer producer = new MainProducer(this);
            producer.produceMessage(sessionId, expedienteId);
        } else {
            Gson gson = new Gson();
            String expedienteGson = gson.toJson(expediente);
            
            enviaExpediente(sessionId, expedienteGson);
        }
    }
    
    public void enviaExpediente(String sessionId, String expedienteGson) {
        sea.sendMessage(expedienteGson, sessionId);
    }

}
