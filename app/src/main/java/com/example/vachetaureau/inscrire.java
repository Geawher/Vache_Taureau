package com.example.vachetaureau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class inscrire extends AppCompatActivity {

    EditText nom ;
    EditText passe;
    EditText passe2;
    TextView connexion;
    Button inscrire;
    DatabaseHelper db ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire);
        db = new DatabaseHelper(this);
        nom = (EditText) findViewById(R.id.nom);
        passe = (EditText) findViewById(R.id.passe);
        passe2 = (EditText) findViewById(R.id.passe2);
        inscrire = (Button) findViewById(R.id.inscrire);
        connexion = (TextView) findViewById(R.id.connexion);
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent connexionIntent = new Intent(inscrire.this, login.class);
                startActivity(connexionIntent);
            }
        });

        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String utilisateur = nom.getText().toString().trim();
                String pwd = passe.getText().toString().trim();
                String pwd2 = passe2.getText().toString().trim();
                if(utilisateur.equals("")){
                    Toast.makeText(inscrire.this, "Vous devez Saisir un nom correct", Toast.LENGTH_SHORT).show();}
                else if(pwd.equals("")){
                    Toast.makeText(inscrire.this, "Vous devez Saisir un Mot de passe ", Toast.LENGTH_SHORT).show();}
                else if  (pwd.equals(pwd2)) {
                    boolean res = db.inseret(utilisateur, pwd);
                    if ( res == true){
                        Intent enrg = new Intent(inscrire.this, login.class);
                        startActivity(enrg);}
                    else
                        Toast.makeText(inscrire.this, "Nom d'utilsateur Existe Déja ", Toast.LENGTH_SHORT).show();}
                else {
                    Toast.makeText(inscrire.this, "Verifier le Mot de passe", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }}
