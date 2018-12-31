package com.hc.weatherforecaster.forecasterservice.mathutility;

import com.hc.weatherforecaster.forecasterservice.entity.Planet;
import com.hc.weatherforecaster.forecasterservice.entity.Point;
import com.hc.weatherforecaster.forecasterservice.entity.StraightShape;
import com.hc.weatherforecaster.forecasterservice.entity.Sun;
import com.hc.weatherforecaster.forecasterservice.service.ConfigPropertiesStuff;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hector Contreras
 */
public class MathUtilities {

    @Autowired
    private static ConfigPropertiesStuff configPropertiesStuff;

    static final double ONE_DECIMALS = 10d;
    static final double TWO_DECIMALS = 100d;
    static final double THREE_DECIMALS = 1000d;



    public static Point getPlanetPositionFromDay(int day, Planet planet){
        Point point = new Point();
        double angleOffset = planet.getAngularSpeed() * day;
        double radians = Math.toRadians(angleOffset);
        double x = (double)Math.round((planet.getRadio() * Math.cos(radians)) * 10d) / 10d;
        point.setX(x);
        //point.setX(precision(planet.getRadio() * Math.cos(radians)));
        //point.setX(Math.round(planet.getRadio() * Math.cos(radians) ));
        double y = (double)Math.round((planet.getRadio() * Math.sin(radians)) * 10d) / 10d;
        point.setY(y);
        //point.setY(precision(planet.getRadio() * Math.sin(radians)));
        //point.setX(Math.round(planet.getRadio() * Math.sin(radians) ));
        return point;
    }

    public static List<Point> planetsToPointsList(List<Planet> planetList, int day){
        return (List<Point>)planetList.stream().map(
                p -> getPlanetPositionFromDay(day, p)).collect(Collectors.toList());
    }

    public static boolean planetsShape(List<Point> pointsList){
        Point p1 = pointsList.get(0), p2 = pointsList.get(1), p3 = pointsList.get(2);
        StraightShape rectShape = new StraightShape(p1, p2);
        /*
         * returns true if planets are aligned
         * returns false if planets form a triangle
         */
        //boolean result = rectShape.belongsToStraight(p3);
        boolean result = rectShape.isCollinear(p3);
        return result;
    }

    public static boolean areSunAligned(List<Point> pointsList){
        Sun sun = new Sun(0, 0);
        Point p1 = pointsList.get(0), p2 = pointsList.get(1), p3 = pointsList.get(2);
        StraightShape rectShape = new StraightShape(p1, p2);
        if (rectShape.belongsToStraight(p3) && rectShape.belongsToStraight(sun.getPosition()))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public static double trianglePerimeter(List<Point> pointsList){
        Point p1 = pointsList.get(0), p2 = pointsList.get(1), p3 = pointsList.get(2);
        return pointsDistance(p1, p2) + pointsDistance(p2, p3) + pointsDistance(p3, p1);
    }

    public static boolean sunInsideTriangle(List<Point> pointsList){
        Sun sun = new Sun(0, 0);
        Point p1 = pointsList.get(0), p2 = pointsList.get(1), p3 = pointsList.get(2);
        Point pSun = sun.getPosition();
        double fullArea = triangleArea(p1, p2, p3);
        double partialArea = triangleArea(p1, p2, pSun) +
                triangleArea(p2, p3, pSun) +
                triangleArea(p3, p1, pSun);
        if( fullArea == partialArea )
            return Boolean.TRUE;

        return Boolean.FALSE;
    }

    public static boolean sunLiesInTriangleVectorsMethod(List<Point> pointsList){
        Sun sun = new Sun(0, 0);
        Point a = pointsList.get(0), b = pointsList.get(1), c = pointsList.get(2);
        Point psun = sun.getPosition();
        double w1 = ((a.getX()*(c.getY()-a.getY())) + ((psun.getY()-a.getY())*(c.getX()-a.getX())) - ((psun.getX()*(c.getY() - a.getX()))));
        w1 = w1 / (((b.getY()-a.getY())*(c.getX()-a.getX())) - ((b.getX()-a.getX())*(c.getY()-a.getY())));
        double w2 = ((psun.getY()-a.getY())-(w1*(b.getY()-a.getY())))/(c.getY()-a.getY());
        if((w1 >= 0) && (w2 >= 0) && ((w1+w2) <= 1))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public static double pointsDistance(Point a, Point b){
        return Math.hypot((b.getY()-a.getY()), (b.getX()-a.getX()));
    }

    public static double triangleArea(Point a, Point b, Point c){
        //double M = a.getX()*( b.getY() - c.getY() );
        //double N = b.getX() * ( c.getY() - a.getY() );
        //double O = c.getX() * ( a.getY() - b.getY() );
        return ((a.getX()*( b.getY() - c.getY() )) + (b.getX() * ( c.getY() - a.getY() )) + (c.getX() * ( a.getY() - b.getY() )))/2;
    }


    public static double precision(double number){
        double decimals;
        int precision = 1;
        switch(precision){
            case 1:
                decimals = ONE_DECIMALS;
                break;
            case 2:
                decimals = TWO_DECIMALS;
                break;
            case 3:
                decimals = THREE_DECIMALS;
                break;
            default:
                decimals = TWO_DECIMALS;
                break;
        }
        return (double)Math.round(number * decimals) / decimals;

    }

    public static double getNumberPrecision(){
        return new ConfigPropertiesStuff().numberPrecision();
    }

    public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }

}
