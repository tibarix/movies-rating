package com.microservices.reviews.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.reviews.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

	
	Collection<Review> findByUserId(Long userId);
	
	Collection<Review> findByMovieId(Long movieId);
}
