package com.example.MeeshoApp.Model;

public class CartModel {
    int iv_cart;
    String tv_cart_name,tv_cart_price,quantity;

    public CartModel(int iv_cart, String tv_cart_name, String tv_cart_price, String quantity) {
        this.iv_cart = iv_cart;
        this.tv_cart_name = tv_cart_name;
        this.tv_cart_price = tv_cart_price;
        this.quantity = quantity;
    }

    public int getIv_cart() {
        return iv_cart;
    }

    public void setIv_cart(int iv_cart) {
        this.iv_cart = iv_cart;
    }

    public String getTv_cart_name() {
        return tv_cart_name;
    }

    public void setTv_cart_name(String tv_cart_name) {
        this.tv_cart_name = tv_cart_name;
    }

    public String getTv_cart_price() {
        return tv_cart_price;
    }

    public void setTv_cart_price(String tv_cart_price) {
        this.tv_cart_price = tv_cart_price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
