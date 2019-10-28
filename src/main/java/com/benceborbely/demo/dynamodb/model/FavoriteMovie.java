package com.benceborbely.demo.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "favoriteMovies")
public class FavoriteMovie {

    @DynamoDBHashKey
    private String userId;

    @DynamoDBRangeKey
    private long addedTsInMs;

    private String title;

    private int publishYear;

    private int length;

}
