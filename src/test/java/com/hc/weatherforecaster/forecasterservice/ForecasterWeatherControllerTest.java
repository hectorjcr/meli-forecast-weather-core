package com.hc.weatherforecaster.forecasterservice;

import com.hc.weatherforecaster.forecasterservice.model.ExplicitDayForecast;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForecasterServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ForecasterWeatherControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getUrl(){
        return "https://meli-forecast-core-ms.herokuapp.com/api/forecast";
        //return "http://localhost:"+port+"/api/forecast";
    }

    @Test
    public void getBulkConnectionTest(){
        String result = restTemplate.getForObject(getUrl()+"/fakefordebugg/12/14",String.class);
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    public void getDayForecastTest(){
        String result = restTemplate.getForObject(getUrl()+"/dayforecast/67",String.class);
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    public void generateRecordsTest(){
        ResponseEntity<String> result = restTemplate.getForEntity(getUrl()+"/generaterecords/0/10",String.class);
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    public void getTotalReportTest(){
        ResponseEntity<String> result = restTemplate.getForEntity(getUrl()+"/totalreport",String.class);
        System.out.println(result);
        assertNotNull(result);
    }
}
