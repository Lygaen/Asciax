package fr.lygaen.asciax.src.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

import static fr.lygaen.asciax.src.mongodb.DBConfig.testCollection;

public class DBWrapper {

    public static InsertOneResult createTestObject(String key, Object value) {
        Document document = new Document().append(key, value);
        return testCollection.insertOne(document);
    }

    public static Document findByKey(MongoCollection<Document> collection, String key, Object value) {
        MongoCursor<Document> cursor = collection.find().cursor();
        while(cursor.hasNext()) {
            Document current = cursor.next();
            if(current.get(key).equals(value)) return current;
        }
        return null;
    }

}
