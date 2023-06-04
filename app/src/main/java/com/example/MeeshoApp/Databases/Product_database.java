package com.example.MeeshoApp.Databases;

import static com.example.MeeshoApp.common.constant.CART_COLUMN_KEY;
import static com.example.MeeshoApp.common.constant.CART_IMAGE_KEY;
import static com.example.MeeshoApp.common.constant.CART_NAME_KEY;
import static com.example.MeeshoApp.common.constant.CART_OFFERS_KEY;
import static com.example.MeeshoApp.common.constant.CART_PRICE_KEY;
import static com.example.MeeshoApp.common.constant.CART_QUANTITY_KEY;
import static com.example.MeeshoApp.common.constant.PRO_DELIEVERY;
import static com.example.MeeshoApp.common.constant.PRO_ID;
import static com.example.MeeshoApp.common.constant.PRO_IMAGE;
import static com.example.MeeshoApp.common.constant.PRO_NAME;
import static com.example.MeeshoApp.common.constant.PRO_OFFERS;
import static com.example.MeeshoApp.common.constant.PRO_PRICE;
import static com.example.MeeshoApp.common.constant.PRO_QUANTITY;
import static com.example.MeeshoApp.common.constant.PRO_RATING;
import static com.example.MeeshoApp.common.constant.PRO_SATUS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Product_database extends SQLiteOpenHelper {

    SQLiteDatabase productdb;
    private static final String dbname = "product3_db";
    private static final String TABLE_DATA = "products";

    private static final int DATABASE_VERSION = 4;

    public Product_database(@Nullable Context context) {
        super(context,dbname, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase productdb) {
        this.productdb = productdb;
        productdb.execSQL("create Table " + TABLE_DATA + " (prod_id integer not null primary key ," +
                "product_image BLOB,product_name TEXT ," +
                "product_price TEXT," +
                "product_offer TEXT," +
                "prod_status TEXT," +
                "prod_rat TEXT," +
                "prod_del TEXT," +
                "product_quantity TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase productdb, int i, int i1) {
        productdb.execSQL("drop Table if exists " + TABLE_DATA);
    }

    public boolean insert_products_item(ArrayList<HashMap<String, String>> products_item){
//        HashMap<String,String> h_mp = new HashMap<>();
        productdb= getWritableDatabase();
        ContentValues values = new ContentValues();
          for (int i=0;i<products_item.size();i++) {
              values.put(PRO_ID, products_item.get(i).get(PRO_ID));
              values.put(PRO_IMAGE, products_item.get(i).get(PRO_IMAGE));
              values.put(PRO_NAME,products_item.get(i).get(PRO_NAME));
              values.put(PRO_PRICE,products_item.get(i).get(PRO_PRICE));
              values.put(PRO_OFFERS, products_item.get(i).get(PRO_OFFERS));
              values.put(PRO_DELIEVERY,products_item.get(i).get(PRO_DELIEVERY));
              values.put(PRO_RATING, products_item.get(i).get(PRO_RATING));
              values.put(PRO_SATUS, products_item.get(i).get(PRO_SATUS));
              values.put(PRO_QUANTITY, products_item.get(i).get(PRO_QUANTITY));
          }
        productdb.insert(TABLE_DATA, null, values);
                return true;
    }
    public boolean update_quantity(String product_id,String prod_qty)
    {
        productdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_quantity",prod_qty);
        productdb.update(TABLE_DATA, values, "prod_id=?", new String[]{product_id});
        productdb.close();
        Log.e("prod_quantity", "kkkkk: "+prod_qty );
        return true;
    }

    public ArrayList<HashMap<String ,String>> getproducts(){
        ArrayList<HashMap<String ,String>> Plist = new ArrayList<>();
        productdb= getReadableDatabase();
        String qry = " Select *  from "  + TABLE_DATA;
        Cursor cursor=productdb.rawQuery(qry,null);
        cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++){
            HashMap<String,String> mp = new HashMap<>();
            mp.put(PRO_ID,cursor.getString(cursor.getColumnIndex(PRO_ID)));
            mp.put(PRO_IMAGE,cursor.getString(cursor.getColumnIndex(PRO_IMAGE)));
            mp.put(PRO_NAME, cursor.getString(cursor.getColumnIndex(PRO_NAME)));
            mp.put(PRO_PRICE, cursor.getString(cursor.getColumnIndex(PRO_PRICE)));
            mp.put(PRO_OFFERS, cursor.getString(cursor.getColumnIndex(PRO_OFFERS)));
            mp.put(PRO_QUANTITY,cursor.getString(cursor.getColumnIndex(PRO_QUANTITY)));
            mp.put(PRO_DELIEVERY, cursor.getString(cursor.getColumnIndex(PRO_DELIEVERY)));
            mp.put(PRO_RATING, cursor.getString(cursor.getColumnIndex(PRO_RATING)));
            mp.put(PRO_SATUS,cursor.getString(cursor.getColumnIndex(PRO_SATUS)));
            Plist.add(mp);
            cursor.moveToNext();
        }
        return Plist;
    }
}
