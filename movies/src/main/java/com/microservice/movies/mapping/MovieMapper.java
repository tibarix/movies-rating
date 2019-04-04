package com.microservice.movies.mapping;

import com.microservice.movies.domains.Movie;
import com.microservice.movies.dto.MovieDto;

import fr.xebia.extras.selma.Mapper;

@Mapper
public interface MovieMapper {
	
	MovieDto getMovieDto(Movie m);

}
