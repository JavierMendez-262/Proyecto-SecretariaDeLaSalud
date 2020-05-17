/*
 * ListaExpedientes.java
 * 
 * Creado en Abril 18, 2020. 20:09
 */
package persistencia;

import java.util.ArrayList;
import negocio.Expediente;

/**
 * Clase que simula una persistencia con la lista de expedientes.
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ListaExpedientes {

    private ArrayList<Expediente> listaExpedientes;

    /**
     * Constructor por omisión.
     */
    public ListaExpedientes() {
        listaExpedientes = new ArrayList<>();
    }

    /**
     * Método que obtiene la lista de expedientes.
     *
     * @return Lista de expedientes.
     */
    public ArrayList<Expediente> getListaExpedientes() {
        return listaExpedientes;
    }

    /**
     * Método que obtiene un expediente comparando su Id con el Id del
     * parámetro.
     *
     * @param id Id del expediente solicitado.
     * @return Expediente con Id solicitada.
     */
    public Expediente getExpediente(String id) {
        for (Expediente expediente : listaExpedientes) {
            if (expediente.id == new Integer(id)) {
                return expediente;
            }
        }
        return null;
    }
}
