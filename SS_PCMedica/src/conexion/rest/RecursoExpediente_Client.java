/*
 * RecursoExpediente_Client.java
 * 
 * Documentado en Mayo 15, 2020. 09:50.
 */
package conexion.rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import objetosnegocio.Expediente;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientProperties;

/**
 * Clase que realiza una conexión RESTful al servidor de BD Local.
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class RecursoExpediente_Client {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://localhost:8443/SS_BDLocal/webresources";

    /**
     * Constructor que inicializa las variables de la clase.
     */
    public RecursoExpediente_Client() {
        System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, "lib/certs/keystore.jks");
        System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, "lib/certs/keystore.jks");
        System.getProperties().put(SSLContextConfigurator.KEY_STORE_PASSWORD, "secretaria");
        System.getProperties().put(SSLContextConfigurator.TRUST_STORE_PASSWORD, "secretaria");

        final SSLContextConfigurator defaultConfig = new SSLContextConfigurator();
        defaultConfig.retrieve(System.getProperties());

        SSLEngineConfigurator sslEngineConfigurator = new SSLEngineConfigurator(defaultConfig, true, false, false);

        client = javax.ws.rs.client.ClientBuilder.newClient().property(ClientProperties.SSL_ENGINE_CONFIGURATOR, sslEngineConfigurator);
        webTarget = client.target(BASE_URI).path("expediente");
    }

    /**
     * Método que solicita al servidor un expediente.
     *
     * @param id Id del expediente a solicitar de la BD Local.
     * @return Expediente del Id solicitado.
     * @throws ClientErrorException cuando se produzca un problema con la
     * solicitud.
     */
    public Expediente getExpediente(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(Expediente.class);
    }

    /**
     * Método que actualiza en el servidor un expediente.
     *
     * @param expediente Expediente a actualizar en el servidor.
     * @return Respuesta de la solicitud al servidor.
     * @throws ClientErrorException cuando se produzca un problema con la
     * solicitud.
     */
    public Response putExpediente(Expediente expediente) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(expediente, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    /**
     * Método que cierra la conexión con el cliente.
     */
    public void close() {
        client.close();
    }
}
