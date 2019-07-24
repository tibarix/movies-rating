package com.microservice.movies.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.microservice.movies.domains.Movie;

@RepositoryRestResource(path="movies")
public interface MoviesRepo extends JpaRepository<Movie, Long>{}
