/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion.rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientProperties;

/**
 *
 * @author Usuario
 */
public class RecursoExpediente_Client {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://localhost:8443/SS_BDRemota/webresources";

    public RecursoExpediente_Client() {
        System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, "src/java/conexion/keystore.jks");
        System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, "src/java/conexion/keystore.jks");
        System.getProperties().put(SSLContextConfigurator.KEY_STORE_PASSWORD, "secretaria");
        System.getProperties().put(SSLContextConfigurator.TRUST_STORE_PASSWORD, "secretaria");

        final SSLContextConfigurator defaultConfig = new SSLContextConfigurator();
        defaultConfig.retrieve(System.getProperties());

        SSLEngineConfigurator sslEngineConfigurator = new SSLEngineConfigurator(defaultConfig, true, false, false);
        client = javax.ws.rs.client.ClientBuilder.newClient().property(ClientProperties.SSL_ENGINE_CONFIGURATOR, sslEngineConfigurator);

        webTarget = client.target(BASE_URI).path("expediente");
    }

    public Response putExpediente(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public <T> T getExpediente(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
}
