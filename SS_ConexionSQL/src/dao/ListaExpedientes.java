/*
 * IListaExpedientes.java
 *
 * Creado en Mayo 17, 2020. 18:04.
 */
package dao;

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
    private final String tableName = "Expediente";

    public ListaExpedientes(ConexionSQL conexion) throws SQLException, ClassNotFoundException {
        this.conexion = conexion;
    }

    @Override
    public ArrayList<Expediente> getListaExpedientes() throws SQLException {
        ArrayList<Expediente> expedientes = new ArrayList<>();
        Expediente expediente = new Expediente();

        rs = conexion.executeQuery("SELECT * FROM " + tableName);
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

        rs = conexion.executeQuery("SELECT * FROM " + tableName + " WHERE ID = " + id);
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
        conexion.executeStatement("INSERT INTO " + tableName + " "
                + "VALUES ("
                + "'" + expediente.getId() + "', "
                + "'" + expediente.getNombre() + "', "
                + "'" + expediente.getSexo() + "', "
                + "'" + expediente.getEdad() + "', "
                + "'" + expediente.getDomicilio() + "');");
    }

    @Override
    public void updateExpediente(Expediente expediente) throws SQLException {
        conexion.executeStatement("UPDATE " + tableName + " "
                + "SET "
                + "Nombre = '" + expediente.getNombre()+ "' "
                + "Sexo = '" + expediente.getSexo()+ "' "
                + "Edad = " + expediente.getEdad()+ " "
                + "Domicilio = '" + expediente.getDomicilio()+ "' "
                + "WHERE ID = " + expediente.getId());
    }

}
