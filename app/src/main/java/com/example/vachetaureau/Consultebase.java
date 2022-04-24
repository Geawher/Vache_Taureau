package com.example.vachetaureau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Consultebase extends AppCompatActivity {

    basepartie2 db ;
    Intent intent= new Intent();
    String nom ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultebase);
        ListView liste=(ListView)findViewById(R.id.liste);
        intent=getIntent();
        nom=intent.getStringExtra("nom1");
        db = new basepartie2(this);
        Cursor data = db.getData(nom);
        ArrayList<String> liste1 =new ArrayList<>() ;
        liste1.add("L'historique Du Joueur") ;
        while (data.moveToNext()){
            liste1.add("username");
            liste1.add(data.getString(1));
            liste1.add("Score");
            liste1.add(data.getString(2));
            liste1.add("Historique");
            liste1.add(data.getString(3));
        }
        ListAdapter adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1,liste1);
        liste.setAdapter(adapter);
    }



}
