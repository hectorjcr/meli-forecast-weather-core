package com.hc.weatherforecaster.forecasterservice.entity;

public class ForecastFinalReport {
    private int rainyPeriods;
    private int droghtPeriods;
    private int maxRainyPeak;
    private int bestConditionPeriods;
    private int foggyPeriods;

    public ForecastFinalReport(int rainyPeriods, int droghtPeriods, int maxRainyPeak, int bestConditionPeriods, int foggyPeriods) {
        this.rainyPeriods = rainyPeriods;
        this.droghtPeriods = droghtPeriods;
        this.maxRainyPeak = maxRainyPeak;
        this.bestConditionPeriods = bestConditionPeriods;
        this.foggyPeriods = foggyPeriods;
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

    @Override
    public String toString() {
        return "ForecastFinalReport{" +
                "rainyPeriods=" + rainyPeriods +
                ", droghtPeriods=" + droghtPeriods +
                ", maxRainyPeak=" + maxRainyPeak +
                ", bestConditionPeriods=" + bestConditionPeriods +
                ", foggyPeriods=" + foggyPeriods +
                '}';
    }
}
