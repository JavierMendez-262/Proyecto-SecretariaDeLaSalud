/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import conexionSQL.ConexionSQL;
import dao.ListaAccesoExpedientes;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.AccesoExpediente;

/**
 *
 * @author javie
 */
public class Main {

    private static final String serverName = "SHOWTIME\\SQLEXPRESS";
    private static final String databaseName = "BDLocal";
    private static final String user = "sa";
    private static final String password = "mypass";

    public static void main(String[] args) {
        ConexionSQL conexion;
        try {
            conexion = new ConexionSQL(serverName, databaseName, user, password);
            ListaAccesoExpedientes lae = new ListaAccesoExpedientes(conexion);
            lae.getAccesoExpedientesPorIdPaciente(1);
            for (AccesoExpediente accesoExpediente : lae.getAccesoExpedientesPorIdPaciente(1)) {
                System.out.println(accesoExpediente.getIdExpediente() + " " + accesoExpediente.getIdMedico() + " " + accesoExpediente.estaAutorizado());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
