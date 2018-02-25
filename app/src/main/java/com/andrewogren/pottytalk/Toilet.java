package com.andrewogren.pottytalk;

import java.util.ArrayList;

/**
 * Created by andrewogren on 2/22/18.
 */

public class Toilet {

    private long id;
    private String buildingName;
    private String hallName;
    private int floor;

    private int avgSmell;
    private int avgToiletPaper;
    private int avgCleanliness;
    private boolean isClogged;

    private ArrayList<Review> reviewsList;

    public long getId() { return id;}
    public void setId(long id) {this.id = id;}

    public String getBuildingName() {return buildingName;}
    public void setBuildingName(String name) {this.buildingName = name;}

    public String getHallName() {return hallName;}
    public void setHallName(String name) {this.hallName = name;}

    public int getFloor() {return floor;}
    public void setFloor(int floor) {this.floor = floor;}

    public int getAvgSmell() {return avgSmell;}
    public void setAvgSmell(int smell) { this.avgSmell = smell; }

    public int getAvgToiletPaper() {return avgToiletPaper;}
    public void setAvgToiletPaper(int paper) {this.avgToiletPaper = paper;}

    public int getAvgCleanliness() {return avgCleanliness;}
    public void setAvgCleanliness(int cleanliness) {this.avgCleanliness = cleanliness;}

    public boolean getClogged() { return isClogged;}
    public void setClogged(boolean clogged) {this.isClogged = clogged;}

    public ArrayList<Review> getReviewsList() { return reviewsList;}
    public void setReviewsList(ArrayList<Review> arr) { this.reviewsList = arr;}

}
