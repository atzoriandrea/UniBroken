package com.example.mirko.unibroken;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class ShowsegnRaga extends AppCompatActivity {
    TextView titolo;
    ImageView img;
    Button indietro;
    TextView descrizione;
    TextView testotitolo;
    TextView luogo;
    TextView costo;
    TextView testoluogo;
    Segnalazione s;
    //Bitmap[] array = new Bitmap[1];
    public static final String BITMAP_EXTRA="java.lang.Integer";
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsegn_raga);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(ListaSegnRaga.SEGN);
        Bundle bundle = getIntent().getExtras();
        SegnFactory sf = SegnFactory.getInstance();
        InterventiFactory ifact = InterventiFactory.getInstance();
        int i = (Integer) obj;
        s = SegnFactory.getSegnalazioneById(i);
        //s = (Segnalazione) obj;
        indietro = (Button)findViewById(R.id.back);
        titolo = (TextView)findViewById(R.id.tit);
        titolo.setText("Segnalazione #"+String.valueOf(s.getId()));
        img = (ImageView)findViewById(R.id.imgdett);
        //testotitolo = (TextView)findViewById(R.id.tittext);
        descrizione = (TextView)findViewById(R.id.des);
        luogo = (TextView)findViewById(R.id.loc);
        //testoluogo = (TextView)findViewById(R.id.loctext);
        descrizione.setText(s.getTesto());
        //testotitolo.setText(s.getTipo());
        luogo.setText("Luogo: " + s.getLuogo());
        costo = (TextView)findViewById(R.id.costo);
        costo.setText("Costo approssimato: "+String.valueOf((ifact.getInterventoById(s.getIdIntervento()).getImporto())));
        img.setImageBitmap(s.getImage().get(s.getImage().size()-1));
        //array[0] = s.getImage();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switcher = new Intent(ShowsegnRaga.this,ImgSwitcherShow.class);
                switcher.putExtra(BITMAP_EXTRA, s.getId());
                startActivity(switcher);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(ShowsegnRaga.this, ListaSegnRaga.class);
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
                Intent logout = new Intent(ShowsegnRaga.this, MainActivity.class);
                // Start the Intent
                startActivity(logout);
            }
        });

    }
    public void back(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        indietro.setBackgroundColor(Color.GREEN);
        onBackPressed();
    }

}
