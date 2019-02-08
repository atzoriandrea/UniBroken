package com.example.mirko.unibroken;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.res.ResourcesCompat;

import java.util.ArrayList;

public class SegnFactory {
        private static SegnFactory instance;

        private static ArrayList<Segnalazione> listaSegnalazioni = new ArrayList<>();
        private SegnFactory(Context c){
            Segnalazione s1 = new Segnalazione();
            s1.setId(1);
            s1.setAutore(4);
            s1.setImage(R.drawable.foto_655402_908x560);
            s1.setTesto("Cedimento del soffitto dell'aula D");

            Segnalazione s2 = new Segnalazione();
            s2.setId(2);
            s2.setAutore(2);
            s2.setImage(BitmapFactory.decodeResource(c.getResources(),R.drawable.foto_655404_514x318));
            s2.setTesto("CCCedimento del soffitto dell'aula D");

            Segnalazione s3 = new Segnalazione();
            s3.setId(3);
            s3.setAutore(1);
            s3.setImage(BitmapFactory.decodeResource(c.getResources(),R.drawable.foto_693184_908x560));
            s3.setTesto("DDCedimento del soffitto dell'aula D");

            listaSegnalazioni.add(s1);
            listaSegnalazioni.add(s2);
            listaSegnalazioni.add(s3);
        }
        public static SegnFactory getInstance(Context c){
            if (instance == null)
                instance = new SegnFactory(c);
            return instance;
        }
        public static ArrayList<Segnalazione> getListaSegnalazioni(Context c){
            SegnFactory factory = SegnFactory.getInstance(c);
            return listaSegnalazioni;
        }
        public static ArrayList<Segnalazione> getListaSegnalazioniByAuthor(int id, Context c){
            SegnFactory factory = SegnFactory.getInstance(c);
            ArrayList<Segnalazione> mysegnalations = new ArrayList<>();
            for(Segnalazione s : listaSegnalazioni){
                if (s.getAutore() == id){
                    mysegnalations.add(s);
                }
            }
            return mysegnalations;
        }
    public static Segnalazione getSegnalazioneById(int id,Context c){
        SegnFactory factory = SegnFactory.getInstance(c);
        for(Segnalazione s : listaSegnalazioni){
            if (s.getId() == id){
                return  s;
            }
        }
        return null;
    }
    public static int getLastSegn(Context c){
        SegnFactory factory = SegnFactory.getInstance(c);
        int m = 0;
        for(Segnalazione s:listaSegnalazioni){
            if (m<s.getId())
                m = s.getId();
        }
        return m;
    }
    public static void addSegnalazione(Segnalazione s, Context c){
        SegnFactory factory = SegnFactory.getInstance(c);
        int max = getLastSegn(c);
        s.setId(max+1);
        listaSegnalazioni.add(s);

    }
    public static void remSegnalazioneById(int id,Context c){
        SegnFactory factory = SegnFactory.getInstance(c);
        ArrayList<Segnalazione> tmp = new ArrayList<>();
        for(Segnalazione s : listaSegnalazioni){
            tmp.add(s);
        }
        for(Segnalazione s : tmp){
            if (s.getId() == id){
                listaSegnalazioni.remove(s);
            }
        }
        factory = SegnFactory.getInstance(c);
    }

}
