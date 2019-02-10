package com.example.mirko.unibroken;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class ShowsegnRaga extends AppCompatActivity {
    TextView titolo;
    ImageView img;
    TextView descrizione;
    TextView luogo;
    Segnalazione s;
    Bitmap[] array = new Bitmap[3];
    TextView costoIntervento;
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsegn);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(ListaSegn.SEGN);
        Serializable objP = intent.getSerializableExtra(HomeRaga.PERSONA_EXTRA);

        Bundle bundle = getIntent().getExtras();
        array[0] = BitmapFactory.decodeResource(getResources(),R.drawable.foto_655402_908x560);
        array[1] = BitmapFactory.decodeResource(getResources(),R.drawable.foto_655404_514x318);
        array[2] = BitmapFactory.decodeResource(getResources(),R.drawable.foto_693184_908x560);
        SegnFactory sf = SegnFactory.getInstance();
        InterventiFactory ifact = InterventiFactory.getInstance();
        int i = (Integer) obj;
        s = SegnFactory.getSegnalazioneById(i);
        //s = (Segnalazione) obj;
        titolo = (TextView)findViewById(R.id.tit);
        img = (ImageView)findViewById(R.id.imgdett);
        descrizione = (TextView)findViewById(R.id.des);
        luogo = (TextView)findViewById(R.id.loc);
        descrizione.setText(s.getTesto());
        img.setImageBitmap(s.getImage().get(s.getImage().size()-1));
        costoIntervento=(TextView)findViewById(R.id.costo);
        double aux=(ifact.getInterventoById(s.getIdIntervento()).getImporto());
        costoIntervento.setText(String.valueOf(aux));

    }
}
