package com.example.mirko.unibroken;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MySegn extends AppCompatActivity {
    public static final String PERSONA_EXTRA="com.example.mirko.esercitazionebonus.Persona";

    public static final String SEGN="com.example.mirko.esercitazionebonus.Segnalazione";

    ListView lista;
    Persona p1;
    ArrayList<Segnalazione> segn = new ArrayList<Segnalazione>();
    SegnFactory sf = SegnFactory.getInstance();
    Segnalazione s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_segn);
        SegnFactory sf = SegnFactory.getInstance();
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Homepage.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();
        p1 = (Persona)obj;
        segn = SegnFactory.getListaSegnalazioniByAuthor(p1.getId());

        OptionActivity.Adattatore a = new OptionActivity.Adattatore(this,segn);
        lista = (ListView)findViewById(R.id.SegList);
        lista.setAdapter(a);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                s = (Segnalazione) lista.getItemAtPosition(position);
                Intent dettaglio = new Intent(MySegn.this , MySegnDetail.class);
                dettaglio.putExtra(PERSONA_EXTRA,p1);
                dettaglio.putExtra(SEGN, s);
                startActivity(dettaglio);

            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(MySegn.this, Homepage.class);
        indietro.putExtra(PERSONA_EXTRA,p1);
        startActivity(indietro);
    }
}
