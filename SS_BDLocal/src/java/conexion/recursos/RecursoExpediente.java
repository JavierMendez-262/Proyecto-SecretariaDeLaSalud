/*
 * RecursoExpediente.java
 * 
 * Creado en Abril 17, 2020. 18:01.
 */
package conexion.recursos;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import objetosnegocio.Expediente;
import persistencia.ListaExpedientes;

/**
 * REST Web Service
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
@Path("expediente")
public class RecursoExpediente {
    
    private ListaExpedientes listaExpedientes = new ListaExpedientes();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RecursoExpediente
     */
    public RecursoExpediente() {
    }

    /**
     * Retrieves representation of an instance of recursos.RecursoExpediente
     * @return an instance of objetosnegocio.Expediente
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExpediente(@PathParam("id")String id) {
        //Envia una peticion GET al ServerEndpointAnnotated.java para mandar el expediente con determinado ID a la sesión conectada.
        Expediente expediente = listaExpedientes.getExpediente(id);
        return Response.status(200).entity(expediente).build();
    }

    /**
     * PUT method for updating or creating an instance of RecursoExpediente
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putExpediente(Expediente content) {
        return Response.status(200).build();
    }
}
