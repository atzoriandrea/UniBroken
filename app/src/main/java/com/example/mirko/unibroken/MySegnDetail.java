package com.example.mirko.unibroken;

import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class MySegnDetail extends AppCompatActivity {
    public static final String PERSONA_EXTRA="com.example.mirko.esercitazionebonus.Persona";

    TextView titolo;
    ImageView img;
    TextView descrizione;
    TextView luogo;
    Segnalazione s;
    Button elimina;
    Persona p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SegnFactory factory = SegnFactory.getInstance(this);
        final Context c = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_segn_detail);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(MySegn.SEGN);
        Serializable obj2 = intent.getSerializableExtra(MySegn.PERSONA_EXTRA);

        Bundle bundle = getIntent().getExtras();
        s = (Segnalazione) obj;
        p = (Persona) obj2;
        titolo = (TextView)findViewById(R.id.tit);
        img = (ImageView)findViewById(R.id.imgdett);
        descrizione = (TextView)findViewById(R.id.des);
        luogo = (TextView)findViewById(R.id.loc);
        elimina = (Button)findViewById(R.id.elimina);
        descrizione.setText(s.getTesto());
        img.setImageBitmap(s.getImage());

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SegnFactory.remSegnalazioneById(s.getId(), c);
                Intent segnalazioni = new Intent(MySegnDetail.this, MySegn.class);
                segnalazioni.putExtra(PERSONA_EXTRA, p);
                startActivity(segnalazioni);
            }
        });
    }
}
