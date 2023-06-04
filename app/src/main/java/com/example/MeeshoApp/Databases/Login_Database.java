package com.example.MeeshoApp.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;

public class Login_Database extends SQLiteOpenHelper {

    public static final String DBNAME = "Login8.db";
    String usernamen="username1";

    public Login_Database(Context context) {
        super(context, "Login_new0.db", null, 5);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username Text,password TEXT,number TEXT,imag BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users ");

    }


    public boolean inesrtData(String username, String password,String number){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("imag","");
        contentValues.put("number",number);
        long result = MyDB.insert("users",null,contentValues);
        if (result==-1) return false;
        else
            return true;
    }


    public boolean insertnumber(String number, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",number);
        contentValues.put("imag","");
        long result = MyDB.insert("users",null,contentValues);
        if (result==-1) return false;
        else
            return true;
    }



    public boolean updateimage(String imag, String username) throws IOException {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imag",imag);
        long result = MyDB.update("users",values,"username = ?",new String[]{username});
        if (result==-1) return false;
        else
            return true;
    }


    public String getnumber(String username){
        String itemname ="";
//        MyDB = getReadableDatabase();
//        Cursor res =  MyDB.rawQuery( "select * from users where usernamen ="+username+"", null );
//        return res;
        SQLiteDatabase MyDB = getReadableDatabase();
        // Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        Cursor cursor = MyDB.rawQuery( "Select * from users where username = ?", new String[] {username}, null);

        if (cursor.moveToFirst()) {

            itemname =  cursor.getString(cursor.getColumnIndex("number"));

        }
        cursor.close();
        return  itemname;
    }



    public String getimage(String username){
        String itemname ="";
//        MyDB = getReadableDatabase();
//        Cursor res =  MyDB.rawQuery( "select * from users where usernamen ="+username+"", null );
//        return res;
        SQLiteDatabase MyDB = getReadableDatabase();
       // Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        Cursor cursor = MyDB.rawQuery( "Select * from users where username = ?", new String[] {username}, null);

        if (cursor.moveToFirst()) {

         itemname =  cursor.getString(cursor.getColumnIndex("imag"));

        }
        cursor.close();
        return  itemname;
    }


    public boolean updatepassword(String username ,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",password);
        long result = MyDB.update("users",contentValues,"username = ?",new String[] {username});
        if (result==-1) return false;
        else
            return true;
    }



    public boolean updateData(String newusername,String username)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("username",newusername);
        long result =  MyDB.update("users", cv, "username=?", new String[]{username});
        if (result==-1)
            return false;
        else
            return true;
    }

    public boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checknumber(String number){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where number = ?", new String[] {number});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }



    public boolean checkusernamepassword(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password =?", new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
