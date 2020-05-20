/*
 * RecursoUsuario_Client.java
 * 
 * Creado en Mayo 19, 2020. 21:10.
 */
package conexion.rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import negocio.Usuario;

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
    private static final String BASE_URI = "http://localhost:8080/SS_BDLocal/webresources";

    public RecursoUsuario_Client() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("login");
    }

    public Response validar(Usuario usuario) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(usuario, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public void close() {
        client.close();
    }
    
}
