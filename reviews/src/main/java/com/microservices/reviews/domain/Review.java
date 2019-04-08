package com.microservices.reviews.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
public class Review {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long movieId;
	
	private Long userId;
	
	private String text;

}
