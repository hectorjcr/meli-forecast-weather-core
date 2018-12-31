package com.hc.weatherforecaster.forecasterservice.service;

import com.hc.weatherforecaster.forecasterservice.entity.ForecastFinalReport;
import com.hc.weatherforecaster.forecasterservice.model.ExplicitDayForecast;
import com.hc.weatherforecaster.forecasterservice.entity.Planet;
import com.hc.weatherforecaster.forecasterservice.entity.Point;
import com.hc.weatherforecaster.forecasterservice.mathutility.MathUtilities;
import com.hc.weatherforecaster.forecasterservice.model.FullWeatherForecast;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class WeatherForecasterService implements WeatherForecasterServiceInterface{

    private final static Logger SERVICE_LOGGER = Logger.getLogger(WeatherForecasterService.class.getName());

    @Override
    public ExplicitDayForecast forecastWeatherFromDay(List<Planet> planets, int day) throws Exception {
        ExplicitDayForecast explicitDayForecast;
        List<Point> pointsList = MathUtilities.planetsToPointsList(planets, day);
        explicitDayForecast = new ExplicitDayForecast(day,manageCondition(pointsList));
        return explicitDayForecast;
    }

    @Override
    public List<ExplicitDayForecast> forecastWeatherDayRange(List<Planet> planets, int fromDay, int endDate) {
        List<ExplicitDayForecast> list = new ArrayList<>();
        for (int i=fromDay; i <= endDate; i++){
            try {
                list.add(forecastWeatherFromDay(planets,i));
            } catch (Exception e) {
                SERVICE_LOGGER.log(Level.FINER,"Forecast list not initialized");
            }
        }
        return list;
    }

    @Override
    public ForecastFinalReport finalForecastReport(int fromDay, int atDay) {
        ForecastFinalReport forecastFinalReport;
        int rainyDays=0,droughtDays=0,bestDaysDays=0,foggyDays =0,topPeakrainyDay =0;
        double maxPerimeter = 0, perimeter=0;
        String climaActual ="",climaAnterior="Drought";
        HashMap<String, Integer> periodos = new HashMap<>();
        periodos.put("Drought",0);periodos.put("Rainy",0);periodos.put("Foggy",0);periodos.put("BestCondition",0);
        List<Planet> planetList = getPlanetsList();
        for (int i = fromDay; i < atDay; i++) {
            List<Point> pointsList = MathUtilities.planetsToPointsList(planetList,i);
            if(MathUtilities.planetsShape(pointsList) && MathUtilities.areSunAligned(pointsList)) {
                droughtDays++;climaActual="Drought";
            }
            if(MathUtilities.planetsShape(pointsList) && !MathUtilities.areSunAligned(pointsList)) {
                bestDaysDays++;
                climaActual="BestCondition";
            }
            if(!MathUtilities.planetsShape(pointsList) && MathUtilities.sunLiesInTriangleVectorsMethod(pointsList)){
                rainyDays++;
                climaActual="Rainy";
                perimeter = MathUtilities.trianglePerimeter(pointsList);
                if(perimeter > maxPerimeter){
                    maxPerimeter=perimeter;
                    topPeakrainyDay = i;
                }
            }
            if(!MathUtilities.planetsShape(pointsList) && !MathUtilities.sunLiesInTriangleVectorsMethod(pointsList)){
                foggyDays++;
                climaActual = "Foggy";
            }
            if (!climaActual.equals(climaAnterior)){
                periodos.computeIfPresent(climaAnterior,(k,v)->v+1);
            }
            climaAnterior = climaActual;
        }
        return new ForecastFinalReport(periodos.get("Rainy"),periodos.get("Drought"),topPeakrainyDay,
                periodos.get("BestCondition"),periodos.get("Foggy"),rainyDays,droughtDays,bestDaysDays,foggyDays);
    }

    @Override
    public String manageCondition(List<Point> pointsList) {
        if(MathUtilities.planetsShape(pointsList)){
            if(MathUtilities.areSunAligned(pointsList)){
                return this.DROUGHT;
            }
            return this.BEST_CONDITION;
        }
        if(MathUtilities.sunLiesInTriangleVectorsMethod(pointsList)){
            return this.RAINY;
        }
        return this.NORMAL;
    }

    @Override
    public List<Planet> getPlanetsList() {
        Planet ferengi = new Planet("ferengi",500,1);
        Planet betasoide = new Planet("betasoide",2000,3);
        Planet vulcano = new Planet("Vulcano",1000,-5);
        List<Planet> list = new ArrayList<>();
        list.add(ferengi);
        list.add(betasoide);
        list.add(vulcano);
        return list;
    }

    public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }
}
