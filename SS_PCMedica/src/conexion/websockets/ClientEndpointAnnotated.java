/*
 * ClientEndpointAnnotated.java
 * 
 * Documentado en Mayo 15, 10:09.
 */
package conexion.websockets;

import conexion.control.Control;
import java.io.IOException;
import java.util.ArrayList;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 * Clase que representa el Cliente Endpoint, y realiza una conexión a través de
 * WebSockets al servidor.
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
@ClientEndpoint
public class ClientEndpointAnnotated {

    private Control control;
    private Session serverSession;

    public ClientEndpointAnnotated(Control control) {
        this.control = control;
    }
    
    /**
     * Método que se ejecuta al abrir una conexión con el servidor.
     *
     * @param session Session que abrió la conexión.
     */
    @OnOpen
    public void onOpen(Session session) {
        this.serverSession = session;
    }

    /**
     * Método que se ejecuta al recibir un mensaje de parte del servidor.
     *
     * @param message Message recivido.
     */
    @OnMessage
    public void onMessage(String message) {
        control.receivedExpedienteWS(message);
    }

    /**
     * Método que imprime un error en consola.
     *
     * @param e Error producido.
     */
    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    /**
     * Método que envía un mensaje/solicitud al servidor.
     *
     * @param message Message a enviar al servidor.
     * @throws IOException cuando se produzca un error con la solicitud.
     */
    public void sendMessage(String message) throws IOException {
        serverSession.getBasicRemote().sendText(message);
    }
}
