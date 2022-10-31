package io.github.perftool.trace.report.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.perftool.trace.module.TraceBean;
import io.github.perftool.trace.report.ITraceReporter;
import org.bson.Document;

import java.util.ArrayList;

public class MongoTraceReporter implements ITraceReporter {

    private final MongoConfig mongoConfig;

    private final MongoDatabase database;

    private final MongoCollection<Document> collection;

    public MongoTraceReporter(MongoConfig mongoConfig) {
        this.mongoConfig = mongoConfig;
        if ("".equalsIgnoreCase(this.mongoConfig.getPassword())
                || "".equalsIgnoreCase(this.mongoConfig.getUsername())) {
            MongoClient mongoClient = new MongoClient(this.mongoConfig.getHost(), this.mongoConfig.getPort());
            this.database = mongoClient.getDatabase(this.mongoConfig.getDatabaseName());
        } else {
            ServerAddress addr = new ServerAddress(this.mongoConfig.getHost(), this.mongoConfig.getPort());
            ArrayList<ServerAddress> addresses = new ArrayList<>();
            addresses.add(addr);
            MongoCredential credential = MongoCredential.createScramSha1Credential(this.mongoConfig.getUsername(),
                    this.mongoConfig.getDatabaseName(), this.mongoConfig.getPassword().toCharArray());
            MongoClientOptions options = MongoClientOptions.builder().build();
            MongoClient mongoClient = new MongoClient(addresses, credential, options);
            this.database = mongoClient.getDatabase(this.mongoConfig.getDatabaseName());
        }
        this.database.createCollection(this.mongoConfig.getCollectionName());
        this.collection = database.getCollection(this.mongoConfig.getCollectionName());
    }

    @Override
    public void reportTrace(TraceBean traceBean) {
        Document document = new Document("traceId", traceBean.getTraceId())
                .append("spanInfo", traceBean.getSpanInfo());
        collection.insertOne(document);
    }
}
