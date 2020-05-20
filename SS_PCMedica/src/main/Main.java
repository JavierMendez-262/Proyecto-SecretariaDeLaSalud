/*
 * Main.java
 * 
 * Documentado en Mayo 19, 2020. 23:59.
 */
package main;

import conexion.control.Control;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.DeploymentException;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Main {

    public static void main(String[] args) {

        Control control = new Control();

        try {
            control.getExpediente("asd", "kike", "1");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DeploymentException ex) {
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
