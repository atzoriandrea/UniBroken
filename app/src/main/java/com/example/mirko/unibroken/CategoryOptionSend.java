package com.example.mirko.unibroken;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CategoryOptionSend extends ListActivity {
    public static class Adattatore extends BaseAdapter {
        String tmp;
        PersonaFactory factory = PersonaFactory.getInstance();
        private List<String> lista=null;
        private Context context=null;
        public Adattatore(Context context, List<String> lista)
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
                v= LayoutInflater.from(context).inflate(R.layout.lista_categorie, null);
            }
            String s=(String) getItem(position);
            TextView txt=(TextView) v.findViewById(R.id.txt_article_description);
            ImageView img = (ImageView) v.findViewById(R.id.immagine) ;
            tmp = s;
            txt.setText(tmp);

            img.setBackgroundColor(Color.WHITE);
            switch (s){
                case("Danno Intonaco"):{
                    img.setImageResource(R.drawable.cat_intonaco);
                    break;
                }
                case("Danno Finestre"):{
                    img.setImageResource(R.drawable.cat_finestre);
                    break;
                }
                case("Cedimento Soffitto"):{
                    img.setImageResource(R.drawable.cat_soffitto);
                    break;
                }
                case("Danno Idraulico"):{
                    img.setImageResource(R.drawable.cat_idraulica);
                    break;
                }
                case("Danno Elettrico"):{
                    img.setImageResource(R.drawable.cat_elettricita);
                    break;
                }
                case("Danno Pavimento"):{
                    img.setImageResource(R.drawable.cat_pavimento);
                    break;
                }
                case("Danno Connettività"):{
                    img.setImageResource(R.drawable.cat_connettivita);
                    break;
                }
                case("Danno Condizionatore(i)"):{
                    img.setImageResource(R.drawable.cat_condizionatori);
                    break;
                }
                case("Danno Arredi Aule"):{
                    img.setImageResource(R.drawable.cat_arredi);
                    break;
                }
                default:{
                    img.setImageResource(R.drawable.cat_all);
                    break;
                }
            }
            //


            return v;
        }

    }
}