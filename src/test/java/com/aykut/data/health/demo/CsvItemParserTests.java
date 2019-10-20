package com.aykut.data.health.demo;

import com.aykut.data.health.demo.domain.CsvItemParser;
import com.aykut.data.health.demo.domain.HealthMetric;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class CsvItemParserTests {

    private static final String LINE_WITH_ALL_QUOTES =   "\"08-05-2015\",\"1.934\",\"905\",\"0,65\",\"0\",\"1.355\",\"46\",\"0\",\"0\",\"168\"";

    @Test
    public void shouldBeAbleToParseRegularLineWithQuotes(){
        CsvItemParser creditLimitItemParser = new CsvItemParser();

        HealthMetric healthMetric = creditLimitItemParser.parseItem(LINE_WITH_ALL_QUOTES);

        assertEquals(healthMetric.calories, Integer.valueOf(1934));
        assertEquals(healthMetric.date, LocalDate.of(2015, 5, 8));
    }
}
