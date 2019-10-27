package com.benceborbely.demo.dynamodb.service.impl;

import com.benceborbely.demo.dynamodb.model.FavoriteMovies;
import com.benceborbely.demo.dynamodb.repository.FavoriteMoviesRepository;
import com.benceborbely.demo.dynamodb.service.FavoriteMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FavoriteMoviesServiceImpl implements FavoriteMoviesService {

    private FavoriteMoviesRepository favoriteMoviesRepository;

    @Autowired
    public FavoriteMoviesServiceImpl(FavoriteMoviesRepository favoriteMoviesRepository) {
        this.favoriteMoviesRepository = favoriteMoviesRepository;
    }

    @Override
    public FavoriteMovies save(FavoriteMovies favoriteMovies) {
        return favoriteMoviesRepository.save(favoriteMovies);
    }

    @Override
    public List<FavoriteMovies> getFavoriteMovies(String userId, long fromTs) {
        //return favoriteMoviesRepository.findByUserIdAndAddedTsInMsIsGreaterThanEqual(userId, fromTs);
        return new ArrayList<>();
    }

}
