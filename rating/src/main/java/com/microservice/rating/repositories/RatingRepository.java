package com.microservice.rating.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.microservice.rating.domains.Rating;

@RepositoryRestResource
public interface RatingRepository extends JpaRepository<Rating, Long> {

	@RestResource
	Collection<Rating> findByMovieId(Long movieId);

	@Query("SELECT AVG(value) as rate from Rating r where r.movieId=:id")
	double getMovieRate(@Param("id") Long movieId);

}
