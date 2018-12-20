package com.hc.weatherforecaster.forecasterservice.entity;

public class ForecastFinalReport {
    private int rainyPeriods;
    private int droghtPeriods;
    private int maxRainyPeak;
    private int bestConditionPeriods;
    private int foggyPeriods;
    private int rainyDays;
    private int droughtDays;
    private int bestConditionsDays;
    private int foggyDays;

    public ForecastFinalReport(int rainyPeriods, int droghtPeriods, int maxRainyPeak, int bestConditionPeriods, int foggyPeriods, int rainyDays, int droughtDays, int bestConditionsDays, int foggyDays) {
        this.rainyPeriods = rainyPeriods;
        this.droghtPeriods = droghtPeriods;
        this.maxRainyPeak = maxRainyPeak;
        this.bestConditionPeriods = bestConditionPeriods;
        this.foggyPeriods = foggyPeriods;
        this.rainyDays = rainyDays;
        this.droughtDays = droughtDays;
        this.bestConditionsDays = bestConditionsDays;
        this.foggyDays = foggyDays;
    }

    public int getRainyPeriods() {
        return rainyPeriods;
    }

    public void setRainyPeriods(int rainyPeriods) {
        this.rainyPeriods = rainyPeriods;
    }

    public int getDroghtPeriods() {
        return droghtPeriods;
    }

    public void setDroghtPeriods(int droghtPeriods) {
        this.droghtPeriods = droghtPeriods;
    }

    public int getMaxRainyPeak() {
        return maxRainyPeak;
    }

    public void setMaxRainyPeak(int maxRainyPeak) {
        this.maxRainyPeak = maxRainyPeak;
    }

    public int getBestConditionPeriods() {
        return bestConditionPeriods;
    }

    public void setBestConditionPeriods(int bestConditionPeriods) {
        this.bestConditionPeriods = bestConditionPeriods;
    }

    public int getFoggyPeriods() {
        return foggyPeriods;
    }

    public void setFoggyPeriods(int foggyPeriods) {
        this.foggyPeriods = foggyPeriods;
    }

    public int getRainyDays() {
        return rainyDays;
    }

    public void setRainyDays(int rainyDays) {
        this.rainyDays = rainyDays;
    }

    public int getDroughtDays() {
        return droughtDays;
    }

    public void setDroughtDays(int droughtDays) {
        this.droughtDays = droughtDays;
    }

    public int getBestConditionsDays() {
        return bestConditionsDays;
    }

    public void setBestConditionsDays(int bestConditionsDays) {
        this.bestConditionsDays = bestConditionsDays;
    }

    public int getFoggyDays() {
        return foggyDays;
    }

    public void setFoggyDays(int foggyDays) {
        this.foggyDays = foggyDays;
    }

    @Override
    public String toString() {
        return "ForecastFinalReport{" +
                "rainyPeriods=" + rainyPeriods +
                ", droghtPeriods=" + droghtPeriods +
                ", maxRainyPeak=" + maxRainyPeak +
                ", bestConditionPeriods=" + bestConditionPeriods +
                ", foggyPeriods=" + foggyPeriods +
                ", rainyDays=" + rainyDays +
                ", droughtDays=" + droughtDays +
                ", bestConditionsDays=" + bestConditionsDays +
                ", foggyDays=" + foggyDays +
                '}';
    }
}
