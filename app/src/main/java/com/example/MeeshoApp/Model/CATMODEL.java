package com.example.MeeshoApp.Model;

public class CATMODEL {

    public CATMODEL(int iv_cat, String tv_cat_name) {
        this.iv_cat = iv_cat;
        this.tv_cat_name = tv_cat_name;
    }

    int iv_cat;
    String tv_cat_name;



    public int getIv_cat() {
        return iv_cat;
    }

    public void setIv_cat(int iv_cat) {
        this.iv_cat = iv_cat;
    }

    public String getTv_cat_name() {
        return tv_cat_name;
    }

    public void setTv_cat_name(String tv_cat_name) {
        this.tv_cat_name = tv_cat_name;
    }
}
