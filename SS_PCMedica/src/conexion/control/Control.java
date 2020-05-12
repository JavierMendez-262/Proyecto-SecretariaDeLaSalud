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
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientProperties;

/**
 *
 * @author Usuario
 */
public class Control {

    private final String URI = "wss://localhost:8443/SS_BDLocal/websockets/expediente";
    private RecursoExpediente_Client rec;
    private ClientEndpointAnnotated cea;

    public Control() {
        try {
            ClientManager client = ClientManager.createClient();
            
            System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, "src/conexion/keystore.jks");
            System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, "src/conexion/keystore.jks");
            System.getProperties().put(SSLContextConfigurator.KEY_STORE_PASSWORD, "secretaria");
            System.getProperties().put(SSLContextConfigurator.TRUST_STORE_PASSWORD, "secretaria");
            
            final SSLContextConfigurator defaultConfig = new SSLContextConfigurator();
            defaultConfig.retrieve(System.getProperties());
            
            SSLEngineConfigurator sslEngineConfigurator = new SSLEngineConfigurator(defaultConfig, true, false, false);
            
            client.getProperties().put(ClientProperties.SSL_ENGINE_CONFIGURATOR, sslEngineConfigurator);
            
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
