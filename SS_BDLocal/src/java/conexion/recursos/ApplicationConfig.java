/*
 * AookicationConfig.java
 * 
 * Creado en Abril 17, 2020. 18:01.
 */
package conexion.recursos;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(conexion.filtro.AUFilter.class);
        resources.add(conexion.recursos.RecursoAccesoExpediente.class);
        resources.add(conexion.recursos.RecursoExpediente.class);
        resources.add(conexion.recursos.RecursoUsuario.class);
    }
    
}
