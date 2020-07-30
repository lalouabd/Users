package com.bandg.users.models;

import sun.security.ec.point.Point;

import java.awt.geom.Point2D;

public class City {
    private final String name;
    private final int codePostal;
    private  final Point2D coordinate;

    public City(String name, int codePostal, Point2D coordinate) {
        this.name = name;
        this.codePostal = codePostal;
        this.coordinate =  new java.awt.Point(2,2);

    }
}

