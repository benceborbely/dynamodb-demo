package com.benceborbely.demo.dynamodb.service.impl;

import com.benceborbely.demo.dynamodb.model.Movie;
import com.benceborbely.demo.dynamodb.repository.MovieRepository;
import com.benceborbely.demo.dynamodb.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> findBy(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        Iterable<Movie> iterable = movieRepository.findAll();

        return StreamSupport
            .stream(iterable.spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteBy(String id) {
        movieRepository.deleteById(id);
    }

}
