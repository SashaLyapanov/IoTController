package mops.lyapanov.IoTController.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "iot_exchange";
    public static final String QUEUE1_NAME = "iot_queue1";
    public static final String QUEUE2_NAME = "iot_queue2";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE1_NAME);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE2_NAME);
    }

}
