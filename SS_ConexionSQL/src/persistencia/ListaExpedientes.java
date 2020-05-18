/*
 * IListaExpedientes.java
 *
 * Creado en Mayo 17, 2020. 18:04.
 */
package persistencia;

import conexionSQL.ConexionSQL;
import interfaces.IListaExpedientes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Expediente;

/**
 * Clase que consulta todos los expedientes alojados en la base de datos.
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ListaExpedientes implements IListaExpedientes {

    private ConexionSQL conexion;
    private ResultSet rs;
    private final String table = "Expediente";

    public ListaExpedientes(String serverName, String databaseName, String user, String password) throws SQLException, ClassNotFoundException {
        conexion = new ConexionSQL(serverName, databaseName, user, password);
    }

    @Override
    public ArrayList<Expediente> getListaExpedientes() throws SQLException {
        ArrayList<Expediente> expedientes = new ArrayList<>();
        Expediente expediente = new Expediente();

        rs = conexion.executeQuery("SELECT * FROM " + table);
        while (rs.next()) {
            expediente.setId(rs.getInt(1));
            expediente.setNombre(rs.getString(2));
            expediente.setSexo(rs.getString(3).charAt(0));
            expediente.setEdad(rs.getInt(4));
            expediente.setDomicilio(rs.getString(5));

            expedientes.add(expediente);
        }
        return expedientes;
    }

    @Override
    public Expediente getExpediente(int id) throws SQLException {
        Expediente expediente = new Expediente();

        rs = conexion.executeQuery("SELECT * FROM " + table + " WHERE ID = " + Integer.toString(id));
        rs.next();
        expediente.setId(rs.getInt(1));
        expediente.setNombre(rs.getString(2));
        expediente.setSexo(rs.getString(3).charAt(0));
        expediente.setEdad(rs.getInt(4));
        expediente.setDomicilio(rs.getString(5));

        return expediente;
    }

    @Override
    public void addExpediente(Expediente expediente) throws SQLException {
        conexion.executeStatement("INSERT INTO " + table + " "
                + "VALUES ("
                + "'" + expediente.getId() + "', "
                + "'" + expediente.getNombre() + "', "
                + "'" + expediente.getSexo() + "', "
                + "'" + expediente.getEdad() + "', "
                + "'" + expediente.getDomicilio() + "');");
    }

}
