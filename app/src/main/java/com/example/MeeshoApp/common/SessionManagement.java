package com.example.MeeshoApp.common;



import static com.example.MeeshoApp.common.constant.CITY_KEY;
import static com.example.MeeshoApp.common.constant.CITY_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.IMAGE_KEY;
import static com.example.MeeshoApp.common.constant.LOCALITY_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.LOGIN_KEY;
import static com.example.MeeshoApp.common.constant.NUMBER_KEY;
import static com.example.MeeshoApp.common.constant.PASSWORD_KEY;
import static com.example.MeeshoApp.common.constant.PINCODE_KEY;
import static com.example.MeeshoApp.common.constant.PINCODE_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.PRODUCT_IMAGE_KEY;
import static com.example.MeeshoApp.common.constant.PRODUCT_NAME_KEY;
import static com.example.MeeshoApp.common.constant.PRODUCT_OFFERS_KEY;
import static com.example.MeeshoApp.common.constant.PRODUCT_PRICE_KEY;
import static com.example.MeeshoApp.common.constant.SHARED_PREFS;
import static com.example.MeeshoApp.common.constant.STATE_KEY;
import static com.example.MeeshoApp.common.constant.STATE_LIVE_KEY;
import static com.example.MeeshoApp.common.constant.USERNAME_KEY;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    Context context;
    SharedPreferences.Editor editor;

    public SessionManagement( Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
         editor = sharedPreferences.edit();
    }


    public void productview(String image,String name,String price,String offers,String rating,Boolean islog){
        editor.putBoolean(LOGIN_KEY, islog);
        editor.putString(PRODUCT_IMAGE_KEY, image);
        editor.putString(PRODUCT_NAME_KEY,name);
        editor.putString(PRODUCT_PRICE_KEY,price);
        editor.putString(PRODUCT_OFFERS_KEY,offers);
        editor.commit();


    }



    public void createdata(String user, String password,Boolean is_log,String city,String pincode,String state){
        editor.putBoolean(LOGIN_KEY, is_log);
        editor.putString(USERNAME_KEY, user);
        editor.putString(PASSWORD_KEY,password);
        editor.putString(CITY_KEY,city);
        editor.putString(PINCODE_KEY,pincode);
        editor.putString(STATE_KEY,state);
        editor.commit();
    }



    public void updateimage(String image){
        editor.putString(IMAGE_KEY,image);
        editor.commit();
    }

    public void insert_live_data(String locality,String city,String pincode,String state){
        editor.putString(LOCALITY_LIVE_KEY,locality);
        editor.putString(CITY_LIVE_KEY,city);
        editor.putString(PINCODE_LIVE_KEY,pincode);
        editor.putString(STATE_LIVE_KEY,state);
        editor.commit();
    }
    public void updatedata(String city,String pincode,String state){
        editor.putString(CITY_KEY,city);
        editor.putString(PINCODE_KEY,pincode);
        editor.putString(STATE_KEY,state);
        editor.commit();
    }
    public void createno(String number){
        editor.putString(NUMBER_KEY,number);
        editor.commit();
    }
    public void create(String image){
        editor.putString(IMAGE_KEY,image);
        editor.commit();
    }

    public boolean islog()
    {
       return sharedPreferences.getBoolean(LOGIN_KEY,false);
    }


    public HashMap<String,String> getdata(){
        HashMap<String,String>user = new HashMap<>();

        user.put(IMAGE_KEY,sharedPreferences.getString(IMAGE_KEY,null));
        user.put(USERNAME_KEY,sharedPreferences.getString(USERNAME_KEY,null));
        user.put(PASSWORD_KEY,sharedPreferences.getString(PASSWORD_KEY,null));
        user.put(PINCODE_KEY,sharedPreferences.getString(PINCODE_KEY,null));
        user.put(CITY_KEY,sharedPreferences.getString(CITY_KEY,null));
        user.put(STATE_KEY,sharedPreferences.getString(STATE_KEY,null));
        user.put(NUMBER_KEY,sharedPreferences.getString(NUMBER_KEY,null));
        user.put(IMAGE_KEY,sharedPreferences.getString(IMAGE_KEY,null));
        user.put(PRODUCT_IMAGE_KEY,sharedPreferences.getString(PRODUCT_IMAGE_KEY,null));
        user.put(PRODUCT_NAME_KEY,sharedPreferences.getString(PRODUCT_NAME_KEY,null));
        user.put(PRODUCT_PRICE_KEY,sharedPreferences.getString(PRODUCT_PRICE_KEY,null));
        user.put(PRODUCT_OFFERS_KEY,sharedPreferences.getString(PRODUCT_OFFERS_KEY,null));
        user.put(LOCALITY_LIVE_KEY,sharedPreferences.getString(LOCALITY_LIVE_KEY,null));
        user.put(CITY_LIVE_KEY,sharedPreferences.getString(CITY_LIVE_KEY,null));
        user.put(PINCODE_LIVE_KEY,sharedPreferences.getString(PINCODE_LIVE_KEY,null));
        user.put(STATE_LIVE_KEY,sharedPreferences.getString(STATE_LIVE_KEY,null));
        return  user;

    }

   
    public void logoutsession(){
        editor.clear();
        editor.commit();
    }

    
    


}
