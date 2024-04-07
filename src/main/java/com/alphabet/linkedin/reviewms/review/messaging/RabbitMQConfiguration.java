package com.alphabet.linkedin.reviewms.review.messaging;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This particular class will be allowing us to define the beans &
 * it will be also used to configure and configure RabbitMQ messaging.
 *
 * Here we are going to define 3 definitions
 * 1. To create a queue
 * 2. For message conversion, so it will convert the message into JSON format.
 *    So we'll be serializing and deserializing the message.
 * 3. gives an instance of RabbitTemplate i.e; HelperClass
 *
 */

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue companyRatingQueue(){
        return new Queue("companyRatingQueue");
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        /**
         * This will be used to serialize and deserialize the messages.
         * Jackson is a popular library for handling JSON in java,
         * to convert messages to and from JSON format.
         */


        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        /**
         * It is a helper class, it handles the creation and releases of resources,
         * when sending messages to (or) receiving messages from RabbitMQ.
         */
        RabbitTemplate x = new RabbitTemplate(connectionFactory);
         x.setMessageConverter(jsonMessageConverter());
         return x;
    }

}
