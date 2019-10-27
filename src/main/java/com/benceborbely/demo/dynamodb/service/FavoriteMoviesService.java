package com.benceborbely.demo.dynamodb.service;

import com.benceborbely.demo.dynamodb.model.FavoriteMovies;

import java.util.List;

public interface FavoriteMoviesService {

    FavoriteMovies save(FavoriteMovies favoriteMovies);

    List<FavoriteMovies> getFavoriteMovies(String userId, long fromTs);

}
