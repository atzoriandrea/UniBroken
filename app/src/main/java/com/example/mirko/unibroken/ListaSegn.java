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
    public class Adattatore extends BaseAdapter{
        private List<Segnalazione> lista=null;
        private Context context=null;
        public Adattatore(Context context, List<Segnalazione> lista)
        {
            this.lista=lista;
            this.context=context;
        }
        @Override
        public int getCount()
        {
            return lista.size();
        }
        @Override
        public Object getItem(int position)
        {
            return lista.get(position);
        }
        @Override
        public long getItemId(int position)
        {
            return getItem(position).hashCode();
        }
        @Override
        public View getView(int position, View v, ViewGroup vg)
        {
            if (v==null)
            {
                v= LayoutInflater.from(context).inflate(R.layout.activity_lista_segn_element, null);
            }
            Segnalazione s=(Segnalazione) getItem(position);
            TextView txt=(TextView) v.findViewById(R.id.txt_article_description);
            txt.setText(s.getTesto());
            txt=(TextView) v.findViewById(R.id.autore);
            txt.setText("Io");
            return v;
        }
    }
    ListView lista;
    ArrayList<Segnalazione> segn = new ArrayList<Segnalazione>();
    SegnFactory sf = SegnFactory.getInstance();
    Adattatore a ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_segn);
        segn = SegnFactory.getListaSegnalazioni();
        a = new Adattatore(this,segn);
        lista = (ListView)findViewById(R.id.SegList);
        lista.setAdapter(a);


    }

}
