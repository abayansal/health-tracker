package com.aykut.data.health.demo.domain;

import com.aykut.data.health.demo.repository.HealthMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class MetricServiceImpl implements MetricService{

    @Autowired
    private HealthMetricRepository repository;

    public MetricServiceImpl(HealthMetricRepository repository){
        this.repository = repository;
    }

    @Override
    public List<HealthMetric> getLastNDays(int days) {
        return repository.getLastNDays(days);
    }

    @Override
    public List<MetricAverage> getAveragesFor(int days) {
        List<HealthMetric> metricList = repository.getLastNDays(days);
        List<MetricAverage> averages = new ArrayList<>();

        for (List<HealthMetric> metrics : splitIntoWeeklyChunks(metricList)){

            String firstDate = metrics.stream().min(Comparator.comparing(m -> m.date)).get().date.toString();
            String lastDate = metrics.stream().max(Comparator.comparing(m -> m.date)).get().date.toString();

            averages.add(new MetricAverage(metrics.stream().mapToDouble(x -> x.calories).average().getAsDouble(), firstDate + " " + lastDate));
        }

        return averages;
    }

    @Override
    public void saveAll(List<HealthMetric> metrics) {
        repository.saveAll(metrics);
    }

    private Collection<List<HealthMetric>> splitIntoWeeklyChunks(List<HealthMetric> healthMetrics){
        int chunkSize = 7;
        AtomicInteger counter = new AtomicInteger();

        Collection<List<HealthMetric>> result = healthMetrics.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
                .values();

        return result;
    }
}
