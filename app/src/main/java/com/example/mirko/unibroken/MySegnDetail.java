package com.example.mirko.unibroken;

import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class MySegnDetail extends AppCompatActivity {
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";

    TextView titolo;
    ImageView img;
    TextView descrizione;
    TextView luogo;
    Button indietro;
    Segnalazione s;
    Button elimina;
    Persona p;
    Context c;
    Bitmap[] array = new Bitmap[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_segn_detail);
        SegnFactory factory = SegnFactory.getInstance();
        c = this;
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(MySegn.SEGN);
        Serializable obj2 = intent.getSerializableExtra(MySegn.PERSONA_EXTRA);

        Bundle bundle = getIntent().getExtras();
        int i = (Integer) obj;
        s = SegnFactory.getSegnalazioneById(i);
        //s = (Segnalazione) obj;
        p = (Persona) obj2;
        titolo = (TextView)findViewById(R.id.tit);
        img = (ImageView)findViewById(R.id.imgdett);
        descrizione = (TextView)findViewById(R.id.des);
        luogo = (TextView)findViewById(R.id.loc);
        elimina = (Button)findViewById(R.id.elimina);
        indietro = (Button)findViewById(R.id.back);
        descrizione.setText(s.getTesto());
        img.setImageBitmap(s.getImage().get(s.getImage().size()-1));

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(c)
                        .setTitle("Elimina segnalazione")
                        .setMessage("Sei sicuro di voler rimuovere la segnalazione?")
                        .setPositiveButton("CONFERMA", null)
                        .setNegativeButton("ANNULLA", null)
                        .show();


                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SegnFactory.remSegnalazioneById(s.getId());
                        Intent segnalazioni = new Intent(MySegnDetail.this, MySegn.class);
                        segnalazioni.putExtra(PERSONA_EXTRA, p);
                        startActivity(segnalazioni);
                    }
                });

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(MySegnDetail.this, MySegn.class);
        indietro.putExtra(PERSONA_EXTRA, p);
        startActivity(indietro);
    }
    public void logout(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Sei sicuro di voler eseguire il logout?")
                .setPositiveButton("CONFERMA", null)
                .setNegativeButton("ANNULLA", null)
                .show();


        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(MySegnDetail.this, MainActivity.class);
                // Start the Intent
                startActivity(logout);
            }
        });

    }
    public void back(View view) {
        onBackPressed();
    }

}
