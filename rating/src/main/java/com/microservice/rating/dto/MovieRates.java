package com.microservice.rating.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
public class MovieRates {
	
	private Long movieId;
	
	private Double rate;
}
