package com.benceborbely.demo.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "greeting")
public class Greeting {

    @DynamoDBHashKey(attributeName = "content")
    private String content;

    public Greeting() {}

    public Greeting(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
