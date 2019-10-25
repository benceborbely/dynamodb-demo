package com.benceborbely.demo.dynamodb.service;

import com.benceborbely.demo.dynamodb.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Movie save(Movie movie);

    Optional<Movie> findBy(String id);

    List<Movie> findAll();

    void deleteBy(String id);

}
