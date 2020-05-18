/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import conexionSQL.ConexionSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javie
 */
public class Main {

    private static final String serverName = "DESKTOP-41MLEHM\\SQLEXPRESS";
    private static final String databaseName = "BDRemota";
    private static final String user = "DBOwner";
    private static final String password = "root";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConexionSQL conexion;
        try {
            conexion = ConexionSQL.getInstance(serverName, databaseName, user, password);
            ResultSet rs = conexion.executeQuery("SELECT * FROM Expediente WHERE ID = 1");
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3).charAt(0));
            System.out.println(rs.getInt(4));
            System.out.println(rs.getString(5));
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
