package com.example.MeeshoApp.Model;

public class ProductviewModel {

    int prod_iv;
    String prod_price;
    String prod_del;
    String prod_rating;
    String prod_offers;



    String prod_name;

    public ProductviewModel(int prod_iv, String prod_price, String prod_del, String prod_rating, String prod_offers, String prod_name) {
        this.prod_iv = prod_iv;
        this.prod_price = prod_price;
        this.prod_del = prod_del;
        this.prod_rating = prod_rating;
        this.prod_offers = prod_offers;
        this.prod_name = prod_name;
    }

    public int getProd_iv() {
        return prod_iv;
    }

    public void setProd_iv(int prod_iv) {
        this.prod_iv = prod_iv;
    }

    public String getProd_price() {
        return prod_price;
    }

    public void setProd_price(String prod_price) {
        this.prod_price = prod_price;
    }

    public String getProd_del() {
        return prod_del;
    }

    public void setProd_del(String prod_del) {
        this.prod_del = prod_del;
    }

    public String getProd_rating() {
        return prod_rating;
    }

    public void setProd_rating(String prod_rating) {
        this.prod_rating = prod_rating;
    }

    public String getProd_offers() {
        return prod_offers;
    }

    public void setProd_offers(String prod_offers) {
        this.prod_offers = prod_offers;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }
}
