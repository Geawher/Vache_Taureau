package com.example.vachetaureau;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class basepartie2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "basepartie2.db";
    public static final String TABLE_NAME1 = "partie";

    public basepartie2(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE partie (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, score INTEGER, hist TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }


    public void  ajouterpartie(String nom , int score , String hist ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeur = new ContentValues();
        valeur.put("score", score);
        valeur.put("username",nom);
        valeur.put("hist",hist);
        db.insert(TABLE_NAME1, null, valeur);
    }




    public Cursor getData(String nom) {
        SQLiteDatabase db = this.getWritableDatabase();
        String ch ="Select * From partie where username = ?";
        Cursor data = db.rawQuery(ch,new String[]{nom});
        return data;
    }
}