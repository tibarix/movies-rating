package com.microservice.movies.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.microservice.movies.dao.MoviesRepo;
import com.microservice.movies.domains.Movie;
import com.microservice.movies.dto.MovieRate;
import com.microservice.movies.feign.RatingReader;

@Service
public class MovieService {

	@Autowired
	private MoviesRepo movieRespo;

	@Autowired
	RatingReader ratingsReader;
	
	@Value("${movies.max_fetch_value:10}")
	private int maxMoviewNumber;


	public List<Movie> getMovies() {
		List<Movie> movies = movieRespo.findAll();

		List<String> movieIdsCollections = movies.stream().map(Movie::getId).map(id -> id.toString())
				.collect(Collectors.toList());
		String movieIds = StringUtils.join(movieIdsCollections);

		Collection<MovieRate> ratings = ratingsReader.read(movieIds);
		Map<Long, Double> map = ratings.stream().collect(Collectors.toMap(MovieRate::getMovieId, MovieRate::getRate));
		return movies
				.stream()
				.limit(maxMoviewNumber)
				.map(movie -> {
					movie.setRate(map.get(movie.getId()));
					return movie;
				})
				.collect(Collectors.toList());
	}

}
