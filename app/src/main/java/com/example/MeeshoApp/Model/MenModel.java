package com.example.MeeshoApp.Model;

public class MenModel {
    int men_catimage;
    String men_catext;



    public int getMen_catimage() {
        return men_catimage;
    }

    public void setMen_catimage(int men_catimage) {
        this.men_catimage = men_catimage;
    }

    public String getMen_catext() {
        return men_catext;
    }

    public void setMen_catext(String men_catext) {
        this.men_catext = men_catext;
    }

    public MenModel(int men_catimage, String men_catext) {
        this.men_catimage = men_catimage;
        this.men_catext = men_catext;
    }





}
