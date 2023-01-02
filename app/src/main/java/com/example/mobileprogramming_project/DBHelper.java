package com.example.mobileprogramming_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "UFO";
    public static final String table_name = "table_login";

    public static final String row_id ="_id";
    public static final String row_name ="Username";
    public static final String row_email ="Email";
    public static final String row_password ="Password";
    public static final String row_dob="DOB";
    public static final String row_class="Class";

    private SQLiteDatabase db;

    public DBHelper(Context context ) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + row_id + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + row_name + "TEXT, " + row_email + "TEXT, " + row_password + "TEXT, " + row_dob + "TEXT, " + row_class + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }

    //Insert Data
    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }

    public boolean checkUser(String email, String password){
        String[] columns = {row_id};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_email + "=?" + " and " + row_password + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();
        db.close();

        if(count > 0){
            return true;
        }else{
            return false;
        }
    }
}
