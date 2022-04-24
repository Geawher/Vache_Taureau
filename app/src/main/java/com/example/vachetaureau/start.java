package com.example.vachetaureau;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class start extends AppCompatActivity {
   int nb_tenta;
   int waqt;
   Intent intent = new Intent() ;
    final String nom = intent.getStringExtra("nom");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        intent=getIntent();
        final String nom = intent.getStringExtra("nom");

        Button help=(Button) findViewById(R.id.button);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView Image=(ImageView) findViewById(R.id.imageView);
                Button close=(Button) findViewById(R.id.button2);
                Image.setVisibility(View.VISIBLE);
                close.setVisibility(View.VISIBLE);
            }
        });

        Button close=(Button) findViewById(R.id.button2);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView Image=(ImageView) findViewById(R.id.imageView);
                Button close1=(Button) findViewById(R.id.button2);
                Image.setVisibility(View.GONE);
                close1.setVisibility(View.GONE);
            }
        });






        Button base = (Button)findViewById(R.id.base);
        final   RadioButton mono = (RadioButton) findViewById(R.id.mono);
        final   RadioButton multi = (RadioButton) findViewById(R.id.multi);
        final   RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
        final   RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
        final   RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);
        final TextView textview2 = (TextView) findViewById(R.id.textView2);
        final RadioGroup radio = (RadioGroup) findViewById(R.id.radio) ;
        radio.setVisibility(View.GONE);
        mono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview2.setText("");
                radio.setVisibility(View.VISIBLE);
            }
        });
        multi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textview2.setText("");
                radio.setVisibility(View.GONE);
            }
        });

        base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent base = new Intent(start.this, Consultebase.class);
                base.putExtra("nom1",nom);
                startActivity(base);
            }
        });












        final   Button startbtn = (Button) findViewById(R.id.startbtn);
        startbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!mono.isChecked() && !multi.isChecked())
                {
                    textview2.setText("You have to choose a mode");
                }
                else if ( mono.isChecked() && (!radio1.isChecked() && !radio2.isChecked() && !radio3.isChecked() ))
                {
                    textview2.setText("You have to choose a level");
                }
                else if (mono.isChecked() && ( radio1.isChecked() || radio2.isChecked() || radio3.isChecked() ))
                {
                  if (radio1.isChecked())
                  {
                      waqt=26;
                      nb_tenta=20;
                  }
                    if (radio2.isChecked())
                    {
                        waqt=21;
                        nb_tenta=10;
                    }
                    if (radio3.isChecked())
                    {
                        waqt=16;
                        nb_tenta=5;
                    }


                    openactivityplay(); }
                else if (multi.isChecked())
                {
                    textview2.setText("Stay Tuned");
                }


            }
            public int getNb()
            {
             return nb_tenta;
            }
        });
    }
   public void openactivityplay (){
       final String nom = intent.getStringExtra("nom");
        Intent intent = new Intent(getBaseContext() , MainActivity.class);
        intent.putExtra("nbre",nb_tenta);
        intent.putExtra("wa9t",waqt);
        intent.putExtra("nom1",nom);
        startActivity(intent);
    }
}
