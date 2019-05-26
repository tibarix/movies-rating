package com.microservice.rating;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	public static final String QUEUE_REVIEWS = "reviews-queue";
	public static final String EXCHANGE_REVIEWS = "reviews-exchange";

	@Bean
	Queue ordersQueue() {
		return QueueBuilder.durable(QUEUE_REVIEWS).build();
	}

	@Bean
	Exchange ordersExchange() {
		return ExchangeBuilder.topicExchange(EXCHANGE_REVIEWS).build();
	}

	@Bean
	Binding binding(Queue ordersQueue, TopicExchange ordersExchange) {
		return BindingBuilder.bind(ordersQueue).to(ordersExchange).with(QUEUE_REVIEWS);
	}
}