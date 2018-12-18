package com.hc.weatherforecaster.forecasterservice.model;


import com.hc.weatherforecaster.forecasterservice.entity.Point;

import java.util.List;

public class FullWeatherForecast {
    private int day;
    private String weather;
    private double perimeter;
    private List<Point> pointList;

    public FullWeatherForecast(int day, String weather, double perimeter, List<Point> pointList) {
        this.day = day;
        this.weather = weather;
        this.perimeter = perimeter;
        this.pointList = pointList;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    @Override
    public String toString() {
        return "FullWeatherForecast{" +
                "day=" + day +
                ", weather='" + weather + '\'' +
                ", perimeter=" + perimeter +
                ", pointList=" + pointList +
                '}';
    }
}
