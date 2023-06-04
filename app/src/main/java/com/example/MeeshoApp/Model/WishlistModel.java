package com.example.MeeshoApp.Model;

public class WishlistModel {

    int wish_iv;
    String wish_name;



    String wish_price;
    String wish_offers;


    public WishlistModel(int wish_iv, String wish_name, String wish_price, String wish_offers) {
        this.wish_iv = wish_iv;
        this.wish_name = wish_name;
        this.wish_price = wish_price;
        this.wish_offers = wish_offers;
    }

    public int getWish_iv() {
        return wish_iv;
    }

    public void setWish_iv(int wish_iv) {
        this.wish_iv = wish_iv;
    }

    public String getWish_name() {
        return wish_name;
    }

    public void setWish_name(String wish_name) {
        this.wish_name = wish_name;
    }

    public String getWish_price() {
        return wish_price;
    }

    public void setWish_price(String wish_price) {
        this.wish_price = wish_price;
    }

    public String getWish_offers() {
        return wish_offers;
    }

    public void setWish_offers(String wish_offers) {
        this.wish_offers = wish_offers;
    }




}
