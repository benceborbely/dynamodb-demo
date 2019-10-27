package com.benceborbely.demo.dynamodb.dao;

import com.benceborbely.demo.dynamodb.model.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieDao {

    Movie save(Movie movie);

    Optional<Movie> findById(String id);

    List<Movie> findAll();

    void deleteById(String id);

}
