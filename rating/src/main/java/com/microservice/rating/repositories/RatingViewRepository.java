package com.microservice.rating.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.rating.domains.Rating;
import com.microservice.rating.dto.MovieRates;
@Repository
public interface RatingViewRepository extends JpaRepository<Rating, Long> {

	@Query("SELECT new com.microservice.rating.dto.MovieRates(movieId,  AVG(r.value)) FROM Rating r where r.movieId in (:movieIds) group BY r.movieId")
	public Collection<MovieRates>findRatesByMovieIds(@Param("movieIds") Collection<Long> movieIds);
}
