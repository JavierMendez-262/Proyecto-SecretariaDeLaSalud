/*
 * RecursoExpediente.java
 * 
 * Creado en Abril 17, 2020. 18:01.
 */
package conexion.recursos;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import negocio.Expediente;
import dao.PersistenciaListas;
import negocio.AccesoExpediente;

/**
 * REST Web Service
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
@Path("expediente")
public class RecursoExpediente {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RecursoExpediente
     */
    public RecursoExpediente() {
    }

    /**
     * Retrieves representation of an instance of recursos.RecursoExpediente
     *
     * @return an instance of objetosnegocio.Expediente
     */
    @GET
    @Path("{idExpediente}/{idMedico}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExpediente(@PathParam("idExpediente") String idExpediente, @PathParam("idMedico") String idMedico) {
        Expediente expediente = null;
        try {
            PersistenciaListas persistenciaListas = PersistenciaListas.getInstance();
            
            if (persistenciaListas.obtenExpediente(new Integer(idExpediente)) == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(expediente).build();
            }
            
            if (persistenciaListas.obtenAccesoExpediente(new Integer(idExpediente), new Integer(idMedico)) == null) {
                persistenciaListas.agregueAccesoExpediente(new AccesoExpediente(new Integer(idExpediente), new Integer(idMedico), false));
                return Response.status(Response.Status.NOT_FOUND).entity(expediente).build();
            } else if (persistenciaListas.obtenAccesoExpediente(new Integer(idExpediente), new Integer(idMedico)).estaAutorizado()) {
                expediente = persistenciaListas.obtenExpediente(new Integer(idExpediente));
                return Response.status(Response.Status.ACCEPTED).entity(expediente).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity(expediente).build();
            }

        } catch (SQLException ex) {
            Logger.getLogger(RecursoExpediente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecursoExpediente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).build();
    }

    /**
     * PUT method for updating or creating an instance of RecursoExpediente
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putExpediente(Expediente content
    ) {
        return Response.status(200).build();
    }
}
