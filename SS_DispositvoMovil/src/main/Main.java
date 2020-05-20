/*
 * Main.java
 * 
 * Documentado en Mayo 19, 2020. 23:59.
 */
package main;

import conexion.control.Control;
import negocio.AccesoExpediente;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Main {
    
    public static void main(String[] args) {
        Control ctrl = new Control("MendozaHiguera", "kike");
        for (AccesoExpediente accesoExpediente : ctrl.getAccesoExpedientes("1")) {
            System.out.println(accesoExpediente.getIdExpediente() + " " + accesoExpediente.getIdMedico() + " " + accesoExpediente.estaAutorizado());
        }
        ctrl.autorizarAccesoExpediente("1", "4");
        for (AccesoExpediente accesoExpediente : ctrl.getAccesoExpedientes("1")) {
            System.out.println(accesoExpediente.getIdExpediente() + " " + accesoExpediente.getIdMedico() + " " + accesoExpediente.estaAutorizado());
        }
    }
}
