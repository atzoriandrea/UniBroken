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

    }

}
