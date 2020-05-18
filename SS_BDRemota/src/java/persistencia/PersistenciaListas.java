/*
 * PersistenciaListas.java
 *
 * Creado en Mayo 17, 2020. 19:10.
 */
package persistencia;

import interfaces.IPersistenciaListas;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Expediente;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class PersistenciaListas implements IPersistenciaListas{
    
    private final String serverName = "DESKTOP-41MLEHM\\SQLEXPRESS";
    private final String databaseName = "BDRemota";
    private final String user = "DBOwner";
    private final String password = "root";

    private ListaExpedientes listaExpedientes;

    public PersistenciaListas() throws SQLException, ClassNotFoundException {
        listaExpedientes = new ListaExpedientes(serverName, databaseName, user, password);
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
