package br.com.videoprocessing.application.infra;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_QUEUE_NAME = "order_videoprocessing_direct_queue";
    public static final String videoprocessing_QUEUE_NAME = "videoprocessing_direct_queue";
    public static final String videoprocessing_EXCHANGE_NAME = "videoprocessing_direct_exchange";
    public static final String videoprocessing_KEY_NAME = "videoprocessing_direct_key";

    @Bean
    public Queue videoprocessingQueue() {
        return new Queue(videoprocessing_QUEUE_NAME);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(videoprocessing_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue videoprocessingQueue, DirectExchange exchange) {
        return BindingBuilder.bind(videoprocessingQueue).to(exchange).with(videoprocessing_KEY_NAME);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
