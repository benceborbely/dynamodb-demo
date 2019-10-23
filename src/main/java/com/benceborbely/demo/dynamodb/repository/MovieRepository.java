package com.benceborbely.demo.dynamodb.repository;

import com.benceborbely.demo.dynamodb.model.Movie;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface MovieRepository extends CrudRepository<Movie, String> {



}
