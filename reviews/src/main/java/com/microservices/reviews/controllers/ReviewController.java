package com.microservices.reviews.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.reviews.domain.Review;
import com.microservices.reviews.dto.ReviewDto;
import com.microservices.reviews.repositories.ReviewRepository;
import com.microservices.reviews.services.ReviewService;

@RestController

public class ReviewController {
	
	
	@Autowired
	private ReviewRepository repo;
	
	@Autowired
	private ReviewService reviewService;
	
	
	
	@GetMapping("/users/{userId}/reviews")
	public Collection<Review> getReviewsByUserId(@PathVariable Long userId){
		return this.repo.findByUserId(userId);
	}
	
	@GetMapping("/movies/{movieId}/reviews")
	public Collection<Review> getReviewsByMovieId(@PathVariable Long movieId){
		return this.repo.findByMovieId(movieId);
	}
	
	@PostMapping("/review")
	public void postReviews(@RequestBody ReviewDto dto){
		this.reviewService.createReview(dto);
		
	}
}
