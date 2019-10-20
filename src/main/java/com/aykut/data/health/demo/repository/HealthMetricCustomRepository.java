package com.aykut.data.health.demo.repository;

import com.aykut.data.health.demo.domain.HealthMetric;

import java.util.List;

public interface HealthMetricCustomRepository{
    List<HealthMetric> getLastNDays(int days);
}
