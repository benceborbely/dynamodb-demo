package com.benceborbely.demo.dynamodb.model;

import lombok.Data;

@Data
public class FavoriteMoviesRequest {

    private String userId;

    private long addedTsInMs;

    private boolean isTrailerWatched;

    private String title;

    private String year;

    private String length;

}
