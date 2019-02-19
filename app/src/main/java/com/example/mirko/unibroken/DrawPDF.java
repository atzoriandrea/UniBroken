package com.example.mirko.unibroken;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DrawPDF extends AppCompatActivity {
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
    TextView resoconti,bud,rimanenza;
    Button send;
    int year;
    ScrollView sv;
    Persona p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_pdf);
        resoconti = (TextView)findViewById(R.id.lista);
        bud = (TextView)findViewById(R.id.budg);
        rimanenza = (TextView)findViewById(R.id.rimanenza);
        send = (Button)findViewById(R.id.send);
        sv = (ScrollView)findViewById(R.id.sv);
        double spesaTot = 0;
        int i = 0;
        SegnFactory sf = SegnFactory.getInstance();
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Resoconti.INTERO);
        Bundle bundle = getIntent().getExtras();

        year = (Integer)obj;
        ArrayList<Segnalazione> anno = SegnFactory.getSegnalazioniperAnno(String.valueOf(year));
        String[] array = new String[anno.size()];
        double temp;
        for(Segnalazione s:anno){
            array[i] = s.getTipo() + " - " + s.getLuogo() + " - € " + String.valueOf(InterventiFactory.getInterventoById(s.getIdIntervento()).getImporto())+"0";
            temp = InterventiFactory.getInterventoById(s.getIdIntervento()).getImporto();
            if (spesaTot + temp <= 30000) {
                spesaTot = spesaTot + temp;
            }
            i++;
        }
        sv.setScrollbarFadingEnabled(false);
        bud.setText("Budget "+String.valueOf(year)+":");
        rimanenza.setText("Budget Rimanente: € "+String.valueOf(30000-spesaTot)+"0");
        resoconti.setText(DrawPDF.BulletListBuilder.getBulletList( "", array));
        send.setText("Invia Resoconto "+String.valueOf(year));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to Open Image applications like Gallery, Google Photos
                AlertDialog dialog = new AlertDialog.Builder(DrawPDF.this)
                        .setTitle("Invia Resoconto")
                        .setMessage("Vuoi davvero inviare il resoconto all'Amministrazione?\n\nTale documento avrà validità ufficiale ai fini della rendicontazione dell'Università degli Studi di Cagliari")
                        .setPositiveButton("CONFERMA", null)
                        .setNegativeButton("ANNULLA", null)
                        .show();


                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent raga = new Intent(DrawPDF.this, HomeRaga.class);
                        PersonaFactory pf = PersonaFactory.getInstance();
                        p = PersonaFactory.getPersonaById(3);
                        raga.putExtra(HomeRaga.PERSONA_EXTRA,p);
                        startActivity(raga);
                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(DrawPDF.this, Resoconti.class);
        startActivity(indietro);
    }
    public void back(View view) {
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
                Intent logout = new Intent(DrawPDF.this, MainActivity.class);

                startActivity(logout);
            }
        });

    }
}
