package com.benceborbely.demo.dynamodb.model;

public class Greeting {

    private final String content;

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}