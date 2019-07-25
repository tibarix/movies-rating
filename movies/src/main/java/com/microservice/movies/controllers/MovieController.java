package com.microservice.movies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.movies.dao.MoviesRepo;
import com.microservice.movies.domains.Movie;
import com.microservice.movies.services.MovieService;

@RestController("/")
@RefreshScope
public class MovieController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private MoviesRepo repo;
	
	@Value("${movies.max_fetch_value}")
	private int maxMoviewNumber;

	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> getMovies() {
		return new ResponseEntity<>(movieService.getMovies(), HttpStatus.OK);
	}

	@PostMapping("/movie")
	public ResponseEntity<Movie> postMovie(@RequestBody Movie movie) {
		return new ResponseEntity<>(repo.save(movie), HttpStatus.CREATED);
	}
	
	@GetMapping("/conf")
	public String getConfigValue() {
		return String.valueOf(maxMoviewNumber);
	}
}
