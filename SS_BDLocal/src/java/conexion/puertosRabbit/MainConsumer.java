/*
 * MainProducer.java
 * 
 * Creado en Abril 18, 2020. 19:34.
 */
package conexion.puertosRabbit;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import conexion.control.Control;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class MainConsumer {

    private final String QUEUE_NAME = "LOCAL_DATABASE";
    private Control control;

    public MainConsumer(Control control) {
        this.control = control;
    }
    
    public void consumeMessage() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("[x] Received Json: \n" + message);
                
                //Consume los expedientes solicitados a la BD Remota
                //La sesionId del destinatario viene en la variable message consumida
                
                control.enviaExpediente(message, "2");
                                
            }; 
            
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
            
        } catch (IOException ex) {
            Logger.getLogger(MainProducer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(MainProducer.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
