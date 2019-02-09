package com.example.mirko.unibroken;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class Homepage extends AppCompatActivity {
    public static final String PERSONA_EXTRA="com.example.mirko.esercitazionebonus.Persona";
    Persona p1;
    Persona p2;
    TextView r;
    Button invia;
    Button segnalazioni;
    Button mio;

    Button indietro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PersonaFactory pf = PersonaFactory.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        segnalazioni = (Button)findViewById(R.id.mostra);
        mio = (Button)findViewById(R.id.visualizzaMieSegnalazioni);
        invia = (Button)findViewById(R.id.invia);
        r = (TextView) findViewById(R.id.uniBroken);
        //indietro = (Button) findViewById(R.id.indietro);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(MainActivity.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();

        p1 = (Persona)obj;
        r = (TextView)findViewById(R.id.uniBroken);
        if (!p1.getIsRaga()) {
            r.setText("Benvenuto in UniBroken, " + p1.getNome());
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



        /*indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent undo = new Intent(Homepage.this, MainActivity.class);
                startActivity(undo);    //bottone per tornare alla prima activity
            }
        });*/
    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(Homepage.this, MainActivity.class);
        startActivity(indietro);
    }
}
