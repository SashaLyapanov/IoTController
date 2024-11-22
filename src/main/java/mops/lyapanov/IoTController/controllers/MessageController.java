package mops.lyapanov.IoTController.controllers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import mops.lyapanov.IoTController.models.Message;
import mops.lyapanov.IoTController.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("controller/general")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    public static final String EXCHANGE_NAME = "iot_exchange";
    public static final String QUEUE1_NAME = "iot_queue1";
    public static final String QUEUE2_NAME = "iot_queue2";
    public static final String ROUTING_KEY_1 = "queue1";
    public static final String ROUTING_KEY_2 = "queue2";

    private final AtomicInteger counter = new AtomicInteger(0);

    @PostMapping("/sendMessage")
    public void gettingMessages(@RequestBody List<Message> messages) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);

            channel.queueDeclare(QUEUE1_NAME, true, false, false, null);
            channel.queueDeclare(QUEUE2_NAME, true, false, false, null);

            channel.queueBind(QUEUE1_NAME, EXCHANGE_NAME, ROUTING_KEY_1);
            channel.queueBind(QUEUE2_NAME, EXCHANGE_NAME, ROUTING_KEY_2);

            for (Message message : messages) {
                String jsonMessages = messageService.serializeMessage(message);

//                String queueName = (counter.get() % 2 == 0) ? QUEUE1_NAME : QUEUE2_NAME;
                String routingKey = (counter.get() % 2 == 0) ? ROUTING_KEY_1 : ROUTING_KEY_2;


                channel.basicPublish(EXCHANGE_NAME, routingKey, null, jsonMessages.getBytes());
                System.out.println(" [x] Sent '" + jsonMessages + "'");
                counter.getAndIncrement();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
