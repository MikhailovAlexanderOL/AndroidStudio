package com.example.city_sight;

import com.yandex.mapkit.geometry.Point;

public class Sight {
    private String title;

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public Sight(){};

    public void setFullDisc(String fullDisc) {
        this.fullDisc = fullDisc;
    }

    public Sight(String title, String fullDisc, Point coordinates) {
        this.title = title;
        this.fullDisc = fullDisc;
        this.coordinates = coordinates;
    }
    public Sight(String title, String fullDisc) {
        this.title = title;
        this.fullDisc = fullDisc;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public Sight(String title, String fullDisc, Point coordinates, String workHours) {
        this.title = title;
        this.fullDisc = fullDisc;
        this.coordinates = coordinates;
        this.workHours = workHours;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkHours() {
        return workHours;
    }

    public String getFullDisc() {
        return fullDisc;
    }

    public String getTitle() {
        return title;
    }

    private String fullDisc;
    private String workHours;
    private Point coordinates;
}
