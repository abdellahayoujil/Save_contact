package com.example.save;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db_save extends SQLiteOpenHelper {


    private final static String DATABASE="DATABASE";
    private  final static int VERSION=1;
    public Db_save(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table save_table(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,number TEXT ,email TEXT, adresse TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists save_table");

    }


    public void insertData(String name,String number,String email,String adresse){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("number",number);
        contentValues.put("email",email);
        contentValues.put("adresse",adresse);
        database.insert("save_table ",null,contentValues);
    }

    public Cursor showData(){

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from save_table order by name asc ",null);
        return cursor;
    }

    public void deleteData(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("save_table","id=?",new String[]{id});
    }
    public void updateData(String name,String number,String email,String adresse,String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("number",number);
        contentValues.put("email",email);
        contentValues.put("adresse",adresse);

        sqLiteDatabase.update("save_table",contentValues,"id=?",new String[]{id});
    }

    public Cursor searchByName(String searchName) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM save_table WHERE name LIKE ? ORDER BY name ASC";
        String[] selectionArgs = new String[]{"%" + searchName + "%"};
        return database.rawQuery(query, selectionArgs);
    }
}
