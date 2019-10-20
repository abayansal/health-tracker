package com.aykut.data.health.demo.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class HealthMetric {

    //auto generated mongo id (_id)
    @Id
    private String id;

    public LocalDate date;
    public Integer calories;
    public Integer steps;
    public Double distance;
    public Integer floors;
    public Integer minutesSitting;
    public Integer minutesSlowActivity;
    public Integer minutesModerateActivity;
    public Integer minutesIntenseActivity;
    public Integer caloriesBurnedByActivity;

    public HealthMetric(
                        LocalDate date,
                        Integer calories,
                        Integer steps,
                        Double distance,
                        Integer floors,
                        Integer minutesSitting,
                        Integer minutesSlowActivity,
                        Integer minutesModerateActivity,
                        Integer minutesIntenseActivity,
                        Integer caloriesBurnedByActivity) {
        this.date = date;
        this.calories = calories;
        this.steps = steps;
        this.distance = distance;
        this.floors = floors;
        this.minutesSitting = minutesSitting;
        this.minutesSlowActivity = minutesSlowActivity;
        this.minutesModerateActivity = minutesModerateActivity;
        this.minutesIntenseActivity = minutesIntenseActivity;
        this.caloriesBurnedByActivity = caloriesBurnedByActivity;
    }

    public HealthMetric(){
    }
}
