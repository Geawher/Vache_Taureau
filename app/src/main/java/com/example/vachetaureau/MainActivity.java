package com.example.vachetaureau;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    Intent intent = new Intent() ;
    String nom ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final  basepartie2 bd = new basepartie2(this) ;
        int tt= getIntent().getIntExtra("wa9t",20);
        final int temps=tt*1000;
        final TextView t1 = findViewById(R.id.t1);


        final int[] T;

        {
            T = new int[4];
        }
        CreeT(T);
        final Button okbtn = (Button) findViewById(R.id.okbtn);
        final TextView textview = (TextView) findViewById(R.id.textView);
        textview.setText("");
        final int[] x = {getIntent().getIntExtra("nbre",10)};
        final int moins_score[]={ 1000/x[0]};
        final int score[]={1000};
        final ProgressBar Bar =(ProgressBar) findViewById(R.id.Bar);
        Bar.setMax(x[0]);
        Bar.setProgress(x[0]);


        EditText editText1 = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);




        View.OnKeyListener key=new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(!((EditText) v).toString().isEmpty())
                    v.focusSearch(View.FOCUS_RIGHT).requestFocus();

                return false;
            }
        };
        editText1.setOnKeyListener(key);
        editText2.setOnKeyListener(key);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        editText3.setOnKeyListener(key);
        ;

        final CountDownTimer CountDownTimerr = new CountDownTimer(temps, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                t1.setText(millisUntilFinished / 1000 + "sec left");
            }

            @Override
            public void onFinish() {
                t1.setText("Time is finished -" +moins_score[0] +"score ");
                x[0]--;
                score[0]-=moins_score[0];
                Bar.setProgress(x[0]);
                Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_SHORT).show();
            }


        };


          CountDownTimerr.start();


        okbtn.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {
                intent=getIntent();
                nom=intent.getStringExtra("nom1");
                CountDownTimerr.cancel();
                CountDownTimerr.start();
                x[0]--; //nb tentative restant
                score[0]-=moins_score[0]; // Score restant
                int essaie;
                EditText editText1 = (EditText) findViewById(R.id.editText);
                EditText editText2 = (EditText) findViewById(R.id.editText2);
                EditText editText3 = (EditText) findViewById(R.id.editText3);
                EditText editText4 = (EditText) findViewById(R.id.editText4);

               TextView textview3 = (TextView) findViewById(R.id.textView3);
                Bar.setProgress(x[0]);
                int N1= Integer.parseInt(editText1.getText().toString());
                int N2= Integer.parseInt(editText2.getText().toString());
                int N3= Integer.parseInt(editText3.getText().toString());
                int N4= Integer.parseInt(editText4.getText().toString());
                int [] T1 = new int [4];
                int Taurau=0,vache=0;
                T1[0]=N1;
                T1[1]=N2;
                T1[2]=N3;
                T1[3]=N4;
                for ( int i=0;i<4;i++){
                    for (int j=0;j<4;j++){
                        if (T[i]==T1[j] && i==j)
                            Taurau++;
                        if (T[i]==T1[j] && i!=j)
                            vache++;
                    }
                }
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                if (x[0] == 0)
                {
                if (Taurau==4)
                {  score[0]=moins_score[0];
                    String hist = textview.getText().toString();
                    bd.ajouterpartie(nom,score[0],hist);
                    textview.setText("Congratulations you won \n your score is "+score[0]);
                    okbtn.setVisibility(View.INVISIBLE);
                    final Button resbtn = (Button) findViewById(R.id.resbtn);
                    resbtn.setVisibility(View.VISIBLE);
                    resbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CreeT(T);
                            resbtn.setVisibility(View.INVISIBLE);
                            okbtn.setVisibility(View.VISIBLE);
                            textview.setText("");
                            x[0] =10;
                            score[0]=1000;
                        }
                    });
                }
                 else   { CountDownTimerr.cancel();
                    String hist = textview.getText().toString();
                    bd.ajouterpartie(nom,score[0],hist);
                        textview.setText("You lost try again");
                        editText1.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                        okbtn.setVisibility(View.INVISIBLE);
                        final Button resbtn = (Button) findViewById(R.id.resbtn);
                        resbtn.setVisibility(View.VISIBLE);
                        resbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CreeT(T);
                                resbtn.setVisibility(View.INVISIBLE);
                                okbtn.setVisibility(View.VISIBLE);
                                textview.setText("");
                                x[0] =10;
                                score[0]=1000;
                                Bar.setProgress(x[0]);
                                CountDownTimerr.start();
                            }
                        });
                    }}
                else
                {
                    if (Taurau==4)
                {   score[0]+=moins_score[0];
                    String hist = textview.getText().toString();
                    bd.ajouterpartie(nom,score[0],hist);
                    textview.setText("Congratulations you won \n your score is "+score[0]);
                    okbtn.setVisibility(View.INVISIBLE);
                    final Button resbtn = (Button) findViewById(R.id.resbtn);
                    resbtn.setVisibility(View.VISIBLE);
                    resbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CreeT(T);
                            resbtn.setVisibility(View.INVISIBLE);
                            okbtn.setVisibility(View.VISIBLE);
                            textview.setText("");
                            x[0] =10;
                            score[0]=1000;
                        }
                    });
                }
                    else{
                    essaie=N1*1000+N2*100+N3*10+N4;
                    textview.setText(String.format("   %d %d Taurau et%d Vache \n%s",  essaie, Taurau, vache,textview.getText()));
                    textview3.setText("Your score is : "+score[0]+"\n Yous still have "+x[0]+" attempt");
                    }
                }

            }



        }) ;
    }








    public static void CreeT (int T[])
    {

        int i = 0;

        while (i < 4) {
            int var = (int) (Math.random() *9+0 );
            boolean existe = false;
            int j = 0;
            while (!existe && j < i) {
                if (T[j] == var) {
                    existe = true;
                }
                else j++;

            }
            if (!existe) {
                T[i] = var;
                i++;
            }
        }
    }

}
