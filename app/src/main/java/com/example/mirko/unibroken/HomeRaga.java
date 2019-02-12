package com.example.mirko.unibroken;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class HomeRaga extends AppCompatActivity {
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";
    Persona p;
    Button invia, gestisci, budget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_raga);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Homepage.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();
        p = (Persona) obj;
        invia = (Button)findViewById(R.id.invia);
        gestisci = (Button)findViewById(R.id.gestisci);
        budget = (Button)findViewById(R.id.budget);
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

}
