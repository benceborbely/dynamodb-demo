package com.benceborbely.demo.dynamodb.service;

import com.benceborbely.demo.dynamodb.model.FavoriteMovie;
import java.util.List;

public interface FavoriteMovieService {

    FavoriteMovie save(FavoriteMovie favoriteMovie);

    List<FavoriteMovie> getFavoriteMovies(String userId, long fromTs);

}
