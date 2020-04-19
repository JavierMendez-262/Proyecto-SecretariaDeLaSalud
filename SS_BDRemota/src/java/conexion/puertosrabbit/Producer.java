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

    private final String QUEUE_NAME = "bdlocal";

    public void produceMessage(Expediente expediente) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Generando mensaje utilizando Json.
            String toSend = "";
            String content = "";
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            content = gson.toJson(expediente);
            int i = 0; 
            while (true) {
                i++; // Al probar checar que se envié solamente una vez y no constantemente
                toSend = "New message " + i + " " + content;
                channel.basicPublish("", QUEUE_NAME, null, toSend.getBytes());
                System.out.println("Message from [Producer] sent: " + '\n' + toSend);
                Thread.sleep(1000);
            }
            //String mensajeAEnviar = "";
            //Envia una solicitud de un expediente a la BD Remota en caso de no encontrarse en la BD Local
            //channel.basicPublish("", QUEUE_NAME, null, mensajeAEnviar.getBytes());
            //System.out.println("[*] Sent Json: \n" + mensajeAEnviar);
        } catch (IOException | TimeoutException | InterruptedException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
