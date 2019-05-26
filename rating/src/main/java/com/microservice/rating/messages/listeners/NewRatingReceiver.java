package com.microservice.rating.messages.listeners;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.rating.dto.MovieRates;
import com.microservice.rating.services.RatingService;

@Component
public class NewRatingReceiver implements CanReceiveMessage {

	@Autowired
	RatingService service;

	@Override
	public void receiveMessage(Object message) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			MovieRates ratingDto = mapper.readValue(message.toString(), MovieRates.class);
			service.createRating(ratingDto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}