package com.microservices.reviews.conf;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig{
    public static final String QUEUE_REVIEWS = "reviews-queue";
    public static final String EXCHANGE_REVIEWS = "reviews-exchange";
    public static final String EXCHANGE_REVIEWS_PATTERN = "reviews-exchange-#";
 
    @Bean
    Queue queue() {
        return QueueBuilder.durable(QUEUE_REVIEWS).build();
    }
 
    @Bean
    Exchange exchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_REVIEWS).build();
    }
 
    @Bean
    Binding binding(Queue ordersQueue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(ordersQueue).to(ordersExchange).with(EXCHANGE_REVIEWS_PATTERN);
    }
}