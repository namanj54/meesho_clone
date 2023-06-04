package com.example.MeeshoApp.Model;

public class ProductModel {
    int iv_product,id,qty;
    String product_name,price,offers,deliver_status,rating,status;
    public ProductModel( ) {

    }
    public ProductModel(int iv_product, String product_name, String price, String offers, String deliver_status, String rating, String status, int id,int qty) {
        this.iv_product = iv_product;
        this.product_name = product_name;
        this.price = price;
        this.offers = offers;
        this.deliver_status = deliver_status;
        this.rating = rating;
        this.status = status;
        this.id = id;
        this.qty = qty;

    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getIv_product() {
        return iv_product;
    }

    public void setIv_product(int iv_product) {
        this.iv_product = iv_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

    public String getDeliver_status() {
        return deliver_status;
    }

    public void setDeliver_status(String deliver_status) {
        this.deliver_status = deliver_status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
