package com.benceborbely.demo.dynamodb.dao.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.benceborbely.demo.dynamodb.dao.FavoriteMovieDao;
import com.benceborbely.demo.dynamodb.model.FavoriteMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Component
public class FavoriteMovieDaoLowLevelImpl implements FavoriteMovieDao {

    private static final String USER_ID = "userId";
    private static final String ADDED_TS_IN_MS = "addedTsInMs";
    private static final String TITLE = "title";
    private static final String PUBLISH_YEAR = "publishYear";
    private static final String LENGTH = "length";

    private AmazonDynamoDB dynamoDBClient;

    @Autowired
    public FavoriteMovieDaoLowLevelImpl(AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBClient = dynamoDBClient;
    }

    @Override
    public FavoriteMovie save(FavoriteMovie favoriteMovie) {
        PutItemRequest putItemRequest = new PutItemRequest()
                .withTableName("favoriteMovies")
                .withItem(createItemFrom(favoriteMovie));

        dynamoDBClient.putItem(putItemRequest);

        return favoriteMovie;
    }

    @Override
    public List<FavoriteMovie> getFavoriteMovies(String userId, long fromTs, int publishYear) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":userId", new AttributeValue().withS(userId));
        eav.put(":fromTs", new AttributeValue().withN(String.valueOf(fromTs)));
        eav.put(":publishYear", new AttributeValue().withN(String.valueOf(publishYear)));

        QueryRequest queryRequest = new QueryRequest()
                .withTableName("favoriteMovies")
                .withKeyConditionExpression("userId = :userId and addedTsInMs > :fromTs")
                .withFilterExpression("publishYear = :publishYear")
                .withExpressionAttributeValues(eav);

        List<Map<String, AttributeValue>> items = dynamoDBClient.query(queryRequest).getItems();

        return items.stream().map(this::createFavoriteMovieFrom).collect(Collectors.toList());
    }

    private Map<String, AttributeValue> createItemFrom(FavoriteMovie favoriteMovie) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(USER_ID, new AttributeValue().withS(favoriteMovie.getUserId()));
        item.put(ADDED_TS_IN_MS, new AttributeValue().withN(String.valueOf(favoriteMovie.getAddedTsInMs())));
        item.put(TITLE, new AttributeValue().withS(favoriteMovie.getTitle()));
        item.put(PUBLISH_YEAR, new AttributeValue().withN(String.valueOf(favoriteMovie.getPublishYear())));
        item.put(LENGTH, new AttributeValue().withN(String.valueOf(favoriteMovie.getLength())));
        return item;
    }

    private FavoriteMovie createFavoriteMovieFrom(Map<String, AttributeValue> item) {
        return FavoriteMovie
                .builder()
                .userId(item.get(USER_ID).getS())
                .addedTsInMs(Long.valueOf(item.get(ADDED_TS_IN_MS).getN()))
                .title(item.get(TITLE).getS())
                .publishYear(Integer.valueOf(item.get(PUBLISH_YEAR).getN()))
                .length(Integer.valueOf(item.get(LENGTH).getN()))
                .build();
    }

}
