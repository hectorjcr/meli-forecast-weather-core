package com.hc.weatherforecaster.forecasterservice.entity;

public class DatesRange {
    private int initDay;
    private int endDate;

    public DatesRange(int initDay, int endDate) {
        this.initDay = initDay;
        this.endDate = endDate;
    }

    public int getInitDay() {
        return initDay;
    }

    public void setInitDay(int initDay) {
        this.initDay = initDay;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "DatesRange{" +
                "initDay=" + initDay +
                ", endDate=" + endDate +
                '}';
    }
}
