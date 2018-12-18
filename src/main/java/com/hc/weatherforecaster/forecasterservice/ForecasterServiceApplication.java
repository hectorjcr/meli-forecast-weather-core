package com.hc.weatherforecaster.forecasterservice;

import com.hc.weatherforecaster.forecasterservice.entity.Planet;
import com.hc.weatherforecaster.forecasterservice.mathutility.MathUtilities;
import com.hc.weatherforecaster.forecasterservice.service.WeatherForecasterService;
import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

@SpringBootApplication
@EnableJSONDoc
public class ForecasterServiceApplication {

    private final static Logger ROOT_LOGGER = Logger.getLogger("com.hc.weatherforecaster.forecasterservice");
    private final static Logger CONTROLLER_LOGGER = Logger.getLogger("com.hc.weatherforecaster.forecasterservice.controller");
    private final static Logger SERVICE_LOGGER = Logger.getLogger("com.hc.weatherforecaster.forecasterservice.service");


    private static final Logger LOGGER = Logger.getLogger(ForecasterServiceApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(ForecasterServiceApplication.class, args);
        System.out.println(MathUtilities.getNumberPrecision());
        try {
            Handler fileHandler = new FileHandler("./applogs.log",true);
            Handler consolHandler = new ConsoleHandler();

            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);

            ROOT_LOGGER.addHandler(fileHandler);
            ROOT_LOGGER.addHandler(consolHandler);

            consolHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);

            LOGGER.log(Level.INFO, "Forecaster weather service initialized ;)");


        } catch (IOException ioEx) {
            LOGGER.log(Level.SEVERE, "Error de entrada y salida");
        } catch (SecurityException seEx){
            LOGGER.log(Level.SEVERE,"Excepcion de seguridad");
        }
        init();
    }

    public static void init(){
        WeatherForecasterService weatherForecasterService = new WeatherForecasterService();
        for (int i = 0; i < 3650; i++) {
            List<Planet> planetList = weatherForecasterService.getPlanetsList();
            try {
                System.out.println(weatherForecasterService.forecastWeatherFromDay(planetList,i));
            } catch (Exception e) {
                LOGGER.log(Level.INFO,"No inicializado");
            }
        }
        System.out.println(weatherForecasterService.finalForecastReport(0,3650));
    }

    /**
     * Esta funcion nos permite convertir el stackTrace en un String, necesario
     * para poder imprimirlos al log debido a cambios en como Java los maneja
     * internamente
     *
     * @param e Excepcion de la que queremos el StackTrace
     * @return StackTrace de la excepcion en forma de String
     */
    public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }

}

