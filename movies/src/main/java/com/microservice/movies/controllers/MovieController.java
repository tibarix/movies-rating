package com.microservice.movies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.movies.dto.MovieDto;
import com.microservice.movies.services.MovieService;

@RestController("/")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/movies")
	public ResponseEntity<List<MovieDto>> getMovies() {
		return new ResponseEntity<>(movieService.getMovies(), HttpStatus.OK);
	}

}
