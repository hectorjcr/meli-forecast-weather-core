package com.hc.weatherforecaster.forecasterservice.entity;

public class StraightShape {

    Point p1;
    Point p2;
    double slope;
    double bConst;

    public StraightShape(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.slope = getSlope(p1, p2);
        this.bConst = getBconst(p1, slope);
    }

    public double getSlope(Point a, Point b){
        return (b.getY() - a.getY() )/( b.getX() - a.getX());
    }

    public double getBconst(Point a, double slope){
        return a.getY() - (slope * a.getX());
    }

    public boolean belongsToStraight(Point p){
        double y = (slope * p.getX()) + bConst;
        if(Double.compare(p.getY(), y) == 0){
            return true;
        }
        return false;
    }

    public boolean isCollinear(Point p){
        //int s = (y2 - y1) * x + (x1 - x2) * y + (x2 * y1 - x1 * y2);
        double s =(p2.getY() - p1.getY()) * p.getX() + (p1.getX() - p2.getX()) * p.getY() + (p2.getX() * p1.getY() - p1.getX() * p2.getY());
        if(s == 0)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }


    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }


}
