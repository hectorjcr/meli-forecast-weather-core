package com.hc.weatherforecaster.forecasterservice.service;

public interface ConfigPropertiesStuffInterface {
    public default double numberPrecision() {
        return 10d;
    }
}
