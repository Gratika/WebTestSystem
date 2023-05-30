package com.testsystem.back_java.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    public static final String BACK_CATEGORYDTO_QUEUE ="back_test_queue";
    public static final String BACK_CATEGORYDTO_EXCHANGE ="back_test_exchange";
    public static final String GET_CATEGORYDTO_KEY ="back_test_routingKey";
    public static final String FRONT_QUEUE ="back_test_queue";
    public static final String FRONT_EXCHANGE ="back_test_exchange";
    public static final String FRONT_ROUTING_KEY ="back_test_routingKey";
    @Bean
    public Queue queue(){
        return new Queue(BACK_CATEGORYDTO_QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(BACK_CATEGORYDTO_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
       return BindingBuilder.bind(queue).to(exchange).with(GET_CATEGORYDTO_KEY);
    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
