/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencialistas;

import java.util.ArrayList;
import objetosnegocio.Expediente;

/**
 *
 * @author Usuario
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
            if (expediente.id.equals(id)) {
                return expediente;
            }
        }
        return null;
    }
}
