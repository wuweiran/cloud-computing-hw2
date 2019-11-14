package edu.nyu.cs9223;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import edu.nyu.cs9223.hw2.database.DbClient;
import edu.nyu.cs9223.hw2.database.DbClientFactory;
/*
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
*/


/**
 * @author 
 */
public class Validator {
    
    private static Validator instance = null; 
    private final DbClient dbClient;

    public Validator(DbClient dbClient) {
        this.dbClient = dbClient;
    }

    public static Validator getInstance(){
        if (instance == null) {
            DbClient client = DbClientFactory.getInstance();
            instance = new Validator(client); 
        }
  
        return instance;
    }
    public String validate(String userId, String OTP) {
        return "sorry, wrong OTP";
    }
    
}
