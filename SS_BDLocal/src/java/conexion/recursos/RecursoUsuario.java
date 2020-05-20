/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion.recursos;

import conexion.filtro.JWTokenHelper;
import dao.PersistenciaListas;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import negocio.Usuario;

/**
 * REST Web Service
 *
 * @author javie
 */
@Path("login")
public class RecursoUsuario {

    private PersistenciaListas persistenciaListas;
    private static final String SECRET_KEY = "secretaria";

    @Context
    private UriInfo context;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validar(Usuario usuarioValidar) {
        System.out.println("hola");
        try {
            persistenciaListas = PersistenciaListas.getInstance();
            Usuario usuario = persistenciaListas.obtenUsuario(usuarioValidar.getNickname());
            
            if (usuario == null) {
                Response.status(Response.Status.UNAUTHORIZED).build();
            }
            
            if (!usuario.getPassword().equals(usuarioValidar.getPassword())) {
                Response.status(Response.Status.UNAUTHORIZED).build();
            }
            
            long tiempo = System.currentTimeMillis();
            
            String token = JWTokenHelper.getInstance().crearToken(usuario.getNickname(), usuario.getPassword());
            
            return Response.status(Response.Status.CREATED).entity(token).build();
            
        } catch (SQLException ex) {
            Logger.getLogger(RecursoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecursoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
