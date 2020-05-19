/*
 * PersistenciaListas.java
 *
 * Creado en Mayo 17, 2020. 19:10.
 */
package dao;

import conexionSQL.ConexionSQL;
import interfaces.IPersistenciaListas;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Expediente;
import dao.ListaExpedientes;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class PersistenciaListas implements IPersistenciaListas {

    private final String serverName = "DESKTOP-41MLEHM\\SQLEXPRESS";
    private final String databaseName = "BDLocal";
    private final String user = "DBOwner";
    private final String password = "root";

    private static PersistenciaListas instance;
    private ConexionSQL conexion;

    private ListaExpedientes listaExpedientes;
    private ListaUsuarios listaUsuarios;
    private ListaAccesoExpedientes listaAccesoExpedientes;

    /**
     * Constructor que inicializa las variables de la clase al valor de sus
     * atributos.
     *
     * @throws SQLException Si se produjo un error en la consulta
     * @throws ClassNotFoundException Si la clase no fue encontrada.
     */
    private PersistenciaListas() throws SQLException, ClassNotFoundException {
        conexion = new ConexionSQL(serverName, databaseName, user, password);

        listaExpedientes = new ListaExpedientes(conexion);
        listaUsuarios = new ListaUsuarios(conexion);
        listaAccesoExpedientes = new ListaAccesoExpedientes(conexion);
    }

    /**
     * Crea una instancia de la clase en caso de sur nula.
     *
     * @return Instancia de la clase.
     * @throws SQLException Si se produjo un error en la consulta
     * @throws ClassNotFoundException Si la clase no fue encontrada.
     */
    public static PersistenciaListas getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new PersistenciaListas();
        }
        return instance;
    }

    @Override
    public ArrayList<Expediente> obtenListaExpedientes() throws SQLException {
        return listaExpedientes.getListaExpedientes();
    }

    @Override
    public Expediente obtenExpediente(int id) throws SQLException {
        return listaExpedientes.getExpediente(id);
    }

    @Override
    public void agregaExpediente(Expediente Expediente) throws SQLException {
        listaExpedientes.addExpediente(Expediente);
    }

}
