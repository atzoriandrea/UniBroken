package com.example.mirko.unibroken;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

public class Resoconti extends AppCompatActivity {
    TextView resoconti2017, resoconti2018;
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";
    public static final String INTERO="java.lang.Integer";
    Persona p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resoconti);
        PersonaFactory pf = PersonaFactory.getInstance();
        p = PersonaFactory.getPersonaById(3);
        resoconti2017 = (TextView)findViewById(R.id.ddiciassette);
        resoconti2018 = (TextView)findViewById(R.id.ddiciotto);
        resoconti2017.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creaPDF = new Intent(Resoconti.this,DrawPDF.class);
                creaPDF.putExtra(INTERO,2017);
                startActivity(creaPDF);
            }
        });
        resoconti2018.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creaPDF = new Intent(Resoconti.this,DrawPDF.class);
                creaPDF.putExtra(INTERO,2018);
                startActivity(creaPDF);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(Resoconti.this, Homepage.class);
        indietro.putExtra(Homepage.PERSONA_EXTRA,p);
        startActivity(indietro);
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
                Intent logout = new Intent(Resoconti.this, MainActivity.class);
                startActivity(logout);
            }
        });

    }
    public void back(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        onBackPressed();
    }
}
