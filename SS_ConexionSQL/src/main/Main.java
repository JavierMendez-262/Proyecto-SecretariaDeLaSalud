/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import conexionSQL.ConexionSQL;
import dao.ListaAccesoExpedientes;
import dao.ListaExpedientes;
import dao.ListaUsuarios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.AccesoExpediente;

/**
 *
 * @author javie
 */
public class Main {

    private static final String serverName = "DESKTOP-41MLEHM\\SQLEXPRESS";
    private static final String databaseName = "BDLocal";
    private static final String user = "DBOwner";
    private static final String password = "root";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConexionSQL conexion;
        try {
            conexion = new ConexionSQL(serverName, databaseName, user, password);
            ListaAccesoExpedientes lae = new ListaAccesoExpedientes(conexion);
            ListaExpedientes le = new ListaExpedientes(conexion);
            ListaUsuarios lu = new ListaUsuarios(conexion);
            for (AccesoExpediente ae : lae.getAccesoExpedientesPorIdMedico()) {
                System.out.println(lu.getUsuario(ae.getIdMedico()).getNickname() + (ae.estaAutorizado() ? "" : " no") + " tiene acceso al expediente de " + le.getExpediente(ae.getIdExpediente()).getNombre());
            }
            AccesoExpediente ae = lae.getAccesoExpediente(1, 4);
            ae.setAutorizacion(!lae.getAccesoExpediente(1, 4).estaAutorizado());
            lae.updateAccesoExpediente(ae);

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
