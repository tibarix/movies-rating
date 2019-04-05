package com.microservice.movies.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.movies.domains.Movie;

@Repository
public interface MoviesRepo extends JpaRepository<Movie, Long>{
	
	

}
