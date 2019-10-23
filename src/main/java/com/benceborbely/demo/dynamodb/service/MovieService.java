package com.benceborbely.demo.dynamodb.service;

import com.benceborbely.demo.dynamodb.model.Movie;

import java.util.Optional;

public interface MovieService {

    Movie save(Movie movie);

    Optional<Movie> findBy(String id);

    void deleteBy(String id);

}
