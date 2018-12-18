package com.hc.weatherforecaster.forecasterservice.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class ConfigPropertiesStuff implements ConfigPropertiesStuffInterface {

    private final static Logger LOGGER = Logger.getLogger(ConfigPropertiesStuff.class.getName());

    @Value(value = "${mathlibrary.number.precision}")
    private double numberPrecision;

    @Override
    public double numberPrecision() {
        double numberPr=0;
        try {
            numberPr = numberPrecision;
        }catch (Exception ex){
            LOGGER.log(Level.CONFIG, "No se pudo leer del archivo de propiedades");
            return 10d;
        }
        LOGGER.log(Level.INFO,"definida la precisión numérica");
        return numberPr;
    }
}
