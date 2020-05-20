/*
 * RecursoUsuario_Client.java
 * 
 * Creado en Mayo 20, 2020. 01:13.
 */
package conexion.rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import negocio.Usuario;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientProperties;

/**
 * Jersey REST client generated for REST resource:RecursoUsuario [login]<br>
 * USAGE:
 * <pre>
 *        RecursoUsuario_Client client = new RecursoUsuario_Client();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author javie
 */
public class RecursoUsuario_Client {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://localhost:8443/SS_BDLocal/webresources";

    public RecursoUsuario_Client() {
        System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, "lib/certs/keystore.jks");
        System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, "lib/certs/keystore.jks");
        System.getProperties().put(SSLContextConfigurator.KEY_STORE_PASSWORD, "secretaria");
        System.getProperties().put(SSLContextConfigurator.TRUST_STORE_PASSWORD, "secretaria");

        final SSLContextConfigurator defaultConfig = new SSLContextConfigurator();
        defaultConfig.retrieve(System.getProperties());

        SSLEngineConfigurator sslEngineConfigurator = new SSLEngineConfigurator(defaultConfig, true, false, false);

        client = javax.ws.rs.client.ClientBuilder.newClient().property(ClientProperties.SSL_ENGINE_CONFIGURATOR, sslEngineConfigurator);
        webTarget = client.target(BASE_URI).path("login");
    }

    public Response validar(Usuario usuario) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(usuario, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public void close() {
        client.close();
    }
}
