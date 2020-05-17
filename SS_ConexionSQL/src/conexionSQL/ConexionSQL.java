/*
 * ConexionSQL.java
 *
 * Creado en Mayo 16, 2020. 08:38.
 */
package conexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que maneja conexiones a una base de datos SQL
 *
 * @author @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ConexionSQL {

    private static ConexionSQL instance;
    private Connection con;
    
    private ConexionSQL(String serverName, String databaseName, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://" + serverName + ":1433;"
                + "databaseName=" + databaseName + ";"
                + "user=" + user + ";"
                + "password=" + password + ";";
        this.con = DriverManager.getConnection(connectionURL);
    }

    public static ConexionSQL getInstance(String serverName, String databaseName, String user, String password) throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new ConexionSQL(serverName, databaseName, user, password);
        }
        return instance;
    }
    
    public ResultSet executeQuery(String query) throws SQLException {
        Statement st = con.createStatement();
        return st.executeQuery(query);
    }
    
    public void executeStatement(String query) throws SQLException {
        Statement st = con.createStatement();
        st.execute(query);
    }

}
