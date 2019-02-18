package com.example.mirko.unibroken;

import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    TextView resoconti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_pdf);
        resoconti = (TextView)findViewById(R.id.lista);
        double spesaTot = 0;
        int i = 0;
        SegnFactory sf = SegnFactory.getInstance();
        ArrayList<Segnalazione> anno = SegnFactory.getSegnalazioniperAnno();
        String[] array = new String[anno.size()];
        for(Segnalazione s:anno){
            array[i] = s.getTipo() + " - " + s.getLuogo() + " - € " + String.valueOf(InterventiFactory.getInterventoById(s.getIdIntervento()).getImporto())+"0";
            spesaTot = spesaTot + InterventiFactory.getInterventoById(s.getIdIntervento()).getImporto();
            i++;
        }
        resoconti.setText("Budget 2018: € 48000,00\nSono state effettuati i seguenti interventi: \n"+DrawPDF.BulletListBuilder.getBulletList( "", array)+"\nBusget Rimanente: "+String.valueOf(48000-spesaTot));
    }
}
