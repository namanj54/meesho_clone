package com.example.MeeshoApp.Databases;


import static com.example.MeeshoApp.common.constant.CART_COLUMN_KEY;
import static com.example.MeeshoApp.common.constant.CART_IMAGE_KEY;
import static com.example.MeeshoApp.common.constant.CART_NAME_KEY;
import static com.example.MeeshoApp.common.constant.CART_OFFERS_KEY;
import static com.example.MeeshoApp.common.constant.CART_PRICE_KEY;
import static com.example.MeeshoApp.common.constant.CART_QUANTITY_KEY;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart_database extends SQLiteOpenHelper {
    SQLiteDatabase cartdb;
    private  static final String dbname1 = "cc4db";
    private  static  final String  TABLE_DATA= "cart";
    private  static final int DATABASE_VERSION =15;


    public Cart_database(@Nullable Context context) {
        super(context,dbname1,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase cartdb) {
        this.cartdb = cartdb;
        cartdb.execSQL("create Table "+ TABLE_DATA +" (column_id integer not null primary key ," +
                "cart_image BLOB,cart_name TEXT ," +
                "cart_price TEXT," +
                "cart_offer TEXT," +
                "cart_quantity TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase cartdb, int i, int i1) {
        cartdb.execSQL("drop Table if exists "+ TABLE_DATA);
    }

    public boolean insertcartData(HashMap<String,String> cart){
        cartdb = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CART_COLUMN_KEY,cart.get(CART_COLUMN_KEY));
        cv.put(CART_IMAGE_KEY,cart.get(CART_IMAGE_KEY));
        cv.put(CART_NAME_KEY,cart.get(CART_NAME_KEY));
        cv.put(CART_PRICE_KEY,cart.get(CART_PRICE_KEY));
        cv.put(CART_OFFERS_KEY,cart.get(CART_OFFERS_KEY));
        cv.put(CART_QUANTITY_KEY,cart.get(CART_QUANTITY_KEY));
        cartdb.insert(TABLE_DATA,null,cv);
        return true;
    }


    public boolean updateCart(String coloumnid,String cartquantity)
    {
         cartdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cart_quantity",cartquantity);
        cartdb.update(TABLE_DATA, values, "column_id=?", new String[]{coloumnid});
        cartdb.close();
        Log.e("sjnxnsa", "updateCourse: "+coloumnid);
        Log.e("updateCourse", "updateCourse: "+cartquantity );
        return true;
    }

    public boolean updte_pr_quantity(String product_id,String cartquantity)
    {
        // calling a method to get writable database.
        cartdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cart_quantity",cartquantity);
        cartdb.update(TABLE_DATA, values, "column_id=?", new String[]{product_id});
        cartdb.close();
        Log.e("updateCourse", "updateCourse: "+cartquantity );
        return true;
    }

    public void removefromcart(String column_id1) {
        Log.e("remove_column_id", "removefromcart: "+CART_COLUMN_KEY );
        Log.e("bvuygyugyug", "removefromcart: "+column_id1);
        cartdb = getReadableDatabase();
        cartdb.execSQL("delete from " + TABLE_DATA + " where " + CART_COLUMN_KEY + " = " + column_id1);


    }


    public ArrayList<HashMap<String ,String>> getcartitem(){
        ArrayList<HashMap<String ,String>> list = new ArrayList<>();
        cartdb= getReadableDatabase();
        String qry = " Select *  from "  + TABLE_DATA;
        Cursor cursor=cartdb.rawQuery(qry,null);
        cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++){
            HashMap<String,String> mp = new HashMap<>();
            mp.put(CART_COLUMN_KEY,cursor.getString(cursor.getColumnIndex(CART_COLUMN_KEY)));
            mp.put(CART_IMAGE_KEY,cursor.getString(cursor.getColumnIndex(CART_IMAGE_KEY)));
            mp.put(CART_NAME_KEY, cursor.getString(cursor.getColumnIndex(CART_NAME_KEY)));
            mp.put(CART_PRICE_KEY, cursor.getString(cursor.getColumnIndex(CART_PRICE_KEY)));
            mp.put(CART_OFFERS_KEY, cursor.getString(cursor.getColumnIndex(CART_OFFERS_KEY)));
            mp.put(CART_QUANTITY_KEY,cursor.getString(cursor.getColumnIndex(CART_QUANTITY_KEY)));


            list.add(mp);
            cursor.moveToNext();


        }
        return list;
    }




}
