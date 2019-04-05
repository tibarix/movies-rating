package com.microservice.movies.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.movies.dto.MovieRate;
import com.microservice.movies.feign.fallbacks.FallBackRatings;

@FeignClient(name = "rating-service", fallback = FallBackRatings.class)
public interface RatingReader {
	@RequestMapping(method = RequestMethod.GET, value = "/all-ratings")
	List<MovieRate> read(@RequestParam("movieIds") String movieIds);
}
