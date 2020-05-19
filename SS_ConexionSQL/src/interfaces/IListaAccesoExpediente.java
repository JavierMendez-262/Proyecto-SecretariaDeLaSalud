/*
 * IListaAccesoExpediente.java
 *
 * Creado en Mayo 19, 2020. 11:20.
 */
package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import negocio.AccesoExpediente;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public interface IListaAccesoExpediente {

    /**
     * Obtiene la lista de acceso a expedientes solicitados por el médico cuyo
     * id concuerde con el del parámetro.
     *
     * @return Lista de acceso a expedientes solicitados
     * @throws SQLException Si hubo un problema con la consulta.
     */
    public ArrayList<AccesoExpediente> getAccesoExpedientesPorIdMedico() throws SQLException;

    /**
     * Obtiene la lista de acceso a expedientes autorizados solicitados por el
     * médico cuyo id concuerde con el del parámetro.
     *
     * @return Lista de acceso a expedientes autorizados solicitados por el
     * médico.
     * @throws SQLException Si hubo un problema con la consulta.
     */
    public ArrayList<AccesoExpediente> getAccesoExpedientesPorIdMedicoAutorizado() throws SQLException;

    /**
     * Obtiene la lista de acceso a expedientes con autorización pediente
     * solicitados por el médico cuyo id concuerde con el del parámetro.
     *
     * @return Lista de acceso a expedientes con autorización pediente
     * solicitados por el médico.
     * @throws SQLException Si hubo un problema con la consulta.
     */
    public ArrayList<AccesoExpediente> getAccesoExpedientesPorIdMedicoPendiente() throws SQLException;

    /**
     * Obtiene un acceso a expediente mediente el id del expediente al que el
     * médico del id médico desea tener acceso.
     *
     * @param idExpediente Id del expediente al que se solicita tener acceso.
     * @param idMedico Id del medico que solicita el acceso.
     * @return AccesoExpediente cuyos id concuerde con los de los parametros.
     * @throws SQLException Si hubo un problema con la consulta.
     */
    public AccesoExpediente getAccesoExpediente(int idExpediente, int idMedico) throws SQLException;

    /**
     * Agrega una solicitud de acceso expediente a la base de datos.
     *
     * @param accesoExpediente Acceso a expediente solicitado.
     * @throws SQLException Si hubo un problema con la consulta.
     */
    public void addAccesoExpediente(AccesoExpediente accesoExpediente) throws SQLException;
    
    /**
     * Actualiza un acceso a expediente en la base de datos.
     *
     * @param accesoExpediente acceso a expediente a actualizar
     * @throws SQLException Si hubo un problema con la declaracion.
     */
    public void updateAccesoExpediente(AccesoExpediente accesoExpediente) throws SQLException;

}
