package com.example.mirko.unibroken;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MySegn extends AppCompatActivity {
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";

    public static final String SEGN="java.lang.Integer";
    Button indietro;
    ListView lista;
    Persona p1;
    ArrayList<Segnalazione> segn = new ArrayList<Segnalazione>();
    Segnalazione s;
    Bitmap[] array = new Bitmap[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_segn);
        SegnFactory sf = SegnFactory.getInstance();
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Homepage.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();
        p1 = (Persona)obj;
        segn = SegnFactory.getListaSegnalazioniByAuthor(p1.getId());

        OptionActivity.Adattatore a = new OptionActivity.Adattatore(this,segn);
        lista = (ListView)findViewById(R.id.SegList);
        lista.setAdapter(a);
        indietro = (Button)findViewById(R.id.back);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                s = (Segnalazione) lista.getItemAtPosition(position);
                Intent dettaglio = new Intent(MySegn.this , MySegnDetail.class);
                dettaglio.putExtra(PERSONA_EXTRA,p1);
                dettaglio.putExtra(SEGN, new Integer(s.getId()));
                startActivity(dettaglio);

            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(MySegn.this, Homepage.class);
        indietro.putExtra(PERSONA_EXTRA, p1);
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
                Intent logout = new Intent(MySegn.this, MainActivity.class);
                // Start the Intent
                startActivity(logout);
            }
        });

    }
}
