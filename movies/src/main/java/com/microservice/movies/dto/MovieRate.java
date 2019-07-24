package com.microservice.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
public class MovieRate{
	
	private Long movieId;
	
	private Double rate;
}