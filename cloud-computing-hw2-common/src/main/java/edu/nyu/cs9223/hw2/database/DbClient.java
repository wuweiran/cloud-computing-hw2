package edu.nyu.cs9223.hw2.database;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wwrus
 */
public class DbClient {

    private final AmazonDynamoDB db;

    public DbClient(AmazonDynamoDB db) {
        this.db = db;
    }

    public Map<String, String> getItem(String tableName, String keyName, String keyValue) {
        HashMap<String, AttributeValue> key = new HashMap<>(1);
        key.put(keyName, new AttributeValue(keyValue));
        GetItemRequest request = new GetItemRequest()
                .withKey(key)
                .withTableName(tableName);
        Map<String, AttributeValue> item = db.getItem(request).getItem();
        Map<String, String> result = new HashMap<>(item.size());
        for (Map.Entry<String, AttributeValue> entry : item.entrySet()) {
            result.put(entry.getKey(), entry.getValue().toString());
        }
        return result;
    }

    public void putItem(String tableName, Map<String, String> item) {
        Map<String, AttributeValue> dbItem = new HashMap<>(item.size());
        for (Map.Entry<String, String> entry : item.entrySet()) {
            dbItem.put(entry.getKey(), new AttributeValue(entry.getValue()));
        }
        db.putItem(tableName, dbItem);
    }

    public void updateItem(String tableName, String keyName, String keyValue, Map<String, String> updateItem) {
        HashMap<String, AttributeValue> key = new HashMap<>(1);
        key.put(keyName, new AttributeValue(keyValue));
        Map<String, AttributeValueUpdate> dbItem = new HashMap<>(updateItem.size());
        for (Map.Entry<String, String> entry : updateItem.entrySet()) {
            dbItem.put(entry.getKey(), new AttributeValueUpdate(new AttributeValue(entry.getValue()), AttributeAction.PUT));
        }
        db.updateItem(tableName, key, dbItem);
    }


}
