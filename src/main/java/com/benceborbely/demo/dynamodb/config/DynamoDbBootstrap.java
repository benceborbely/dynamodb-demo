package com.benceborbely.demo.dynamodb.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.benceborbely.demo.dynamodb.model.Movie;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        createMoviesTable();
        createFavoriteMoviesTable();
    }

    private void createMoviesTable() {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(Movie.class);
        createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(2L, 2L));

        client.createTable(createTableRequest);
    }

    private void createFavoriteMoviesTable() {
        List<AttributeDefinition> attributeDefinitions = Stream.of(
            new AttributeDefinition("userId", "S"),
            new AttributeDefinition("addedTsInMs", "N")
        ).collect(Collectors.toList());

        CreateTableRequest createTableRequest = new CreateTableRequest()
            .withTableName("favoriteMovies")
            .withAttributeDefinitions(attributeDefinitions)
            .withKeySchema(new KeySchemaElement("userId", "HASH"))
            .withKeySchema(new KeySchemaElement("addedTsInMs", "RANGE"))
            .withProvisionedThroughput(new ProvisionedThroughput(2L, 2L));

        client.createTable(createTableRequest);
    }

}
