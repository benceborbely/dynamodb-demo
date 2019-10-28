package com.benceborbely.demo.dynamodb.service.impl;

import com.benceborbely.demo.dynamodb.dao.FavoriteMovieDao;
import com.benceborbely.demo.dynamodb.model.FavoriteMovie;
import com.benceborbely.demo.dynamodb.service.FavoriteMovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    private FavoriteMovieDao favoriteMovieDao;

    @Autowired
    public FavoriteMovieServiceImpl(FavoriteMovieDao favoriteMovieDao) {
        this.favoriteMovieDao = favoriteMovieDao;
    }

    @Override
    public FavoriteMovie save(FavoriteMovie favoriteMovie) {
        return favoriteMovieDao.save(favoriteMovie);
    }

    @Override
    public List<FavoriteMovie> getFavoriteMovies(String userId, long fromTs, int publishYear) {
        return favoriteMovieDao.getFavoriteMovies(userId, fromTs, publishYear);
    }

}
