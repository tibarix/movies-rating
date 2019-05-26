package com.microservices.reviews.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.reviews.conf.RabbitConfig;
import com.microservices.reviews.domain.Review;
import com.microservices.reviews.dto.ReviewDto;
import com.microservices.reviews.repositories.ReviewRepository;

@Service
//@EnableBinding(Source.class)
public class ReviewService {

	@Autowired
	private ReviewRepository repo;

	@Autowired
	RabbitTemplate template;

	@Transactional
	public void createReview(ReviewDto reviewDto) throws JsonProcessingException {

		Review newReview = new Review();
		newReview.setMovieId(reviewDto.getMovieId());
		newReview.setText(reviewDto.getText());
		newReview.setUserId(0L);
		RatingDto ratingDto = new RatingDto(reviewDto.getMovieId(), 0L, reviewDto.getRate());
		repo.save(newReview);
		ObjectMapper mapper = new ObjectMapper();
		template.convertAndSend(RabbitConfig.EXCHANGE_REVIEWS, "reviews-queue",
				mapper.writeValueAsString(ratingDto));
		System.out.println("Sent");
		System.out.println(ratingDto);
	}

}
