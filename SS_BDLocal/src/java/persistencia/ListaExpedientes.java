/*
 * ListaExpedientes.java
 * 
 * Creado en Abril 18, 2020. 20:09
 */
package persistencia;

import java.util.ArrayList;
import objetosnegocio.Expediente;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class ListaExpedientes {

    private ArrayList<Expediente> listaExpedientes;

    public ListaExpedientes() {
        listaExpedientes = new ArrayList<>();
        listaExpedientes.add(new Expediente("Dahir Valenzuela", "001", "09/10/2020", "Dr. House", "Basurero", "Coronavairus"));
        listaExpedientes.add(new Expediente("Felipe Gustavo", "002", "08/08/2019", "Dr. Simi", "Ejecutivo", "Gripa"));
    }

    public ArrayList<Expediente> getListaExpedientes() {
        return listaExpedientes;
    }
    
    public Expediente getExpedienteId(String id) {
        for (Expediente expediente : listaExpedientes) {
            if(expediente.id.equals(id)) {
                return expediente;
            }
        }
        return null;
    }
}
