package com.benceborbely.demo.dynamodb.model;

import java.util.List;

public class MovieRequest {

    private String title;

    private int year;

    private long length;

    private String director;

    private int ageLimit;

    private List<String> starActors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public List<String> getStarActors() {
        return starActors;
    }

    public void setStarActors(List<String> starActors) {
        this.starActors = starActors;
    }
}
