package com.microservice.movies.feign.fallbacks;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.microservice.movies.dto.MovieRate;
import com.microservice.movies.feign.RatingReader;

@Component
public class FallBackRatings implements RatingReader {

	@Override
	public List<MovieRate> read(String movieIds) {
		return Collections.emptyList();
	}

}
