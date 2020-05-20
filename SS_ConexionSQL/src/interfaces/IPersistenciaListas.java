/*
 * IPersistenciaListas.java
 *
 * Creado en Mayo 17, 2020. 18:52.
 */
package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Expediente;
import negocio.Usuario;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public interface IPersistenciaListas {

    /**
     * Obtiene la lista de expedientes.
     *
     * @return Lista de expedientes.
     * @throws SQLException Cuando haya algun problema en la base de datos.
     */
    public ArrayList<Expediente> obtenListaExpedientes() throws SQLException;

    /**
     * Obtiene un expediente cuyo Id concuerde con el Id del parametro.
     *
     * @param id Id del expediente a obtener.
     * @return Expediente cuyo Id concuerde con el Id del parametro.
     * @throws SQLException Cuando haya algun problema en la base de datos.
     */
    public Expediente obtenExpediente(int id) throws SQLException;

    /**
     * Agrega un expediente a la lista de expedientes.
     *
     * @param Expediente Expediente a agregar a la lista.
     * @throws SQLException Cuando haya algun problema en la base de datos.
     */
    public void agregaExpediente(Expediente expediente) throws SQLException;

    /**
     * Obtiene la lista de usuarios.
     *
     * @return Lista de usuarios.
     * @throws SQLException Cuando haya algun problema en la base de datos.
     */
    public ArrayList<Usuario> obtenListaUsuarios() throws SQLException;

    /**
     * Obtiene un usuario cuyo Id concuerde con el id del parametro.
     * 
     * @param id Id del usuario a obtener.
     * @return Usuario con Id del parametro
     * @throws SQLException Cuando haya algun problema en la base de datos.
     */
    public Usuario obtenUsuario(int id) throws SQLException;
    
    /**
     * Obtiene un usuario cuyo nombre concuerde con el nombre del parametro.
     * 
     * @param nombre Nombre del usuario.
     * @return Usuario con el nickname del parametro
     * @throws SQLException 
     */
    public Usuario obtenUsuario(String nickname) throws SQLException;
}
