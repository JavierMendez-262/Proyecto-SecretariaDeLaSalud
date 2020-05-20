/*
 * ListaUsuarios.java
 * 
 * Creado en Mayo 19, 2020. 11:55.
 */
package dao;

import conexionSQL.ConexionSQL;
import interfaces.IListaUsuarios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Usuario;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ListaUsuarios implements IListaUsuarios {

    private ConexionSQL conexion;
    private ResultSet rs;
    private final String tableName = "Usuario";

    public ListaUsuarios(ConexionSQL conexion) throws SQLException, ClassNotFoundException {
        this.conexion = conexion;
    }

    @Override
    public ArrayList<Usuario> getListaUsuarios() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();

        rs = conexion.executeQuery("SELECT * FROM " + tableName);
        while (rs.next()) {
            usuario.setId(rs.getInt(1));
            usuario.setNickname(rs.getString(2));
            usuario.setPassword(rs.getString(3));
            usuario.setEsMedico(rs.getInt(4) == 1);

            usuarios.add(usuario);
        }
        return usuarios;
    }

    @Override
    public Usuario getUsuario(int id) throws SQLException {
        Usuario usuario = new Usuario();

        rs = conexion.executeQuery("SELECT * FROM " + tableName + " WHERE ID = " + id);
        rs.next();
        usuario.setId(rs.getInt(1));
        usuario.setNickname(rs.getString(2));
        usuario.setPassword(rs.getString(3));
        usuario.setEsMedico(rs.getInt(4) == 1);

        return usuario;
    }

    @Override
    public void addUsuario(Usuario usuario) throws SQLException {
        conexion.executeStatement("INSERT INTO " + tableName + " "
                + "VALUES ("
                + "'" + usuario.getNickname() + "', "
                + "'" + usuario.getPassword() + "', "
                + "'" + usuario.isEsMedico() + "');");
    }

    @Override
    public void updateUsuario(Usuario usuario) throws SQLException {
        conexion.executeStatement("UPDATE " + tableName + " "
                + "SET "
                + "Nickname = " + usuario.getNickname() + " "
                + "Password = " + usuario.getPassword() + " "
                + "esMedico = " + usuario.isEsMedico() + " "
                + "WHERE ID = " + usuario.getId());
    }

    @Override
    public Usuario getUsuario(String nombre) throws SQLException {
        Usuario usuario = new Usuario();

        rs = conexion.executeQuery("SELECT * FROM " + tableName + " WHERE Nickname = '" + nombre + "' ");
        rs.next();
        usuario.setId(rs.getInt(1));
        usuario.setNickname(rs.getString(2));
        usuario.setPassword(rs.getString(3));
        usuario.setEsMedico(rs.getInt(4) == 1);

        return usuario;
    }

}
