package src.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static src.others.Secret.MONGO_DB_URL_SECRET;

public class DBConfig {
    private static final ConnectionString MONGO_DB_URL = MONGO_DB_URL_SECRET;

    private static final MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(MONGO_DB_URL)
            .retryWrites(true)
            .build();
    public static final MongoClient mongoClient = MongoClients.create(settings);
    public static final MongoDatabase database = mongoClient.getDatabase("Asciax");
    public static final MongoCollection<Document> configCollection = database.getCollection("Config");
    public static final MongoCollection<Document> guildsCollection = database.getCollection("Guilds");
    public static final MongoCollection<Document> testCollection = database.getCollection("Test");
}
