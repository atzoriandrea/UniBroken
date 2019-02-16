package com.example.mirko.unibroken;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class HomeRaga extends AppCompatActivity {
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";
    Persona p;
    TextView r;
    Button invia, gestisci, budget,indietro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_raga);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Homepage.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();
        p = (Persona) obj;
        r = (TextView)findViewById(R.id.uniBroken);
        r.setText("Benvenuto, Ing. " + p.getCognome());
        invia = (Button)findViewById(R.id.invia);
        gestisci = (Button)findViewById(R.id.gestisci);
        budget = (Button)findViewById(R.id.budget);
        indietro = (Button)findViewById(R.id.back);
        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(HomeRaga.this, SendSegn.class);
                send.putExtra(PERSONA_EXTRA, p);
                startActivity(send);
            }
        });
        gestisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent segnalazioni = new Intent(HomeRaga.this, ListaSegnRaga.class);
                segnalazioni.putExtra(PERSONA_EXTRA, p);
                startActivity(segnalazioni);
            }
        });
        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent segnalazioni = new Intent(HomeRaga.this, Raga_Budget.class);
                segnalazioni.putExtra(PERSONA_EXTRA, p);
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
                Intent logout = new Intent(HomeRaga.this, MainActivity.class);
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
