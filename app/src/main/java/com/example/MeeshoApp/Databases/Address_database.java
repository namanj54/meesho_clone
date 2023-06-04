package com.example.MeeshoApp.Databases;

import static com.example.MeeshoApp.common.constant.ADD_USER;
import static com.example.MeeshoApp.common.constant.ADD_USERCITY;
import static com.example.MeeshoApp.common.constant.ADD_USERHOUSENO;
import static com.example.MeeshoApp.common.constant.ADD_USERPHONE;
import static com.example.MeeshoApp.common.constant.ADD_USERPIN;
import static com.example.MeeshoApp.common.constant.ADD_USERSTATE;
import static com.example.MeeshoApp.common.constant.CART_COLUMN_KEY;
import static com.example.MeeshoApp.common.constant.CART_IMAGE_KEY;
import static com.example.MeeshoApp.common.constant.CART_NAME_KEY;
import static com.example.MeeshoApp.common.constant.CART_OFFERS_KEY;
import static com.example.MeeshoApp.common.constant.CART_PRICE_KEY;
import static com.example.MeeshoApp.common.constant.CART_QUANTITY_KEY;
import static com.example.MeeshoApp.common.constant.ORDER_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Address_database extends SQLiteOpenHelper {

    SQLiteDatabase adddb;
    private  static final String dbname = "add5db";
    private  static  final String  TABLE_DATA= "add_details";
    private  static final int DATABASE_VERSION =6;


    public Address_database(@Nullable Context context) {
        super(context, dbname, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase adddb) {
        this.adddb = adddb;
        adddb.execSQL("create Table "+ TABLE_DATA +" (add_username TEXT primary key ," +
                "add_userphone TEXT," +
                "add_userhouseno TEXT," +
                "add_userpin TEXT,"+
                "add_usercity TEXT,"+
                "add_userstate TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase adddb, int i, int i1) {
        adddb.execSQL("drop Table if exists "+ TABLE_DATA);
    }

    public boolean insertadddata(HashMap<String,String> add){
        adddb = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ADD_USER,add.get(ADD_USER));
        cv.put(ADD_USERPHONE,add.get(ADD_USERPHONE));
        cv.put(ADD_USERHOUSENO,add.get(ADD_USERHOUSENO));
        cv.put(ADD_USERPIN,add.get(ADD_USERPIN));
        cv.put(ADD_USERCITY,add.get(ADD_USERCITY));
        cv.put(ADD_USERSTATE,add.get(ADD_USERSTATE));
        adddb.insert(TABLE_DATA,null,cv);
        return true;
    }


    public void removefromcart(String name) {
        adddb = getReadableDatabase();
        adddb.execSQL("delete from " + TABLE_DATA + " where " + ADD_USER + " = " + name);


    }


    public ArrayList<HashMap<String ,String>> getaddress(){
        ArrayList<HashMap<String ,String>> list = new ArrayList<>();
        adddb= getReadableDatabase();
        String qry = " Select *  from "  + TABLE_DATA;
        Cursor cursor=adddb.rawQuery(qry,null);
        cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++){
            HashMap<String,String> mp = new HashMap<>();
            mp.put(ADD_USER,cursor.getString(cursor.getColumnIndex(ADD_USER)));
            mp.put(ADD_USERPHONE,cursor.getString(cursor.getColumnIndex(ADD_USERPHONE)));
            mp.put(ADD_USERHOUSENO, cursor.getString(cursor.getColumnIndex(ADD_USERHOUSENO)));
            mp.put(ADD_USERPIN, cursor.getString(cursor.getColumnIndex(ADD_USERPIN)));
            mp.put(ADD_USERCITY, cursor.getString(cursor.getColumnIndex(ADD_USERCITY)));
            mp.put(ADD_USERSTATE,cursor.getString(cursor.getColumnIndex(ADD_USERSTATE)));


            list.add(mp);
            cursor.moveToNext();


        }
        return list;
    }



}
