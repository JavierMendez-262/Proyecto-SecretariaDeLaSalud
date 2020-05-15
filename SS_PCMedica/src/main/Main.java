/*
 * Main.java
 */
package main;

import conexion.control.Control;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class Main {

    public static void main(String[] args) {
        
        Control control = new Control();
        
        control.getExpediente("002");
        
        while(true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}