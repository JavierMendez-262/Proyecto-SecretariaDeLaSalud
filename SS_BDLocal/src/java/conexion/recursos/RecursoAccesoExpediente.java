/*
 * RecursoAccesoExpediente.java
 * 
 * Documentado en Mayo 19, 2020. 22:08.
 */
package conexion.recursos;

import dao.PersistenciaListas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import negocio.AccesoExpediente;

/**
 * REST Web Service
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
@Path("accesoexpediente")
public class RecursoAccesoExpediente {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RecursoAccesoExpediente
     */
    public RecursoAccesoExpediente() {
    }

    /**
     * Retrieves representation of an instance of
     * conexion.recursos.RecursoAccesoExpediente
     *
     * @return an instance of negocio.AccesoExpediente
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<AccesoExpediente> getAccesoExpediente(@PathParam("id") String id) {
        ArrayList<AccesoExpediente> acceso = null;
        try {
            PersistenciaListas pl = PersistenciaListas.getInstance();
            acceso = pl.obtenAccesoExpedientesPorIdPaciente(new Integer(id));
            return acceso;
        } catch (SQLException ex) {
            Logger.getLogger(RecursoAccesoExpediente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecursoAccesoExpediente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Retrieves representation of an instance of
     * conexion.recursos.RecursoAccesoExpediente
     *
     * @return an instance of negocio.AccesoExpediente
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<AccesoExpediente> getAccesoExpedientePendiente(@PathParam("id") String id) {
        ArrayList<AccesoExpediente> acceso = null;
        try {
            PersistenciaListas pl = PersistenciaListas.getInstance();
            acceso = pl.obtenAccesoExpedientesPorIdPacientePendiente(new Integer(id));
            return acceso;
        } catch (SQLException ex) {
            //Logger.getLogger(RecursoAccesoExpediente.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("SQLException");
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(RecursoAccesoExpediente.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("SQLException");
        }
        return null;
    }

    /**
     * PUT method for updating or creating an instance of
     * RecursoAccesoExpediente
     *
     * @param autorizar Autorización
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putAccesoExpediente(AccesoExpediente autorizar) {
        try {
            PersistenciaListas pl = PersistenciaListas.getInstance();
            AccesoExpediente ae = pl.obtenAccesoExpediente(autorizar.getIdExpediente(), autorizar.getIdMedico());
            ae.setAutorizacion(true);
            pl.actualizarAccesoExpediente(ae);
        } catch (SQLException ex) {
            Logger.getLogger(RecursoAccesoExpediente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecursoAccesoExpediente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
