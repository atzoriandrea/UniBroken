package com.example.mirko.unibroken;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaSegnRaga extends AppCompatActivity {
    public static final String SEGN="java.lang.Integer";
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";

    Button indietro;
    ListView lista;
    ArrayList<Segnalazione> segn = new ArrayList<Segnalazione>();
    Persona p1;
    Segnalazione s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_segn_raga);
        SegnFactory sf = SegnFactory.getInstance();
        segn = SegnFactory.getListaSegnalazioni();
        OptionActivityRaga.Adattatore a = new OptionActivityRaga.Adattatore(this,segn);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(HomeRaga.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();
        p1 = (Persona)obj;
        indietro = (Button)findViewById(R.id.back);
        lista = (ListView)findViewById(R.id.SegList);
        lista.setAdapter(a);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                s = (Segnalazione) lista.getItemAtPosition(position);
                Intent dettaglio = new Intent(ListaSegnRaga.this , Showsegn.class);
                dettaglio.putExtra(SEGN, new Integer(s.getId()));
                startActivity(dettaglio);

            }
        });
    }
    public void onBackPressed() {
        Intent indietro = new Intent(ListaSegnRaga.this, Homepage.class);
        indietro.putExtra(Homepage.PERSONA_EXTRA, p1);
        startActivity(indietro);
    }
    public void back(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        indietro.setBackgroundColor(Color.GREEN);
        onBackPressed();
    }
    public void logout(View view) {
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
                // Create intent to Open Image applications like Gallery, Google Photos
                Intent logout = new Intent(ListaSegnRaga.this, MainActivity.class);
                // Start the Intent
                startActivity(logout);
            }
        });

    }


}