package com.hc.weatherforecaster.forecasterservice.entity;

/**
 * @author Hector Contreras
 *
 */
public class Planet {

    private String name;
    private double radio;
    private double angularSpeed;

    public Planet(String name, double radio, double angularSpeed) {
        this.name = name;
        this.radio = radio;
        this.angularSpeed = angularSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double getAngularSpeed() {
        return angularSpeed;
    }

    public void setAngularSpeed(double angularSpeed) {
        this.angularSpeed = angularSpeed;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", radio=" + radio +
                ", angularSpeed=" + angularSpeed +
                '}';
    }
}
