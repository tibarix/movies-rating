package com.microservices.reviews.services;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter @Setter
class RatingDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4329039727801256062L;
	private Long movieId;
	private Long userId;
	private double rate;
}