package com.microservice.movies.services;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@Value("${server.port}")
	private String port;

	@Value("${environments.url}")
	private String address;

	@Autowired
	RatingReader reader;

	private MovieMapper mapper = Selma.builder(MovieMapper.class).build();

	public List<MovieDto> getMovies() {
		List<Movie> movies = movieRespo.findAll();

		List<String> movieIdsCollections = movies.stream().map(Movie::getId).map(id -> id.toString())
				.collect(Collectors.toList());
		String movieIds = StringUtils.join(movieIdsCollections);

		Collection<MovieRate> rates = fetchRatings(movieIds);
		Map<String, String> map = rates.stream().collect(Collectors.toMap(MovieRate::getMovieId, MovieRate::getRate));
		return movies.stream().map(mapper::getMovieDto).map(dto -> {
			String mappedMovieId = map.get(String.valueOf(dto.getId()));
			if (mappedMovieId != null) {
				dto.setRate(Double.valueOf(mappedMovieId));
			}
			return dto;
		}).collect(Collectors.toList());
	}

	public List<MovieRate> fetchRatings(String movieIds) {
		return reader.read(movieIds);
	}

	

}
