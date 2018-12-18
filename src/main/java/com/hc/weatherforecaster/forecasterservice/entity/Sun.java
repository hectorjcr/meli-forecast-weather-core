package com.hc.weatherforecaster.forecasterservice.entity;


/**
 * @author Hector Contreras
 */
public class Sun {

    private double x;
    private double y;
    private String name;
    private Point position = new Point(0,0);

    public Sun() {
        this.x = 0;
        this.y = 0;
        this.name = "Sun";
    }

    public Sun(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sun{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
