package com.microservice.movies.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.movies.dao.MoviesRepo;
import com.microservice.movies.domains.Movie;
import com.microservice.movies.dto.MovieDto;
import com.microservice.movies.mapping.MovieMapper;

import fr.xebia.extras.selma.Selma;

@Service
public class MovieService {
	
	@Autowired
	private MoviesRepo movieRespo;
	
	private MovieMapper mapper = Selma.builder(MovieMapper.class).build();
	
	public List<MovieDto> getMovies(){
		List<Movie> movies = movieRespo.findAll();
		return movies.stream().map(mapper::getMovieDto).collect(Collectors.toList());
	}

}
