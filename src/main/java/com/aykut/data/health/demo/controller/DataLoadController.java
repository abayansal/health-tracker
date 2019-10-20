package com.aykut.data.health.demo.controller;

import com.aykut.data.health.demo.domain.CsvFileReader;
import com.aykut.data.health.demo.domain.HealthMetric;
import com.aykut.data.health.demo.domain.MetricAverage;
import com.aykut.data.health.demo.domain.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class DataLoadController {
    @Autowired
    private CsvFileReader csvFileReader;

    @Autowired
    private MetricService metricService;


    @PostMapping(value = "/upload")
    public void uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {

        try {
            List<HealthMetric> metrics = csvFileReader.readFile(convert(file));
            metricService.saveAll(metrics);
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.toString());
        }
    }

    @GetMapping(value = "/last-n-days/{days}")
    public List<HealthMetric> getLastNDays(@PathVariable Integer days) {

        List<HealthMetric> metrics;
        metrics = metricService.getLastNDays(days);
        return metrics;
    }

    @GetMapping(value = "/average/{days}")
    public List<MetricAverage> getAverageFor(@PathVariable Integer days) {

        List<MetricAverage> averages;
        averages = metricService.getAveragesFor(days);
        return averages;
    }

    private static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
