/*
 * ListaAccesoExpedientes.java
 * 
 * Creado en Mayo 19, 2020. 11:55.
 */
package dao;

import conexionSQL.ConexionSQL;
import interfaces.IListaAccesoExpediente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.AccesoExpediente;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ListaAccesoExpedientes implements IListaAccesoExpediente {

    private ConexionSQL conexion;
    private ResultSet rs;
    private final String tableName = "AccesoExpediente";

    public ListaAccesoExpedientes(ConexionSQL conexion) throws SQLException, ClassNotFoundException {
        this.conexion = conexion;
    }

    @Override
    public ArrayList<AccesoExpediente> getAccesoExpedientesPorIdMedico() throws SQLException {
        ArrayList<AccesoExpediente> accesoExpedientes = new ArrayList<>();
        AccesoExpediente accesoExpediente = new AccesoExpediente();

        rs = conexion.executeQuery("SELECT * FROM " + tableName);
        while (rs.next()) {
            accesoExpediente.setIdExpediente(rs.getInt(1));
            accesoExpediente.setIdMedico(rs.getInt(2));
            accesoExpediente.setAutorizacion(rs.getInt(3) == 1);

            accesoExpedientes.add(accesoExpediente);
        }
        return accesoExpedientes;
    }

    @Override
    public ArrayList<AccesoExpediente> getAccesoExpedientesPorIdMedicoAutorizado() throws SQLException {
        ArrayList<AccesoExpediente> accesoExpedientes = new ArrayList<>();
        AccesoExpediente accesoExpediente = new AccesoExpediente();

        rs = conexion.executeQuery("SELECT * FROM " + tableName);
        while (rs.next()) {
            if (rs.getInt(3) == 0) {
                continue;
            } else {
                accesoExpediente.setIdExpediente(rs.getInt(1));
                accesoExpediente.setIdMedico(rs.getInt(2));
                accesoExpediente.setAutorizacion(rs.getInt(3) == 1);

                accesoExpedientes.add(accesoExpediente);
            }
        }
        return accesoExpedientes;
    }

    @Override
    public ArrayList<AccesoExpediente> getAccesoExpedientesPorIdMedicoPendiente() throws SQLException {
        ArrayList<AccesoExpediente> accesoExpedientes = new ArrayList<>();
        AccesoExpediente accesoExpediente = new AccesoExpediente();

        rs = conexion.executeQuery("SELECT * FROM " + tableName);
        while (rs.next()) {
            if (rs.getInt(3) == 1) {
                continue;
            } else {
                accesoExpediente.setIdExpediente(rs.getInt(1));
                accesoExpediente.setIdMedico(rs.getInt(2));
                accesoExpediente.setAutorizacion(rs.getInt(3) == 1);

                accesoExpedientes.add(accesoExpediente);
            }
        }
        return accesoExpedientes;
    }

    @Override
    public AccesoExpediente getAccesoExpediente(int idExpediente, int idMedico) throws SQLException {
        AccesoExpediente accesoExpediente = new AccesoExpediente();
        
        rs = conexion.executeQuery("SELECT * FROM " + tableName + " WHERE IdExpediente = " + idExpediente + " and IdMedico = " + idMedico);
        rs.next();
        accesoExpediente.setIdExpediente(rs.getInt(1));
        accesoExpediente.setIdMedico(rs.getInt(2));
        accesoExpediente.setAutorizacion(rs.getInt(3) == 1);

        return accesoExpediente;
    }

    @Override
    public void addAccesoExpediente(AccesoExpediente accesoExpediente) throws SQLException {
        conexion.executeStatement("INSERT INTO " + tableName + " "
                + "VALUES ("
                + "'" + accesoExpediente.getIdExpediente() + "', "
                + "'" + accesoExpediente.getIdMedico() + "');");
    }

    @Override
    public void updateAccesoExpediente(AccesoExpediente accesoExpediente) throws SQLException {
        conexion.executeStatement("UPDATE " + tableName + " "
                + "SET "
                + "estaAutorizado =  " + (accesoExpediente.estaAutorizado() == true ? 1 : 0) + " "
                + "WHERE "
                + "IdExpediente = " + accesoExpediente.getIdExpediente() + " "
                + "and IdMedico = " + accesoExpediente.getIdMedico());
    }
}
