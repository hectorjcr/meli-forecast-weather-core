package com.hc.weatherforecaster.forecasterservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hc.weatherforecaster.forecasterservice.entity.ForecastFinalReport;
import com.hc.weatherforecaster.forecasterservice.entity.Planet;
import com.hc.weatherforecaster.forecasterservice.exception.ResourceNotFoundException;
import com.hc.weatherforecaster.forecasterservice.model.ExplicitDayForecast;
import com.hc.weatherforecaster.forecasterservice.service.WeatherForecasterService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping(value = "/api/forecast")
@Component
@CrossOrigin("*")
@Api(name="Controlador principal para las predicciones climaticas",
        description="Operaciones petinentes al control de data e informacion del clima, genera la informacion que se almacena en la base de datos",
        stage = ApiStage.RC)
public class ForecastWeatherController {

    private static final Logger CONTROLLER_LOGGER = Logger.getLogger(ForecastWeatherController.class.getName());


    private RestTemplate restTemplate = new RestTemplate();

    @Value(value = "${microservice.dbservice.uri}")
    private String restUri;

    private String uriSuffix = "/api/v1/forecast";

    @Autowired
    private WeatherForecasterService weatherForecasterService;

    public ForecastWeatherController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping(value = "/fakefordebugg/{fromday}/{atDay}")
    @ApiMethod(description = "Este metodo es usado solo para verificar que los servicios se comunican entre si")
    public ResponseEntity<HashMap> getBulkConnection(@ApiPathParam(name = "fromday", description = "Dia de inicio") @PathVariable(value = "fromday") String fd,
                                                    @ApiPathParam(name = "atDay", description = "Dia final") @PathVariable(value = "atDay") String ad){
        HashMap<String,String> result = new HashMap<>();
        result.put("fromDay",fd);
        result.put("atDay",ad);
        CONTROLLER_LOGGER.log(Level.INFO,result.toString());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/dayforecast/{id}")
    @ApiMethod(description = "Devuelve las condiciones climatologicas de la galaxia para un dia especifico")
    public ResponseEntity<String>  getDayForecast(@ApiPathParam(name = "id", description = "Numero del dia a consultar")
                                                      @PathVariable(value = "id") int day) throws ResourceNotFoundException {
        List<Planet> planetsList = weatherForecasterService.getPlanetsList();
        ExplicitDayForecast dayForecast;
        String json;
        try {
             dayForecast = weatherForecasterService.forecastWeatherFromDay(planetsList,day);
             ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
             json = ow.writeValueAsString(dayForecast);

        } catch (Exception e) {
            CONTROLLER_LOGGER.log(Level.FINE, WeatherForecasterService.getStackTrace(e));
            return new ResponseEntity<String>("Recurso no encontrado",HttpStatus.NO_CONTENT);
        }
        CONTROLLER_LOGGER.log(Level.INFO,dayForecast.toString());
        return new ResponseEntity<String>(json,HttpStatus.valueOf(200));
    }

    @RequestMapping(value = "/generaterecords/{fromday}/{atday}")
    @Async(value = "threadPoolTaskExecutor")
    @ApiMethod(description = "Este metodo solo debe ser levantado por el microservicio del job Scheduler, " +
            "se usa para generar el pronostico completo de un rango de dias si los dia del rango ya existen, actualiza " +
            "la informacion de lo contrario crea una nueva entrada")
    public ResponseEntity<String> generateRecords(@ApiPathParam(name = "fromday",description = "dia de inicio de los calculos")
                                                      @PathVariable(value = "fromday") int fromDay,
                                             @ApiPathParam(name = "atday", description = "dia tope para realizar los calculos")
                                             @PathVariable(value = "atday") int atDay) throws ResourceNotFoundException{
        List<Planet> planetsList = weatherForecasterService.getPlanetsList();
        List<ExplicitDayForecast> list = new ArrayList<>();
        ExplicitDayForecast dayForecast;
        restUri = restUri + uriSuffix;
        String response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        for (int i = fromDay; i < atDay; i++) {
            try {

                dayForecast = weatherForecasterService.forecastWeatherFromDay(planetsList,i);
                String json = new ObjectMapper().writeValueAsString(dayForecast);
                response = restTemplate.postForObject(restUri,dayForecast,String.class);
                CONTROLLER_LOGGER.log(Level.INFO,response);

            }catch (Exception e){
                CONTROLLER_LOGGER.log(Level.FINE, WeatherForecasterService.getStackTrace(e));
                return notFound().build();
            }
        }
        return ResponseEntity.ok().body("Operación Exitosa");
    }

    @RequestMapping(value = "/totalreport")
    @ApiMethod(description = "Devuelve un string con los valores del reporte total de eventos climatologicos")
    @Async(value = "threadPoolTaskExecutor")
    public ResponseEntity<ForecastFinalReport> getTotalReport(){
        String result="";
        int totalRows = 3650;
        restUri = restUri + uriSuffix + "/totalrows";
        try{
            String response = restTemplate.getForObject(restUri.toString(),String.class);
            totalRows = Integer.parseInt(response.toString());
        }catch (Exception e){
            CONTROLLER_LOGGER.log(Level.WARNING,e.getMessage());
            return ResponseEntity.noContent().build();
        }
        ForecastFinalReport forecastFinalReport = weatherForecasterService.finalForecastReport(0,totalRows);
        CONTROLLER_LOGGER.log(Level.INFO,forecastFinalReport.toString());
        return ResponseEntity.ok().body(forecastFinalReport);
    }


}
