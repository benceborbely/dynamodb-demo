package com.benceborbely.demo.dynamodb.dao;

import com.benceborbely.demo.dynamodb.model.FavoriteMovie;
import java.util.List;

public interface FavoriteMovieDao {

    FavoriteMovie save(FavoriteMovie favoriteMovie);

    List<FavoriteMovie> getFavoriteMovies(String userId, long fromTs, int publishYear);

}
