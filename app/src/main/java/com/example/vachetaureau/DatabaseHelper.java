package com.example.vachetaureau;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "base.db";
    public static final String TABLE_NAME1 = "Joueur";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";






    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Joueur (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(sqLiteDatabase);
    }


    public boolean inseret (String nom , String pwd ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeur = new ContentValues() ;
        valeur.put("username",nom);
        valeur.put("password",pwd);
        String ch ="Select * From Joueur where username = ?";
        Cursor c = db.rawQuery(ch,new String[]{nom});
        if (c.getCount()>0)
            return false;
        else {
            db.insert(TABLE_NAME1,null,valeur);
            return true;}}

    public boolean  connecter (String nom , String pwd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeur = new ContentValues() ;
        valeur.put("username",nom);
        valeur.put("password",pwd);
        String ch ="Select * From Joueur where username = ? AND password = ? ";
        Cursor c = db.rawQuery(ch,new String[]{nom,pwd});
        if (c.getCount()>0)
            return true  ;
        else
            return false ; }





    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String ch="Select * From " +TABLE_NAME1 ;
        Cursor data=db.rawQuery(ch,null);
        return data ;
    }


}











