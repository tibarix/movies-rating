package com.microservice.rating.messages.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.microservice.rating.services.RatingService;

//TODO  : @Component
public class EditRatingReceiver implements CanReceiveMessage {

	@Autowired
	RatingService service;

	@Override
	public void receiveMessage(Object message) {
		
	}

}
