/*
 * IListaExpedientes.java
 *
 * Creado en Mayo 16, 2020. 08:38.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public interface IListaExpedientes {
    
    public ArrayList<Expediente> getListaExpedientes();
    
    public Expediente getExpediente(String id);
    
    public Boolean addExpediente(Expediente expediente);
    
}
