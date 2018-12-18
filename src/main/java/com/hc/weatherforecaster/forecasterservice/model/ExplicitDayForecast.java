package com.hc.weatherforecaster.forecasterservice.model;

public class ExplicitDayForecast {
    private int day;
    private String weather;

    public ExplicitDayForecast(int day, String weather) {
        this.day = day;
        this.weather = weather;
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

    @Override
    public String toString() {
        return "ExplicitDayForecast{" +
                "day=" + day +
                ", weather='" + weather + '\'' +
                '}';
    }
}
