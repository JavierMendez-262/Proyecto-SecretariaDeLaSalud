/*
 * ListaExpedientes.java
 *
 * Creado en Mayo 16, 2020. 08:38.
 */
package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Expediente;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public interface IListaExpedientes {

    /**
     * Método que obtiene la lista de expedientes
     *
     * @return lista de expediente de la base de datos
     * @throws java.sql.SQLException
     * @throws SQLException Si hubo un problema con la consulta.
     */
    public ArrayList<Expediente> getListaExpedientes() throws SQLException;

    /**
     * Obtiene un expediente cuyo id concuerde con el id del parámetro.
     *
     * @param id Id del expediente a obtener
     * @return Expediente cuyo id concuerde con el id del parámetro
     * @throws java.sql.SQLException
     * @throws SQLException Si hubo un problema con la consulta.
     */
    public Expediente getExpediente(int id) throws SQLException;

    /**
     * Agrega un expediente a la base de datos.
     *
     * @param expediente expediente a agregar
     * @throws java.sql.SQLException
     * @throws SQLException Si hubo un problema con la declaracion.
     */
    public void addExpediente(Expediente expediente) throws SQLException;

}
