package com.example.MeeshoApp.Databases;


import static com.example.MeeshoApp.common.constant.PRODUCT_IMAGE_KEY;
import static com.example.MeeshoApp.common.constant.PRODUCT_NAME_KEY;
import static com.example.MeeshoApp.common.constant.PRODUCT_OFFERS_KEY;
import static com.example.MeeshoApp.common.constant.PRODUCT_PRICE_KEY;
import static com.example.MeeshoApp.common.constant.WISH_COLOUMN;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class  Wishlist_database extends SQLiteOpenHelper {
    SQLiteDatabase wishdb;


    private  static final String dbname = "wishlist6.db";
    private  static  final String  TABLE_DATA= "wishlist";
    private  static final int DATABASE_VERSION =9;

    public Wishlist_database(Context context) {
        super(context,dbname,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase wishdb) {
        this.wishdb=wishdb;

        wishdb.execSQL("create Table "+ TABLE_DATA +" (wish_id integer not null primary key,wish_image BLOB,wish_name TEXT ," +
                "wish_price TEXT,wish_offer TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase wishdb, int i, int i1) {
        wishdb.execSQL("drop Table if exists "+ TABLE_DATA);

    }
    public boolean insertwishData(HashMap<String,String> wishes){
       wishdb = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(WISH_COLOUMN,wishes.get(WISH_COLOUMN));
        cv.put(PRODUCT_IMAGE_KEY,wishes.get(PRODUCT_IMAGE_KEY));
        cv.put(PRODUCT_NAME_KEY,wishes.get(PRODUCT_NAME_KEY));
        cv.put(PRODUCT_PRICE_KEY,wishes.get(PRODUCT_PRICE_KEY));
        cv.put(PRODUCT_OFFERS_KEY,wishes.get(PRODUCT_OFFERS_KEY));

        wishdb.insert(TABLE_DATA,null,cv);
        return true;

    }

    public void removewish(String wishes_id) {
        wishdb = getReadableDatabase();
        wishdb.execSQL("delete from " + TABLE_DATA + " where " + WISH_COLOUMN + " = " + wishes_id);

    }


     public ArrayList<HashMap<String ,String>> getWishList(){
        ArrayList<HashMap<String ,String>> list = new ArrayList<>();
        wishdb= getReadableDatabase();
        String qry = " Select *  from "  + TABLE_DATA;
        Cursor cursor=wishdb.rawQuery(qry,null);
        cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++){
            HashMap<String,String> msp = new HashMap<>();
            msp.put(WISH_COLOUMN,cursor.getString(cursor.getColumnIndex(WISH_COLOUMN)));
            msp.put(PRODUCT_IMAGE_KEY,cursor.getString(cursor.getColumnIndex(PRODUCT_IMAGE_KEY)));
            msp.put(PRODUCT_NAME_KEY, cursor.getString(cursor.getColumnIndex(PRODUCT_NAME_KEY)));
            msp.put(PRODUCT_PRICE_KEY, cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE_KEY)));
            msp.put(PRODUCT_OFFERS_KEY, cursor.getString(cursor.getColumnIndex(PRODUCT_OFFERS_KEY)));


            list.add(msp);
            cursor.moveToNext();
        }


         return list;
     }


}
