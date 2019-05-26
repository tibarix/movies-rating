package com.microservice.rating.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.rating.domains.Rating;
import com.microservice.rating.dto.MovieRates;
import com.microservice.rating.repositories.RatingRepository;

@Service
public class RatingService {

	@Autowired
	RatingRepository repo;

	@Transactional
	public void createRating(MovieRates o) {
		Rating r = new Rating();
		r.setMovieId(o.getMovieId());
		r.setValue(o.getRate());
		repo.save(r);
	}

}
