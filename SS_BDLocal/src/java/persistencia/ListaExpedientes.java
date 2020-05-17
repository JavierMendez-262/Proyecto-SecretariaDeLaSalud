/*
 * ListaExpedientes.java
 * 
 * Creado en Abril 18, 2020. 20:09
 */
package persistencia;

import java.util.ArrayList;
import objetosnegocio.Expediente;

/**
 * Clase que simula una persistencia con la lista de expedientes.
 * 
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ListaExpedientes {

    private ArrayList<Expediente> listaExpedientes;
    private static ListaExpedientes instance;

    /**
     * Constructor por omisión.
     */
    private ListaExpedientes() {
        listaExpedientes = new ArrayList<>();
    }
    
    public static ListaExpedientes getInstance() {
        if (instance == null) {
            instance = new ListaExpedientes();
        }
        return instance;
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
     * Método que obtiene un expediente comparando su Id con el Id del parámetro.
     * @param id Id del expediente solicitado.
     * @return Expediente con Id solicitada.
     */
    public Expediente getExpediente(String id) {
        for (Expediente expediente : listaExpedientes) {
            if (expediente.id.equals(id)) {
                return expediente;
            }
        }
        return null;
    }

    /**
     * Método que agrega un expediente a la lista de expedientes, sólo cuando
     * este no se encuentre ya en la lista.
     *
     * @param expediente expediente a agregar.
     * @return True si fue agregado, false en caso contrario.
     */
    public Boolean addExpediente(Expediente expediente) {
        if (getExpediente(expediente.getId()) == null) {
            listaExpedientes.add(expediente);
            return true;
        }
        return false;
    }
}
