package com.benceborbely.demo.dynamodb.model;

import lombok.Data;

import java.util.List;

@Data
public class MovieRequest {

    private String title;

    private int year;

    private long length;

    private String director;

    private int ageLimit;

    private List<String> starActors;

}
