package com.example.mirko.unibroken;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaSegn extends AppCompatActivity {
    public static final String SEGN="com.example.mirko.esercitazionebonus.Segnalazione";

    ListView lista;
    ArrayList<Segnalazione> segn = new ArrayList<Segnalazione>();
    SegnFactory sf = SegnFactory.getInstance(this);
    Segnalazione s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_segn);
        segn = SegnFactory.getListaSegnalazioni(this);


        OptionActivity.Adattatore a = new OptionActivity.Adattatore(this,segn);
        lista = (ListView)findViewById(R.id.SegList);
        lista.setAdapter(a);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3){
                s = (Segnalazione) lista.getItemAtPosition(position);
                Intent dettaglio = new Intent(ListaSegn.this , Showsegn.class);
                dettaglio.putExtra(SEGN, s);
                startActivity(dettaglio);

            }
        });
    }


}
