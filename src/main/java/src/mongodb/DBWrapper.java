package src.mongodb;

import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

import static src.mongodb.DBConfig.testCollection;

public class DBWrapper {

    public static InsertOneResult createTestObject(String key, Object value) {
        Document document = new Document().append(key, value);
        return testCollection.insertOne(document);
    }

}
