/*
 * Control.java
 * 
 * Documentado en Mayo 15, 2020. 09:41.
 */
package conexion.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URI;
import negocio.Expediente;
import org.glassfish.tyrus.client.ClientManager;
import conexion.rest.RecursoExpediente_Client;
import conexion.websockets.ClientEndpointAnnotated;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.websocket.DeploymentException;
import javax.ws.rs.NotFoundException;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientProperties;

/**
 * Clase que maneja las conexiones al servidor.
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Control {

    private static final String URI = "wss://localhost:8443/SS_BDLocal/websockets/expediente";
    private RecursoExpediente_Client rec;
    private ClientEndpointAnnotated cea;
    private Gson gson;

    /**
     * Constructor que inicializa las variables de la clase.
     */
    public Control() {
        this.rec = new RecursoExpediente_Client();
        this.cea = new ClientEndpointAnnotated(this);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Método que solicita el expediente al servidor de la BD Local a través de
     * RESTful y WebSockets.
     *
     * @param id Id del expediente a solicitar de la BD Local.
     * @return Expediente del Id solicitado.
     */
    public Expediente getExpediente(String id) throws IOException, URISyntaxException, DeploymentException {
        try {
            Expediente expediente = rec.getExpediente(id);// Se solicita el expediente al servidor a través de RESTful.

            System.out.println(gson.toJson(expediente));//En caso de que el servidor local lo posea se imprime no más para ver.

        } catch (NotFoundException NFE) {// Si no lo encuentra, solicitalo a través de WebSockets al servidor.
            prepareClient().connectToServer(cea, new URI(URI));
            cea.sendMessage(id);

            System.out.println("Espere un momento... ");// Se espera a que la solicitud sea procesada.
        }

        return null;
    }

    /**
     * Método que se invoca al obtenerse una respuesta de WS del Servidor.
     *
     * @param expedienteGson Expediente en formato Json solicitado
     */
    public void receivedExpedienteWS(String expedienteGson) {
        if (!expedienteGson.equals("null")) {
            Expediente expediente = gson.fromJson(expedienteGson, Expediente.class);

            System.out.println(expedienteGson);
        } else {
            System.out.println("Error: Expediente no encontrado.");
        }
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

    private ClientManager prepareClient() {
        System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, "lib/certs/keystore.jks");
        System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, "lib/certs/keystore.jks");
        System.getProperties().put(SSLContextConfigurator.KEY_STORE_PASSWORD, "secretaria");
        System.getProperties().put(SSLContextConfigurator.TRUST_STORE_PASSWORD, "secretaria");

        final SSLContextConfigurator defaultConfig = new SSLContextConfigurator();
        defaultConfig.retrieve(System.getProperties());

        SSLEngineConfigurator sslEngineConfigurator = new SSLEngineConfigurator(defaultConfig, true, false, false);

        ClientManager client = ClientManager.createClient();
        client.getProperties().put(ClientProperties.SSL_ENGINE_CONFIGURATOR, sslEngineConfigurator);

        return client;
    }
}
