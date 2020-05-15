/*
 * Control.java
 * 
 * Documentado en Mayo 15, 2020. 09:41.
 */
package conexion.control;

import java.net.URI;
import objetosnegocio.Expediente;
import org.glassfish.tyrus.client.ClientManager;
import conexion.rest.RecursoExpediente_Client;
import conexion.websockets.ClientEndpointAnnotated;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientProperties;

/**
 * Clase que maneja las conexiones al servidor.
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Control {

    private final String URI = "wss://localhost:8443/SS_BDLocal/websockets/expediente";
    private RecursoExpediente_Client rec;
    private ClientEndpointAnnotated cea;

    /**
     * Constructor que inicializa las variables de la clase.
     */
    public Control() {
        try {
            ClientManager client = ClientManager.createClient();

            System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, "lib/certs/keystore.jks");
            System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, "lib/certs/keystore.jks");
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

    /**
     * Método que solicita el expediente al servidor de la BD Local a través de
     * RESTful y WebSockets.
     *
     * @param id Id del expediente a solicitar de la BD Local.
     * @return Expediente del Id solicitado.
     */
    public Expediente getExpediente(String id) {
        rec.getExpediente(id);
        try {
            cea.sendMessage(id);
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Cea recibe json y luego será transformado a Expediente
        return null;
    }

    /**
     * Método que actualiza el expediente al servidor de la BD Local a través de 
     * RESTful.
     * 
     * @param expediente Expediente a actualizar de la BD Local.
     */
    public void putExpediente(Expediente expediente) {
        rec.putExpediente(expediente);
    }
}
