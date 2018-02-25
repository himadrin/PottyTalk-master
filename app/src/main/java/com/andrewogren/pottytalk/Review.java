package com.andrewogren.pottytalk;

/**
 * Created by andrewogren on 2/22/18.
 */

public class Review {

    private long timeInMillis;
    private String userID;

    private int smellRating;
    private int toiletPaperRating;
    private int cleanlinessRating;
    private boolean isClogged;

    public long getTime() { return timeInMillis; }
    public void setTime(long time) { this.timeInMillis = time;}

    public String getUserID() {return  userID;}
    public void setUserID(String userID) {this.userID = userID;}

    public int getSmellRating() {return smellRating;}
    public void setSmellRating(int smellRating) {this.smellRating = smellRating;}

    public int getToiletPaperRating() {return this.toiletPaperRating;}
    public void setToiletPaperRating(int tpRating) {this.toiletPaperRating = tpRating;}

    public int getCleanlinessRating(){return cleanlinessRating;}
    public void setCleanlinessRating(int cleanlinessRating) {this.cleanlinessRating =
            cleanlinessRating;}

    public boolean getClogged() {return isClogged;}
    public void setClogged(boolean isClogged) { this.isClogged = isClogged;  }

}
