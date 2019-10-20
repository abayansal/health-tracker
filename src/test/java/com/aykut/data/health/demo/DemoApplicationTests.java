package com.aykut.data.health.demo;

import com.aykut.data.health.demo.controller.DataLoadController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private DataLoadController controller;

    @Test
    public void contextLoads() {
        assertNotNull(controller);
    }

}
