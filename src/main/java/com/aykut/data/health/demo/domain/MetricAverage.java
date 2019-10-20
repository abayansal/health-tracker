package com.aykut.data.health.demo.domain;

public class MetricAverage {
    private Double average;
    private String week;

    public MetricAverage(Double average, String week) {
        this.average = average;
        this.week = week;
    }

    public Double getAverage() {
        return average;
    }

    public String getWeek() {
        return week;
    }
}
