package com.example.MeeshoApp.Databases;



import static com.example.MeeshoApp.common.constant.MY_ORDER_DATE;
import static com.example.MeeshoApp.common.constant.MY_ORDER_ID;
import static com.example.MeeshoApp.common.constant.MY_ORDER_IMAGE;
import static com.example.MeeshoApp.common.constant.MY_ORDER_NAME;
import static com.example.MeeshoApp.common.constant.MY_ORDER_PRICE;
import static com.example.MeeshoApp.common.constant.MY_ORDER_Quantity;
import static com.example.MeeshoApp.common.constant.MY_ORDER_SOLDTO;
import static com.example.MeeshoApp.common.constant.MY_ORDER_SUPPLIER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class My_orderdatabse extends SQLiteOpenHelper {
    SQLiteDatabase orderdb;
    private  static final String dbname1 = "orderdb2";
    private  static  final String  TABLE_DATA= "my_order";
    private  static final int DATABASE_VERSION =3;


    public My_orderdatabse(@Nullable Context context) {
        super(context,dbname1,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase orderdb) {
        this.orderdb = orderdb;
        orderdb.execSQL("create Table "+ TABLE_DATA +" (order_id integer not null primary key ," +
                "order_image BLOB,cart_name TEXT ," +
                "order_name TEXT," +
                "order_quantity TEXT," +
                "order_date TEXT," +
                "order_sold TEXT," +
                "order_supplier TEXT," +
                "order_price TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase orderdb, int i, int i1) {
        orderdb.execSQL("drop Table if exists "+ TABLE_DATA);
    }

    public boolean myorderdata(HashMap<String,String> order){
        orderdb = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MY_ORDER_ID,order.get(MY_ORDER_ID));
        cv.put(MY_ORDER_IMAGE,order.get(MY_ORDER_IMAGE));
        cv.put(MY_ORDER_NAME,order.get(MY_ORDER_NAME));
        cv.put(MY_ORDER_PRICE,order.get(MY_ORDER_PRICE));
        cv.put(MY_ORDER_Quantity,order.get(MY_ORDER_Quantity));
        cv.put(MY_ORDER_DATE,order.get(MY_ORDER_DATE));
        cv.put(MY_ORDER_SOLDTO,order.get(MY_ORDER_SOLDTO));
        cv.put(MY_ORDER_SUPPLIER,order.get(MY_ORDER_SUPPLIER));
        orderdb.insert(TABLE_DATA,null,cv);
        return true;
    }


    public boolean updateCart(String coloumnid,String cartquantity)
    {

        orderdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cart_quantity",cartquantity);
        orderdb.update(TABLE_DATA, values, "column_id=?", new String[]{coloumnid});

        orderdb.close();
        Log.e("sjnxnsa", "updateCourse: "+coloumnid);
        Log.e("updateCourse", "updateCourse: "+cartquantity );


        return true;
    }




    public ArrayList<HashMap<String ,String>> getorderdata(){
        ArrayList<HashMap<String ,String>> list = new ArrayList<>();
        orderdb= getReadableDatabase();
        String qry = " Select *  from "  + TABLE_DATA;
        Cursor cursor=orderdb.rawQuery(qry,null);
        cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++){
            HashMap<String,String> mp = new HashMap<>();
            mp.put(MY_ORDER_ID,cursor.getString(cursor.getColumnIndex(MY_ORDER_ID)));
            mp.put(MY_ORDER_IMAGE,cursor.getString(cursor.getColumnIndex(MY_ORDER_IMAGE)));
            mp.put(MY_ORDER_NAME, cursor.getString(cursor.getColumnIndex(MY_ORDER_NAME)));
            mp.put(MY_ORDER_PRICE, cursor.getString(cursor.getColumnIndex(MY_ORDER_PRICE)));
            mp.put(MY_ORDER_Quantity,cursor.getString(cursor.getColumnIndex(MY_ORDER_Quantity)));
            mp.put(MY_ORDER_DATE, cursor.getString(cursor.getColumnIndex(MY_ORDER_DATE)));
            mp.put(MY_ORDER_SOLDTO, cursor.getString(cursor.getColumnIndex(MY_ORDER_SOLDTO)));
            mp.put(MY_ORDER_SUPPLIER,cursor.getString(cursor.getColumnIndex(MY_ORDER_SUPPLIER)));
            list.add(mp);
            cursor.moveToNext();

        }
        return list;
    }


}
