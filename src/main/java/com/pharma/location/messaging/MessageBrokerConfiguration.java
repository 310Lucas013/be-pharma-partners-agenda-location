package com.pharma.location.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBrokerConfiguration {

    @Value("${rabbitmq.queue}")
    private String queueName;
    @Value("${rabbitmq.routingKey}")
    private String routingKey;


    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean("create-appointment")
    public Queue createAppointmentQueue() {
        return new Queue("create-appointment");
    }

    @Bean("update-appointment")
    public Queue updateAppointmentQueue() {
        return new Queue("update-appointment");
    }

    @Bean
    Binding createAccountBinding(@Qualifier("create-appointment") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("create-appointment");
    }

    @Bean
    Binding updateAccounttBinding(@Qualifier("update-appointment") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("update-appointment");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
