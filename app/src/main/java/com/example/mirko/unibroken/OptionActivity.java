package com.example.mirko.unibroken;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OptionActivity extends ListActivity {
    public static class Adattatore extends BaseAdapter {
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
            String author;
            if (v==null)
            {
                v= LayoutInflater.from(context).inflate(R.layout.activity_lista_segn_element, null);
            }
            Segnalazione s=(Segnalazione) getItem(position);
            TextView txt=(TextView) v.findViewById(R.id.txt_article_description);
            ImageView img = (ImageView) v.findViewById(R.id.immagine) ;
            tmp = s.getData() + "-" + s.getTipo();
            txt.setText(tmp);
            txt=(TextView) v.findViewById(R.id.autore);
            author = PersonaFactory.getPersonaById(s.getAutore()).getNome() + " " +
                    PersonaFactory.getPersonaById(s.getAutore()).getCognome();
            txt.setText(author);
            if(s.getImage().size() > 0)
                img.setImageBitmap(s.getImage().get((s.getImage().size())-1));
            else
                img.setImageResource(R.drawable.dummy_image_square);

            return v;
        }

    }

    /*private LruCache<String, Bitmap> mMemoryCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
    */



}
