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
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
@ServerEndpoint("/consultaExpediente")
public class ServerEndpointAnnotated {

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
    private Control control = new Control(this);

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connection Opened...");
        clients.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(message);

        String expedienteId = message;
        control.buscaExpediente(session.getId(), expedienteId);
        //Recibe ID, y envia a la session un JSON
    }

    public void sendMessage(String message, String sessionId) {
        for (Session client : clients) {
            if (client.getId().equals(sessionId)) {
                try {
                    client.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Connection Closed...");
        clients.remove(session);
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }
}
