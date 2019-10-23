package com.benceborbely.demo.dynamodb.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.benceborbely.demo.dynamodb.model.Greeting;
import com.benceborbely.demo.dynamodb.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DynamoDbBootstrap implements ApplicationListener<ApplicationReadyEvent>  {

    private DynamoDBMapper dynamoDBMapper;

    private AmazonDynamoDB client;

    @Autowired
    public DynamoDbBootstrap(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB client) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.client = client;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(Greeting.class);
        createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(2L, 2L));

        client.createTable(createTableRequest);

        dynamoDBMapper.save(new Greeting("Bence"));

        Greeting greeting = dynamoDBMapper.load(Greeting.class, "Bence");
        System.out.println(greeting.getContent());

        createMoviesTable();
    }

    private void createMoviesTable() {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(Movie.class);
        createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(2L, 2L));
        client.createTable(createTableRequest);
    }

}
