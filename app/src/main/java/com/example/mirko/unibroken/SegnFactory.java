package com.example.mirko.unibroken;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import java.util.ArrayList;

public class SegnFactory {
        private static SegnFactory instance;
        private static Bitmap [] v = new Bitmap[3];

        private static ArrayList<Segnalazione> listaSegnalazioni = new ArrayList<>();
        private SegnFactory(){
            Segnalazione s1 = new Segnalazione();
            s1.setId(1);
            s1.setAutore(4);
            s1.setImage(v[0]);
            s1.setTesto("Cedimento del soffitto dell'aula D");

            Segnalazione s2 = new Segnalazione();
            s2.setId(2);
            s2.setAutore(2);
            s2.setImage(v[1]);
            s2.setTesto("CCCedimento del soffitto dell'aula D");

            Segnalazione s3 = new Segnalazione();
            s3.setId(3);
            s3.setAutore(1);
            s3.setImage(v[2]);
            s3.setTesto("DDCedimento del soffitto dell'aula D");

            listaSegnalazioni.add(s1);
            listaSegnalazioni.add(s2);
            listaSegnalazioni.add(s3);
        }
        public static SegnFactory getInstance(){
            if (instance == null)
                instance = new SegnFactory();
            return instance;
        }
        public static ArrayList<Segnalazione> getListaSegnalazioni(){
            SegnFactory factory = SegnFactory.getInstance();
            return listaSegnalazioni;
        }
        public static ArrayList<Segnalazione> getListaSegnalazioniByAuthor(int id){
            SegnFactory factory = SegnFactory.getInstance();
            ArrayList<Segnalazione> mysegnalations = new ArrayList<>();
            for(Segnalazione s : listaSegnalazioni){
                if (s.getAutore() == id){
                    mysegnalations.add(s);
                }
            }
            return mysegnalations;
        }
    public static Segnalazione getSegnalazioneById(int id){
        SegnFactory factory = SegnFactory.getInstance();
        for(Segnalazione s : listaSegnalazioni){
            if (s.getId() == id){
                return  s;
            }
        }
        return null;
    }
    public static int getLastSegn(){
        SegnFactory factory = SegnFactory.getInstance();
        int m = 0;
        for(Segnalazione s:listaSegnalazioni){
            if (m<s.getId())
                m = s.getId();
        }
        return m;
    }
    public static void addSegnalazione(Segnalazione s ){
        SegnFactory factory = SegnFactory.getInstance();
        int max = getLastSegn();
        s.setId(max+1);
        listaSegnalazioni.add(s);

    }
    public static void setBitmaps (Bitmap [] a){
            v[0] = a[0];
            v[1] = a[1];
            v[2] = a[2];
    }
    public static void remSegnalazioneById(int id){
        SegnFactory factory = SegnFactory.getInstance();
        ArrayList<Segnalazione> tmp = new ArrayList<>();
        for(Segnalazione s : listaSegnalazioni){
            tmp.add(s);
        }
        for(Segnalazione s : tmp){
            if (s.getId() == id){
                listaSegnalazioni.remove(s);
            }
        }
        factory = SegnFactory.getInstance();
    }

}
