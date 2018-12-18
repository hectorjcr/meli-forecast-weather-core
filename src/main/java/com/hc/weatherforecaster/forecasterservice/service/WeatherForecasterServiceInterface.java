package com.hc.weatherforecaster.forecasterservice.service;

import com.hc.weatherforecaster.forecasterservice.entity.ForecastFinalReport;
import com.hc.weatherforecaster.forecasterservice.entity.Point;
import com.hc.weatherforecaster.forecasterservice.model.ExplicitDayForecast;
import com.hc.weatherforecaster.forecasterservice.entity.Planet;
import com.hc.weatherforecaster.forecasterservice.model.FullWeatherForecast;

import java.util.List;

public interface WeatherForecasterServiceInterface {
    final String RAINY = "Rainy";
    final String DROUGHT = "Drought";
    final String BEST_CONDITION = "Best Condition";
    final String NORMAL = "Foggy";


    ExplicitDayForecast forecastWeatherFromDay(List<Planet> planets, int day) throws Exception;
    String manageCondition(List<Point> pointsList);
    List<ExplicitDayForecast> forecastWeatherDayRange(List<Planet> planets, int fromDay, int endDate);
    ForecastFinalReport finalForecastReport(int fromDay, int atDay);
    List<Planet> getPlanetsList();
}
