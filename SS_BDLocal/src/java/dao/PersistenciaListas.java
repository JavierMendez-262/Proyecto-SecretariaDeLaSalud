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
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.AccesoExpediente;
import negocio.Usuario;

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
    public Expediente obtenExpediente(int id) {
        try {
            return listaExpedientes.getExpediente(id);
        } catch (SQLException ex) {
            return null;
        }
        
    }

    @Override
    public void agregaExpediente(Expediente expediente) throws SQLException {
        listaExpedientes.addExpediente(expediente);
    }

    @Override
    public ArrayList<Usuario> obtenListaUsuarios() throws SQLException {
        return listaUsuarios.getListaUsuarios();
    }

    @Override
    public Usuario obtenUsuario(int id) throws SQLException {
        return listaUsuarios.getUsuario(id);
    }

    @Override
    public Usuario obtenUsuario(String nickname) throws SQLException {
        return listaUsuarios.getUsuario(nickname);
    }

    @Override
    public ArrayList<AccesoExpediente> obtenAccesoExpedientesPorIdMedico(int id) throws SQLException {
        return listaAccesoExpedientes.getAccesoExpedientesPorIdMedico(id);
    }

    @Override
    public ArrayList<AccesoExpediente> obtenAccesoExpedientesPorIdMedicoAutorizado(int id) throws SQLException {
        return listaAccesoExpedientes.getAccesoExpedientesPorIdMedicoAutorizado(id);
    }

    @Override
    public ArrayList<AccesoExpediente> obtenAccesoExpedientesPorIdMedicoPendiente(int id) throws SQLException {
        return listaAccesoExpedientes.getAccesoExpedientesPorIdMedicoPendiente(id);
    }

    @Override
    public ArrayList<AccesoExpediente> obtenAccesoExpedientesPorIdPaciente(int id) throws SQLException {
        return listaAccesoExpedientes.getAccesoExpedientesPorIdPaciente(id);
    }

    @Override
    public ArrayList<AccesoExpediente> obtenAccesoExpedientesPorIdPacienteAutorizado(int id) throws SQLException {
        return listaAccesoExpedientes.getAccesoExpedientesPorIdPacienteAutorizado(id);
    }

    @Override
    public ArrayList<AccesoExpediente> obtenAccesoExpedientesPorIdPacientePendiente(int id) throws SQLException {
        return listaAccesoExpedientes.getAccesoExpedientesPorIdPacientePendiente(id);
    }

    @Override
    public void actualizarAccesoExpediente(AccesoExpediente acceso) throws SQLException {
        listaAccesoExpedientes.updateAccesoExpediente(acceso);
    }

    @Override
    public AccesoExpediente obtenAccesoExpediente(int idExpediente, int idMedico) {
        try {
            return listaAccesoExpedientes.getAccesoExpediente(idExpediente, idMedico);
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public void agregueAccesoExpediente(AccesoExpediente ae) throws SQLException {
        listaAccesoExpedientes.addAccesoExpediente(ae);
    }
}
