package com.example.mirko.unibroken;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;

public class ListaSegn extends AppCompatActivity {
    public static final String SEGN="java.lang.Integer";
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";

    Button indietro;
    ListView lista;
    TextView waiting, gestite;
    ArrayList<Segnalazione> segn = new ArrayList<Segnalazione>();
    Persona p1;
    Segnalazione s;
    TextView dropdown;
    OptionActivity.Adattatore a;
    String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_segn);
        SegnFactory sf = SegnFactory.getInstance();
        segn = SegnFactory.getListaSegnalazioni();
        a = new OptionActivity.Adattatore(ListaSegn.this,SegnFactory.getWaiting(true,segn));
        Intent intent = getIntent();
        waiting = (TextView)findViewById(R.id.waiting);
        gestite = (TextView)findViewById(R.id.gestite);
        dropdown = (TextView)findViewById(R.id.tipologia);//aggiunngere a xml
        Serializable obj = intent.getSerializableExtra(Homepage.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();
        dropdown.setText(SegnFactory.getSelectedCategory());
        p1 = (Persona)obj;
        indietro = (Button)findViewById(R.id.back);

        waiting.setPaintFlags(waiting.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        gestite.setPaintFlags(gestite.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        waiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waiting.setTextColor(Color.BLACK);
                waiting.setBackgroundResource(R.drawable.back_scelto);
                gestite.setTextColor(Color.WHITE);
                gestite.setBackgroundResource(R.drawable.backg_scelta);
                a = new OptionActivity.Adattatore(ListaSegn.this,SegnFactory.getWaiting(true,segn));
                lista.setAdapter(a);
            }
        });
        gestite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gestite.setTextColor(Color.BLACK);
                gestite.setBackgroundResource(R.drawable.back_scelto);
                waiting.setTextColor(Color.WHITE);
                waiting.setBackgroundResource(R.drawable.backg_scelta);
                a = new OptionActivity.Adattatore(ListaSegn.this,SegnFactory.getWaiting(false,segn));
                lista.setAdapter(a);

            }
        });

        lista = (ListView)findViewById(R.id.SegList);
        lista.setAdapter(a);
        lista.setScrollbarFadingEnabled(false);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3){
                s = (Segnalazione) lista.getItemAtPosition(position);
                Intent dettaglio = new Intent(ListaSegn.this , Showsegn.class);
                dettaglio.putExtra(PERSONA_EXTRA,p1);
                dettaglio.putExtra(SEGN, new Integer(s.getId()));
                startActivity(dettaglio);

            }
        });
        dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choose = new Intent(ListaSegn.this,ChooseType.class);
                choose.putExtra(PERSONA_EXTRA,p1);
                startActivity(choose);
            }
        });
        selected = SegnFactory.getSelectedCategory();
        if(!selected.equals("Tutte le segnalazioni")){
            a = new OptionActivity.Adattatore(ListaSegn.this,SegnFactory.getListaSegnalazioniByType(selected));
            lista.setAdapter(a);
        }
        else{
            a = new OptionActivity.Adattatore(ListaSegn.this,SegnFactory.getListaSegnalazioni());
            lista.setAdapter(a);
        }


    }
    public void onBackPressed() {
        Intent indietro = new Intent(ListaSegn.this, Homepage.class);
        SegnFactory.setSelectedCategory("Tutte le segnalazioni");
        indietro.putExtra(PERSONA_EXTRA, p1);
        startActivity(indietro);
    }
    public void back(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
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
                Intent logout = new Intent(ListaSegn.this, MainActivity.class);
                // Start the Intent
                startActivity(logout);
            }
        });

    }
    public String removeQty (String s){
        char [] array = s.toCharArray();
        int lenght = s.length();
        int fine = 0;
        String temp;
        for(int i = 0; i<lenght; i++){
            if (array[i]==' ' && array[i+1]=='(')
                fine = i;
        }
        temp = s.substring(0,fine);
        return temp;
    }

}
