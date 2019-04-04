package com.microservice.movies.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.microservice.movies.domains.Movie;

@Component
public interface MoviesRepo extends JpaRepository<Movie, Long>{
	

}
