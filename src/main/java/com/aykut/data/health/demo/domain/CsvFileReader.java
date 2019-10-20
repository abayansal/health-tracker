package com.aykut.data.health.demo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvFileReader {

    @Autowired
    private CsvItemParser csvItemParser;

    public List<HealthMetric> readFile(File file) throws IOException {
        List<HealthMetric> items = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        try {
            String line;
            int i=0;
            while ((line = reader.readLine()) != null) {
                if(i==0){
                    i++;
                    continue;
                }
                items.add(csvItemParser.parseItem(line));
            }
        } finally {
            reader.close();
        }
        return items;
    }
}
