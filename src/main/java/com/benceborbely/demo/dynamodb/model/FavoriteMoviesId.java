package com.benceborbely.demo.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

import java.io.Serializable;

@DynamoDBDocument
public class FavoriteMoviesId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private long addedTsInMs;

    public FavoriteMoviesId() {
    }

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "addedTsInMs")
    public long getAddedTsInMs() {
        return addedTsInMs;
    }

    public void setAddedTsInMs(long addedTsInMs) {
        this.addedTsInMs = addedTsInMs;
    }

}
