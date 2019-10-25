package com.benceborbely.demo.dynamodb.dao.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.benceborbely.demo.dynamodb.dao.MovieDao;
import com.benceborbely.demo.dynamodb.model.Movie;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieDaoImpl implements MovieDao {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    public MovieDaoImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Movie save(Movie movie) {
        dynamoDBMapper.save(movie);
        return movie;
    }

    @Override
    public Optional<Movie> findById(String id) {
        return Optional.ofNullable(dynamoDBMapper.load(Movie.class, id));
    }

    @Override
    public List<Movie> findAll() {
        return dynamoDBMapper.scan(Movie.class, new DynamoDBScanExpression());
    }

    @Override
    public void deleteById(String id) {
        Optional<Movie> movie = this.findById(id);

        if (movie.isPresent()) {
            dynamoDBMapper.delete(movie);
        }
    }

}
