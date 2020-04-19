/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion.control;

import java.net.URI;
import objetosnegocio.Expediente;
import org.glassfish.tyrus.client.ClientManager;
import conexion.rest.RecursoExpediente_Client;
import conexion.websockets.ClientEndpointAnnotated;

/**
 *
 * @author Usuario
 */
public class Control {

    private final String URI = "ws://localhost:8080/SS_BDLocal/consultaExpediente";
    private RecursoExpediente_Client rec;
    private ClientEndpointAnnotated cea;

    public Control() {
        try {
            ClientManager client = ClientManager.createClient();
            this.rec = new RecursoExpediente_Client();
            this.cea = new ClientEndpointAnnotated();
            client.connectToServer(cea, new URI(URI));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Expediente getExpediente(String id) {
        rec.getExpediente(Expediente.class, id);
        cea.sendMessage(id);
        // Cea recibe json y luego será transformado a Expediente
        return null;
    }

    public void putExpediente(Expediente expediente) {
        rec.putExpediente(expediente);
    }
}
