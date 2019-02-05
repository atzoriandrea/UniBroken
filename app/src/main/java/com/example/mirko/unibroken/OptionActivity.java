package com.example.mirko.unibroken;

import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OptionActivity extends ListActivity {
    public static class Adattatore extends BaseAdapter {
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
            String author;
            if (v==null)
            {
                v= LayoutInflater.from(context).inflate(R.layout.activity_lista_segn_element, null);
            }
            Segnalazione s=(Segnalazione) getItem(position);
            TextView txt=(TextView) v.findViewById(R.id.txt_article_description);
            ImageView img = (ImageView) v.findViewById(R.id.immagine) ;
            txt.setText(s.getTesto());
            txt=(TextView) v.findViewById(R.id.autore);
            author = PersonaFactory.getPersonaById(s.getAutore()).getNome() + " " +
                    PersonaFactory.getPersonaById(s.getAutore()).getCognome();
            txt.setText(author);
            img.setImageResource(s.getImage());

            return v;
        }
    }




}
