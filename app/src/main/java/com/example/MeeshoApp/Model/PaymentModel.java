package com.example.MeeshoApp.Model;

import android.widget.ImageView;
import android.widget.TextView;

public class PaymentModel {

    int iv_pay_logo;
    String tv_pay_name;

    public PaymentModel(int iv_pay_logo, String tv_pay_name) {
        this.iv_pay_logo = iv_pay_logo;
        this.tv_pay_name = tv_pay_name;
    }

    public int getIv_pay_logo() {
        return iv_pay_logo;
    }

    public void setIv_pay_logo(int iv_pay_logo) {
        this.iv_pay_logo = iv_pay_logo;
    }

    public String getTv_pay_name() {
        return tv_pay_name;
    }

    public void setTv_pay_name(String tv_pay_name) {
        this.tv_pay_name = tv_pay_name;
    }
}
