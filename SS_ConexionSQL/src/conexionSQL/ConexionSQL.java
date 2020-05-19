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

    private Connection con;

    /**
     * Constructor que inicializa los atributos de la clase al valor de sus
     * parámetros.
     *
     * @param serverName Nombre del servidor de la base de datos.
     * @param databaseName Nombre de la base de datos a utilizar.
     * @param user Usuario con permisos para administrar la base de datos.
     * @param password Contrasena del usuario.
     * @throws SQLException Si se produce un error en la base de datos.
     * @throws ClassNotFoundException Si se produce un error con el jdbc.
     */
    public ConexionSQL(String serverName, String databaseName, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://" + serverName + ":1433;"
                + "databaseName=" + databaseName + ";"
                + "user=" + user + ";"
                + "password=" + password + ";";
        this.con = DriverManager.getConnection(connectionURL);
    }

    /**
     * Ejecuta el query que recibe en su parametro en la base de datos.
     *
     * @param query Consulta a ejecutar en la base de datos.
     * @return ResultSet con el conjunto de resultados
     * @throws SQLException Si hubo un problema con la consulta.
     */
    public ResultSet executeQuery(String query) throws SQLException {
        Statement st = con.createStatement();
        return st.executeQuery(query);
    }

    /**
     * Ejecuta el statemente que recibe en su parametro en la base de datos.
     *
     * @param statement Declaracion a ejecutar en la base de datos.
     * @throws SQLException Si hubo un problema con la consulta.
     */
    public void executeStatement(String statement) throws SQLException {
        Statement st = con.createStatement();
        st.execute(statement);
    }

}
