package com.microservices.reviews.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.reviews.domain.Review;
import com.microservices.reviews.dto.ReviewDto;
import com.microservices.reviews.repositories.ReviewRepository;

import lombok.AllArgsConstructor;
import lombok.ToString;

@Service
@EnableBinding(Source.class)
public class ReviewService {

	@Autowired
	private ReviewRepository repo;

	MessageChannel messageChannel;

	public ReviewService(Source source) {
		this.messageChannel = source.output();
	}

	@Transactional
	public void createReview(ReviewDto reviewDto) {
		
		@AllArgsConstructor
		@ToString
		class RatingDto {
			Long movieId;
			Long userId;
			double rate;
		}
		
		Review newReview = new Review();
		newReview.setMovieId(reviewDto.getMovieId());
		newReview.setText(reviewDto.getText());
		newReview.setUserId(0L);
		RatingDto ratingDto = new RatingDto(reviewDto.getMovieId(), 0L, reviewDto.getRate());
		repo.save(newReview);
		Message<String> build = MessageBuilder.withPayload(ratingDto.toString()).build();
		this.messageChannel.send(build);
		System.out.println(ratingDto);
	}

}
