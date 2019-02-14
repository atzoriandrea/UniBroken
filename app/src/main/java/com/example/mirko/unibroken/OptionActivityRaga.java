package com.example.mirko.unibroken;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OptionActivityRaga extends AppCompatActivity {

    public static class Adattatore extends BaseAdapter {
        InterventiFactory ifact;
        String tmp;
        PersonaFactory factory = PersonaFactory.getInstance();
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
            SegnFactory sfactory = SegnFactory.getInstance();
            String author;
            if (v==null)
            {
                v= LayoutInflater.from(context).inflate(R.layout.activity_option_raga, null);
            }
            ifact = InterventiFactory.getInstance();
            Segnalazione s=(Segnalazione) getItem(position);
            TextView txt=(TextView) v.findViewById(R.id.txt_article_description);
            ImageView img = (ImageView) v.findViewById(R.id.immagine) ;
            tmp = s.getData() + "-" + s.getTipo();
            txt.setText(tmp);
            txt=(TextView) v.findViewById(R.id.autore);
            author = PersonaFactory.getPersonaById(s.getAutore()).getNome() + " " +
                    PersonaFactory.getPersonaById(s.getAutore()).getCognome();
            txt.setText(author);
            txt=(TextView)v.findViewById(R.id.costo);
            double aux=(ifact.getInterventoById(s.getIdIntervento()).getImporto());
            txt.setText("Costo prev. appr: " + String.valueOf(aux));
            if(s.getImage().size() > 0)
                img.setImageBitmap(SegnFactory.getBitmapFromMemCache(String.valueOf(s.getId())));
            else
                img.setImageResource(R.drawable.dummy_image_square);

            return v;
        }
    }
}
