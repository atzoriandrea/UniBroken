package com.example.mirko.unibroken;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListaSegn extends AppCompatActivity {

    ListView lista;
    ArrayList<Segnalazione> segn = new ArrayList<Segnalazione>();
    SegnFactory sf = SegnFactory.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_segn);
        segn = SegnFactory.getListaSegnalazioni();
        OptionActivity.Adattatore a = new OptionActivity.Adattatore(this,segn);
        lista = (ListView)findViewById(R.id.SegList);
        lista.setAdapter(a);


    }

}
