package com.microservice.rating.controllers;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.rating.domains.Rating;
import com.microservice.rating.dto.MovieRates;
import com.microservice.rating.repositories.RatingRepository;
import com.microservice.rating.repositories.RatingViewRepository;

@RestController
public class RatingsController {

	@Autowired
	private RatingRepository ratingRepo;

	@Autowired
	private RatingViewRepository ratingViewRepo;

	@GetMapping("/all-ratings")
	public ResponseEntity<Collection<MovieRates>> getRatings(@RequestParam String movieIds) {
		Collection<Long> movieIdsSet = Stream.of(movieIds.split(",")).map(Long::valueOf).collect(Collectors.toSet());
		Collection<MovieRates> ratesByMovieIds = ratingViewRepo.findRatesByMovieIds(movieIdsSet);
		return ResponseEntity.ok(ratesByMovieIds);
	}

	@GetMapping("/ratings/{movieId}")
	public ResponseEntity<Collection<Rating>> getMovieRatings(@PathVariable Long movieId) {
		return ResponseEntity.ok(this.ratingRepo.findByMovieId(movieId));
	}

}
