package edu.nyu.cs9223.hw2.database;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import edu.nyu.cs9223.hw2.notification.SnsClient;

public class DbClientFactory {
    private volatile static DbClient dbClient;

    private DbClientFactory() {
    }

    public static DbClient getInstance() {
        if (dbClient == null) {
            synchronized (DbClient.class) {
                if (dbClient == null) {
                    dbClient = buildDynamoDBClient();
                }
            }
        }
        return dbClient;
    }

    private static DbClient buildDynamoDBClient() {
        final AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
        return new DbClient(db);
    }
}
