/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion.websockets;

import java.io.IOException;
import java.util.ArrayList;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author Usuario
 */
@ClientEndpoint
public class ClientEndpointAnnotated {

    private Session serverSession;
    private ArrayList<String> receivedMessages = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        this.serverSession = session;
    }

    @OnMessage
    public void onMessage(String message) {
        //receivedMessages.add(message);
        System.out.println(message);
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    public void sendMessage(String message) {
        try {
            serverSession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
