package com.microservice.movies.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.movies.dao.MoviesRepo;
import com.microservice.movies.domains.Movie;
import com.microservice.movies.dto.MovieDto;
import com.microservice.movies.dto.MovieRate;
import com.microservice.movies.feign.RatingReader;
import com.microservice.movies.mapping.MovieMapper;

import fr.xebia.extras.selma.Selma;

@Service
public class MovieService {

	@Autowired
	private MoviesRepo movieRespo;

	@Autowired
	RatingReader ratingsReader;

	private MovieMapper mapper = Selma.builder(MovieMapper.class).build();

	public List<MovieDto> getMovies() {
		List<Movie> movies = movieRespo.findAll();

		List<String> movieIdsCollections = movies.stream().map(Movie::getId).map(id -> id.toString())
				.collect(Collectors.toList());
		String movieIds = StringUtils.join(movieIdsCollections);

		Collection<MovieRate> ratings = ratingsReader.read(movieIds);
		Map<Long, Double> map = ratings.stream().collect(Collectors.toMap(MovieRate::getMovieId, MovieRate::getRate));
		return movies.stream().map(mapper::getMovieDto).map(dto -> {
			dto.setRate(map.get(dto.getId()));
			return dto;
		}).collect(Collectors.toList());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public MovieDto createMovie(MovieDto movieDto) {
		Movie movie = this.mapper.getMovie(movieDto);
		movie = this.movieRespo.save(movie);
		movieDto.setId(movie.getId());
		return movieDto;
	}
	

	

}
