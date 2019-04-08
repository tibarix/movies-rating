package com.microservice.rating.services;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Sink.class)
public class RatingService {
	
	SubscribableChannel channel;
	
	public  RatingService(Sink sink) {
		this.channel = sink.input();
	}
	@StreamListener(Sink.INPUT)
	public void createRating(Object o) {
	}

}
