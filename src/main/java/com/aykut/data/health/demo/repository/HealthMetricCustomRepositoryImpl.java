package com.aykut.data.health.demo.repository;

import com.aykut.data.health.demo.domain.HealthMetric;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class HealthMetricCustomRepositoryImpl implements HealthMetricCustomRepository{

    @Override
    public List<HealthMetric> getLastNDays(int days) {
        //hack to make it today
        LocalDate today = LocalDate.of(2016, 5, 7);

        CodecRegistry pojoCodecRegistry = fromRegistries(com.mongodb.MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        MongoDatabase database = mongoClient.getDatabase("health-data");

        MongoCollection<HealthMetric> collection = database.getCollection("healthMetric", HealthMetric.class);

        Query query = new Query();
        query.addCriteria(Criteria.where("date").gt(today.minusDays(days)));
        ArrayList<HealthMetric> docs = collection.find(query.getQueryObject(), HealthMetric.class)
                .sort(new BasicDBObject("date", 1))
                .into(new ArrayList<>());

        return docs;
    }
}


