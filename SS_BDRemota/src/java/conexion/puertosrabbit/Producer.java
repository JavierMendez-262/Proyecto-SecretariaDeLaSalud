/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion.puertosrabbit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosnegocio.Expediente;

/**
 *
 * @author Usuario
 */
public class Producer {

    private final String QUEUE_NAME = "LOCAL_DATABASE";

    public Producer() {
    }
    
    public void produceMessage(Expediente expediente) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String content = gson.toJson(expediente);
            
            // Al probar checar que se envié solamente una vez y no constantemente
            
            channel.basicPublish("", QUEUE_NAME, null, content.getBytes());
            System.out.println("[*] Sent Json: " + '\n' + content);

        } catch (IOException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
