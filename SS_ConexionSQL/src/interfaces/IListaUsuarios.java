/*
 * IListaUsuarios.java
 *
 * Creado en Mayo 19, 2020. 11:20.
 */
package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Usuario;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public interface IListaUsuarios {
    
    /**
     * Método que obtiene la lista de usuarios
     *
     * @return lista de usuarios de la base de datos
     * @throws SQLException Si hubo un problema con la consulta.
     */
    public ArrayList<Usuario> getListaUsuarios() throws SQLException;

    /**
     * Obtiene un usuario cuyo id concuerde con el id del parámetro.
     *
     * @param id Id del usuario a obtener
     * @return Usuario cuyo id concuerde con el id del parámetro
     * @throws SQLException Si hubo un problema con la consulta.
     */
    public Usuario getUsuario(int id) throws SQLException;

    /**
     * Agrega un usuario a la base de datos.
     *
     * @param usuario usuario a agregar
     * @throws SQLException Si hubo un problema con la declaracion.
     */
    public void addUsuario(Usuario usuario) throws SQLException;
    
    /**
     * Actualiza un usuario en la base de datos.
     *
     * @param usuario usuario a actualizar
     * @throws SQLException Si hubo un problema con la declaracion.
     */
    public void updateUsuario(Usuario usuario) throws SQLException;
    
}
