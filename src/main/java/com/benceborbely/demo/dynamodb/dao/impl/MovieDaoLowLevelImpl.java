package com.benceborbely.demo.dynamodb.dao.impl;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.benceborbely.demo.dynamodb.dao.MovieDao;
import com.benceborbely.demo.dynamodb.model.Movie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MovieDaoLowLevelImpl implements MovieDao {

    private static final String TABLE_NAME = "movies";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String YEAR = "year";
    private static final String LENGTH = "length";
    private static final String DIRECTOR = "director";
    private static final String AGE_LIMIT = "ageLimit";
    private static final String STAR_ACTORS = "starActors";

    private AmazonDynamoDB dynamoDBClient;

    @Autowired
    public MovieDaoLowLevelImpl(AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBClient = dynamoDBClient;
    }

    @Override
    public Movie save(Movie movie) {
        if (isBlank(movie.getId())) {
            movie.setId(UUID.randomUUID().toString());
        }

        PutItemRequest putItemRequest = new PutItemRequest()
            .withTableName(TABLE_NAME)
            .withItem(createItemFrom(movie));

         dynamoDBClient.putItem(putItemRequest);

         return movie;
    }

    @Override
    public Optional<Movie> findById(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(ID, new AttributeValue().withS(id));

        GetItemRequest getItemRequest = new GetItemRequest()
            .withTableName(TABLE_NAME)
            .withKey(key);

        Map<String, AttributeValue> item = dynamoDBClient.getItem(getItemRequest).getItem();

        return item != null
            ? Optional.of(createMovieFrom(item))
            : Optional.empty();
    }

    @Override
    public List<Movie> findAll() {
        ScanRequest scanRequest = new ScanRequest().withTableName(TABLE_NAME);

        List<Map<String, AttributeValue>> items = dynamoDBClient.scan(scanRequest).getItems();

        return items.stream().map(this::createMovieFrom).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        if (!findById(id).isPresent()) {
            return;
        }

        Map<String, AttributeValue> key = new HashMap<>();
        key.put(ID, new AttributeValue().withS(id));

        DeleteItemRequest deleteItemRequest = new DeleteItemRequest()
            .withTableName(TABLE_NAME)
            .withKey(key);

        dynamoDBClient.deleteItem(deleteItemRequest);
    }

    private Map<String, AttributeValue> createItemFrom(Movie movie) {
        List<AttributeValue> starActors = movie
            .getStarActors()
            .stream()
            .map(starActor -> new AttributeValue().withS(starActor))
            .collect(Collectors.toList());

        Map<String, AttributeValue> item = new HashMap<>();
        item.put(ID, new AttributeValue().withS(String.valueOf(movie.getId())));
        item.put(TITLE, new AttributeValue().withS(movie.getTitle()));
        item.put(YEAR, new AttributeValue().withN(String.valueOf(movie.getYear())));
        item.put(LENGTH, new AttributeValue().withN(String.valueOf(movie.getLength())));
        item.put(DIRECTOR, new AttributeValue().withS(movie.getDirector()));
        item.put(AGE_LIMIT, new AttributeValue().withN(String.valueOf(movie.getAgeLimit())));
        item.put(STAR_ACTORS, new AttributeValue().withL(starActors));
        return item;
    }

    private Movie createMovieFrom(Map<String, AttributeValue> item) {
        List<String> starActors = item
            .get(STAR_ACTORS)
            .getL()
            .stream()
            .map(AttributeValue::getS)
            .collect(Collectors.toList());

        return Movie
            .builder()
            .id(item.get(ID).getS())
            .title(item.get(TITLE).getS())
            .year(Integer.valueOf(item.get(YEAR).getN()))
            .length(Long.valueOf(item.get(LENGTH).getN()))
            .director(item.get(DIRECTOR).getS())
            .ageLimit(Integer.valueOf(item.get(AGE_LIMIT).getN()))
            .starActors(starActors)
            .build();
    }

}
