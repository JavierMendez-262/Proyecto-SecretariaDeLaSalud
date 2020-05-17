/*
 * ServerEndpointAnnotated.java
 * 
 * Creado en Abril 17, 2020. 19:16.
 */
package conexion.websockets;

import conexion.control.Control;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Clase que representa el Server Endpoint.
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
@ServerEndpoint("/websockets/expediente")
public class ServerEndpointAnnotated {

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
    private Control control = new Control(this);

    /**
     * Método que se ejecuta cuando se abre una conexión por parte de un
     * Cliente. Agrega la sesión conectada a una lista de sesiones.
     *
     * @param session Sesión que abrió la sesión.
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connection Opened...");
        clients.add(session);
    }

    /**
     * Método que se ejecuta al recibir un mensaje.
     *
     * @param message Mensaje recibido.
     * @param session Sesión remitente.
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("session id: " + session.getId());
        control.buscaExpediente(message, session.getId());
    }

    /**
     * Método que envía un mensaje a una sesión conectada al Server Endpoint.
     *
     * @param message Mensaje a enviar a la sesión.
     * @param sessionId Id de la sesión destinataria.
     */
    public void sendMessage(String message, String sessionId) {
        System.out.println("session id: " + sessionId);
        for (Session client : clients) {
            if (client.getId().equals(sessionId)) {
                try {
                    client.getBasicRemote().sendText(message != null ? message : "null");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * Método que se ejecuta cuando una conexión es cerrada. Remueve la sesión
     * del cliente desconectado de la lista de sesiones.
     *
     * @param session Sesión del cliente que cerró la conexión.
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("Connection Closed...");
        clients.remove(session);
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
}
