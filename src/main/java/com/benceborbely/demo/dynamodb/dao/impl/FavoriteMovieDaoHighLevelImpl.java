package com.benceborbely.demo.dynamodb.dao.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.benceborbely.demo.dynamodb.dao.FavoriteMovieDao;
import com.benceborbely.demo.dynamodb.model.FavoriteMovie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMovieDaoHighLevelImpl implements FavoriteMovieDao {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    public FavoriteMovieDaoHighLevelImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public FavoriteMovie save(FavoriteMovie favoriteMovie) {
        dynamoDBMapper.save(favoriteMovie);
        return favoriteMovie;
    }

    @Override
    public List<FavoriteMovie> getFavoriteMovies(String userId, long fromTs, int publishYear) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":userId", new AttributeValue().withS(userId));
        eav.put(":fromTs", new AttributeValue().withN(String.valueOf(fromTs)));
        eav.put(":publishYear", new AttributeValue().withN(String.valueOf(publishYear)));

        DynamoDBQueryExpression<FavoriteMovie> queryExpression = new DynamoDBQueryExpression<FavoriteMovie>()
            .withKeyConditionExpression("userId = :userId and addedTsInMs > :fromTs")
            .withFilterExpression("publishYear = :publishYear")
            .withExpressionAttributeValues(eav);

        return dynamoDBMapper.query(FavoriteMovie.class, queryExpression);
    }

}
