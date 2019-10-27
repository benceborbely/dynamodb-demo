package com.benceborbely.demo.dynamodb.repository;

import com.benceborbely.demo.dynamodb.model.FavoriteMovies;
import com.benceborbely.demo.dynamodb.model.FavoriteMoviesId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteMoviesRepository extends CrudRepository<FavoriteMovies, FavoriteMoviesId> {



}
