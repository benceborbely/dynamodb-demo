package com.benceborbely.demo.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "favoriteMovies")
public class FavoriteMovies {

    @Id
    private FavoriteMoviesId favoriteMoviesId;

    private boolean isTrailerWatched;

    private String title;

    private String year;

    private String length;

    public FavoriteMovies() {
    }

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return favoriteMoviesId != null ? favoriteMoviesId.getUserId() : null;
    }

    public void setUserId(String userId) {
        if (favoriteMoviesId == null) {
            favoriteMoviesId = new FavoriteMoviesId();
        }
        favoriteMoviesId.setUserId(userId);
    }

    @DynamoDBRangeKey(attributeName = "addedTsInMs")
    public long getAddedTsInMs() {
        return favoriteMoviesId != null ? favoriteMoviesId.getAddedTsInMs() : null;
    }

    public void setAddedTsInMs(long addedTsInMs) {
        if (favoriteMoviesId == null) {
            favoriteMoviesId = new FavoriteMoviesId();
        }
        favoriteMoviesId.setAddedTsInMs(addedTsInMs);
    }

    public boolean isTrailerWatched() {
        return isTrailerWatched;
    }

    public void setTrailerWatched(boolean trailerWatched) {
        isTrailerWatched = trailerWatched;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

}
