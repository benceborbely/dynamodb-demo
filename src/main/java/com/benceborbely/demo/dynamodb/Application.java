package com.benceborbely.demo.dynamodb;

import com.benceborbely.demo.dynamodb.repository.FavoriteMoviesRepository;
import com.benceborbely.demo.dynamodb.repository.MovieRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableDynamoDBRepositories(includeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MovieRepository.class, FavoriteMoviesRepository.class})
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
