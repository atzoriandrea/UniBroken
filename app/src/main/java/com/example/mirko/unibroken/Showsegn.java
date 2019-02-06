package com.example.mirko.unibroken;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class Showsegn extends AppCompatActivity {
    TextView titolo;
    ImageView img;
    TextView descrizione;
    TextView luogo;
    Segnalazione s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsegn);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(ListaSegn.SEGN);
        Bundle bundle = getIntent().getExtras();
        s = (Segnalazione) obj;
        titolo = (TextView)findViewById(R.id.tit);
        img = (ImageView)findViewById(R.id.imgdett);
        descrizione = (TextView)findViewById(R.id.des);
        luogo = (TextView)findViewById(R.id.loc);
        descrizione.setText(s.getTesto());
        img.setImageResource(s.getImage());


    }
}
