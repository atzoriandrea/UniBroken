package com.example.mirko.unibroken;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import static android.view.View.OVER_SCROLL_ALWAYS;

public class Raga_Budget extends AppCompatActivity {

    Button indietro;
    Button auto;
    Persona p;
    TextView budgetValore;
    ScrollView inRip;
    TextView riparazione;
    ScrollView listaConsigli;
    TextView consigliati;
    Toast t;

    public static class BulletListBuilder {

        private static final String SPACE = " ";
        private static final String BULLET_SYMBOL = "&#8226";
        private static final String EOL = System.getProperty("line.separator");
        private static final String TAB = "\t";

        private BulletListBuilder() {

        }

        public static String getBulletList(String header, String []items) {
            StringBuilder listBuilder = new StringBuilder();
            if (header != null && !header.isEmpty()) {
                listBuilder.append(header + EOL + EOL);
            }
            if (items != null && items.length != 0) {
                for (String item : items) {
                    Spanned formattedItem = Html.fromHtml(BULLET_SYMBOL + SPACE + item);
                    listBuilder.append(TAB + formattedItem + EOL);
                }
            }
            return listBuilder.toString();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SegnFactory sf = SegnFactory.getInstance();
        final Context c = this;
        InterventiFactory ifact = InterventiFactory.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raga__budget);
        indietro = (Button)findViewById(R.id.back);
        auto = (Button)findViewById(R.id.gestisciAutomaticamente);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Homepage.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();
        p = (Persona)obj;
        //final Spinner dropdown = findViewById(R.id.tipologia2);
        //Utilizzare item
        riparazione = (TextView)findViewById(R.id.content1);
        consigliati = (TextView)findViewById(R.id.content2);
        budgetValore=(TextView)findViewById(R.id.budgetValore);
        inRip=(ScrollView)findViewById(R.id.inRip);
        inRip.setScrollbarFadingEnabled(false);
        listaConsigli=(ScrollView)findViewById(R.id.listaConsigli);
        listaConsigli.setScrollbarFadingEnabled(false);
        budgetValore.setText("€ "+String.valueOf(InterventiFactory.getBudget()));
        riparazione.setText(BulletListBuilder.getBulletList( "", SegnFactory.getListaTItoli() ));
        consigliati.setText(BulletListBuilder.getBulletList( "", SegnFactory.getConsigliati() ));
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SegnFactory.getConsigliati().length != 0){

                    final AlertDialog dialog = new AlertDialog.Builder(c)
                            .setTitle("Gestione Automatica delle Segnalazioni")
                            .setMessage("UniBroken inserirà tutte le voci in 'Unibroken consiglia' tra le segnalazioni approvate, e comunicherà la richiesta alle ditte associate.\nSi desidera procedere?")
                            .setPositiveButton("CONFERMA", null)
                            .setNegativeButton("ANNULLA", null)
                            .show();


                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            consigliati.setText(BulletListBuilder.getBulletList( "", SegnFactory.eseguiConsigliati() ));
                            riparazione.setText(BulletListBuilder.getBulletList( "", SegnFactory.getListaTItoli() ));
                            budgetValore.setText("€ "+String.valueOf(InterventiFactory.getBudget()));
                            dialog.hide();
                              }
                });
                }else{
                    text_msg(new View(c));
                }

            }
        });
    }
    public void text_msg(View view){
        t = Toast.makeText(getApplicationContext(), "Il budget è già stato assegnato nel miglior modo possibile. Ottimo!", Toast.LENGTH_LONG);
        t.setGravity(Gravity.BOTTOM,0 ,250);
        t.show();
    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(Raga_Budget.this, HomeRaga.class);
        indietro.putExtra(Homepage.PERSONA_EXTRA, p);
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
                Intent logout = new Intent(Raga_Budget.this, MainActivity.class);
                // Start the Intent
                startActivity(logout);
            }
        });

    }
}
