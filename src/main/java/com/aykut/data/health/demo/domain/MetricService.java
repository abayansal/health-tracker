package com.aykut.data.health.demo.domain;

import java.util.List;

public interface MetricService {

    List<HealthMetric> getLastNDays(int days);

    List<MetricAverage> getAveragesFor(int days);

    void saveAll(List<HealthMetric> metrics);
}

