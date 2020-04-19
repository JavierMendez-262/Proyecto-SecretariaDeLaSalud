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
public class MainProducer {

    private final String QUEUE_NAME = "bdremoto";
    private Control control;

    public MainProducer(Control control) {
        this.control = control;
    }
    
    public void produceMessage(String sessionId, String expedienteId) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            
            //Envia una solicitud de un expediente a la BD Remota en caso de no encontrarse en la BD Local
            
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("[x] Received Json: \n" + message);
                
                //Consume los expedientes solicitados a la BD Remota
                //La sesionId se destinataria viene en la variable message consumida
                
                control.enviaExpediente(sessionId, expedienteId);
                                
            };
            
            channel.basicPublish("", QUEUE_NAME, null, expedienteId.getBytes());
            System.out.println("[*] Sent Json: \n" + expedienteId);
            
            
        } catch (IOException ex) {
            Logger.getLogger(MainProducer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(MainProducer.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}