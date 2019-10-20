package com.aykut.data.health.demo.domain;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class CsvItemParser {

    public HealthMetric parseItem(String line) {
        Map<Integer, String> fieldValues = getRawFieldValues(line);

        LocalDate date = LocalDate.parse(fieldValues.get(0), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Integer calories = Integer.valueOf(fieldValues.get(1).replace(".", ""));
        Integer steps = Integer.valueOf(fieldValues.get(2).replace(".", ""));
        Double distance = Double.valueOf(fieldValues.get(3).replace(",", "."));
        Integer floors = Integer.valueOf(fieldValues.get(4).replace(".", ""));
        Integer minutesSitting = Integer.valueOf(fieldValues.get(5).replace(".", ""));
        Integer minutesSlowActivity = Integer.valueOf(fieldValues.get(6).replace(".", ""));
        Integer minutesModerateActivity = Integer.valueOf(fieldValues.get(7).replace(".", ""));
        Integer minutesIntenseActivity = Integer.valueOf(fieldValues.get(8).replace(".", ""));
        Integer caloriesBurnedByActivity = Integer.valueOf(fieldValues.get(9).replace(".", ""));

        HealthMetric metric = new HealthMetric(date,
                                                calories,
                                                steps,
                                                distance,
                                                floors,
                                                minutesSitting,
                                                minutesSlowActivity,
                                                minutesModerateActivity,
                                                minutesIntenseActivity,
                                                caloriesBurnedByActivity);

        return metric;
    }

    private Map<Integer, String> getRawFieldValues(String line){
        Map<Integer, String> words = new HashMap<>();
        boolean notInsideComma = true;
        int wordIndex = 0;
        int start =0;
        for(int i=0; i<line.length()-1; i++)
        {
            if(line.charAt(i) == ',' && notInsideComma)
            {
                words.put(wordIndex, line.substring(start, i).replace("\"", ""));
                wordIndex++;
                start = i + 1;
            }
            else if(line.charAt(i) == '"')
                notInsideComma = !notInsideComma;
        }
        words.put(wordIndex, line.substring(start).replace("\"", ""));
        return words;
    }
}
