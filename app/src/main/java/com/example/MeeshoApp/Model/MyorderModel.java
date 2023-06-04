package com.example.MeeshoApp.Model;

public class MyorderModel {

    int iv_myorder;
    String tv_orderdt,tv_order_id,tv_sold,tv_supplier,tv_ordername;


    public MyorderModel(int iv_myorder, String tv_orderdt, String tv_order_id, String tv_sold, String tv_supplier, String tv_ordername) {
        this.iv_myorder = iv_myorder;
        this.tv_orderdt = tv_orderdt;
        this.tv_order_id = tv_order_id;
        this.tv_sold = tv_sold;
        this.tv_supplier = tv_supplier;
        this.tv_ordername = tv_ordername;
    }

    public int getIv_myorder() {
        return iv_myorder;
    }

    public void setIv_myorder(int iv_myorder) {
        this.iv_myorder = iv_myorder;
    }

    public String getTv_orderdt() {
        return tv_orderdt;
    }

    public void setTv_orderdt(String tv_orderdt) {
        this.tv_orderdt = tv_orderdt;
    }

    public String getTv_order_id() {
        return tv_order_id;
    }

    public void setTv_order_id(String tv_order_id) {
        this.tv_order_id = tv_order_id;
    }

    public String getTv_sold() {
        return tv_sold;
    }

    public void setTv_sold(String tv_sold) {
        this.tv_sold = tv_sold;
    }

    public String getTv_supplier() {
        return tv_supplier;
    }

    public void setTv_supplier(String tv_supplier) {
        this.tv_supplier = tv_supplier;
    }

    public String getTv_ordername() {
        return tv_ordername;
    }

    public void setTv_ordername(String tv_ordername) {
        this.tv_ordername = tv_ordername;
    }
}
