package com.example.mirko.unibroken;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.io.Serializable;

public class Homepage extends AppCompatActivity {
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";
    Persona p1;
    Persona p2;
    TextView r;
    Button invia;
    Button segnalazioni;
    Button mio;
    Button indietro;
    Button indietroB;


    private Toolbar mTopToolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PersonaFactory pf = PersonaFactory.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        segnalazioni = (Button)findViewById(R.id.mostra);
        mio = (Button)findViewById(R.id.visualizzaMieSegnalazioni);
        invia = (Button)findViewById(R.id.invia);
        indietro = (Button)findViewById(R.id.back) ;
        r = (TextView) findViewById(R.id.uniBroken);
        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(MainActivity.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();

        p1 = (Persona)obj;
        r = (TextView)findViewById(R.id.uniBroken);
        if (!p1.getIsRaga()) {
            r.setText("Benvenuto, " + p1.getNome());
        } else {
            Intent showResults = new Intent(Homepage.this, HomeRaga.class);
            showResults.putExtra(PERSONA_EXTRA,p1);
            startActivity(showResults);
       }

        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(Homepage.this, SendSegn.class);
                send.putExtra(PERSONA_EXTRA, p1);
                startActivity(send);
            }
        });

        segnalazioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent segnalazioni = new Intent(Homepage.this, ListaSegn.class);
                segnalazioni.putExtra(PERSONA_EXTRA, p1);
                startActivity(segnalazioni);
            }
        });

        mio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent segnalazioni = new Intent(Homepage.this, MySegn.class);
                segnalazioni.putExtra(PERSONA_EXTRA, p1);
                startActivity(segnalazioni);
            }
        });




    }
    @Override
    public void onBackPressed() {
        logout(new View(this));
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
                Intent logout = new Intent(Homepage.this, MainActivity.class);
                // Start the Intent
                startActivity(logout);
            }
        });

    }
    public void back(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        logout(view);
    }

}
