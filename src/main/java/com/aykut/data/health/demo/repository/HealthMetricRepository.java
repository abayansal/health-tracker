package com.aykut.data.health.demo.repository;

import com.aykut.data.health.demo.domain.HealthMetric;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "health-metrics", path = "health-metrics")
public interface HealthMetricRepository extends MongoRepository<HealthMetric, String>, HealthMetricCustomRepository {

    @Override
    @RestResource(exported = false)
    void deleteById(String id);

    @Override
    @RestResource(exported = false)
    void delete(HealthMetric entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
