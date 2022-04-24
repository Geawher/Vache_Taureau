package com.example.vachetaureau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText nom ;
    EditText passe;
   Button connexion ;
   TextView inscrire1;
    DatabaseHelper db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        connexion = (Button) findViewById(R.id.connexion);
        nom = (EditText) findViewById(R.id.nom);
        passe = (EditText) findViewById(R.id.passe);
        inscrire1 = (TextView) findViewById(R.id.inscrire);
        inscrire1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inscrireIntent = new Intent(login.this, inscrire.class);
                startActivity(inscrireIntent);
            }
        });



        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String utilisateur = nom.getText().toString().trim();
                String pwd = passe.getText().toString().trim();
                if(utilisateur.equals("")){
                Toast.makeText(login.this, "Vous devez Saisir un nom correct", Toast.LENGTH_SHORT).show();}
                if(utilisateur.equals("")){
                    Toast.makeText(login.this, "Vous devez Saisir un nom correct", Toast.LENGTH_SHORT).show();}
                else if(pwd.equals("")){
                    Toast.makeText(login.this, "Vous devez Saisir un Mot de passe ", Toast.LENGTH_SHORT).show();}
                else{
                    boolean res = db.connecter(utilisateur,pwd);
                    if (res == true) {
                        Toast.makeText(login.this, "Validation des Données", Toast.LENGTH_SHORT).show();
                        Intent enrg = new Intent(login.this, start.class);
                        enrg.putExtra("nom",utilisateur);
                        startActivity(enrg);
                    } else {

                        Toast.makeText(login.this, "Verifier Les Données", Toast.LENGTH_SHORT).show();
                    }}
            }
        });


    }








}