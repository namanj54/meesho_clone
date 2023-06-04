package com.example.MeeshoApp.Model;

public class LookingModel {
     int imageview1;
     int imageview2;
     String textview;



    String id;


    public LookingModel(int imageview1, int imageview2, String textview, String id) {
        this.imageview1 = imageview1;
        this.imageview2 = imageview2;
        this.textview = textview;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getImageview2() {
        return imageview2;
    }

    public void setImageview2(int imageview2) {
        this.imageview2 = imageview2;
    }


    public int getImageview1() {
        return imageview1;
    }

    public void setImageview1(int imageview1) {
        this.imageview1 = imageview1;
    }

    public String getTextview() {
        return textview;
    }

    public void setTextview(String textview) {
        this.textview = textview;
    }
}

