/*
 * Main.java
 * 
 * Documentado en Mayo 19, 2020. 23:59.
 */
package main;

import conexion.control.Control;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Main {

    public static void main(String[] args) {

        Control control = new Control("MendozaHiguera", "kike");

        try {
            control.getExpediente("4", "1");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                Thread.sleep(7 * 1000);
                break;
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}