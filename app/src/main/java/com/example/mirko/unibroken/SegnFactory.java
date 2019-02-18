package com.example.mirko.unibroken;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.util.LruCache;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class SegnFactory{
        private static SegnFactory instance;
        private static Bitmap [] v = new Bitmap[11];
        private static LruCache<String, Bitmap> mMemoryCache;
    private static ArrayList<Segnalazione> listaSegnalazioni = new ArrayList<>();
        private SegnFactory(){
            InterventiFactory ifact = InterventiFactory.getInstance();
            prepareCache();
            Segnalazione stemp = new Segnalazione();
            stemp.setId(-1000);
            stemp.setLuogo("Aula D");
            stemp.setAutore(4);
            stemp.setTesto("Cedimento del soffitto dell'aula D");
            stemp.setTipo("Cedimento Soffitto");
            stemp.setData("12/12/2012");
            stemp.setIdIntervento(InterventiFactory.getInterventoByType(stemp.getTipo()));


            Segnalazione s1 = new Segnalazione();
            s1.setId(1);
            s1.setLuogo("Aula D");
            s1.setAutore(4);
            s1.setImage(v[0]);
            s1.setTesto("Cedimento del soffitto dell'aula D");
            s1.setTipo("Cedimento Soffitto");
            s1.setData("12/12/2016");
            s1.setSolved(true);
            s1.setIdIntervento(InterventiFactory.getInterventoByType(s1.getTipo()));

            Segnalazione s2 = new Segnalazione();
            s2.setId(2);
            s2.setAutore(2);
            s2.setImage(v[1]);
            s2.setLuogo("Aula C");
            s2.setTipo("Danno Elettrico");
            s2.setData("15/01/2018");
            s2.setTesto("CCCedimento del soffitto dell'aula D");
            s2.setIdIntervento(InterventiFactory.getInterventoByType(s2.getTipo()));

            Segnalazione s3 = new Segnalazione();
            s3.setId(3);
            s3.setAutore(1);
            s3.setLuogo("Aula A");
            s3.setImage(v[2]);
            s3.setData("10/02/2019");
            s3.setTipo("Danno Idraulico");
            s3.setTesto("DDCedimento del soffitto dell'aula D");
            s3.setConfirmed(true);
            s3.setIdIntervento(InterventiFactory.getInterventoByType(s3.getTipo()));

            Segnalazione s4 = new Segnalazione();
            s4.setId(4);
            s4.setLuogo("Aula M.Fisica");
            s4.setAutore(4);
            s4.setImage(v[3]);
            s4.setImage(v[4]);
            s4.setTesto("Sedie Mancandi in Aula Magna di FIsica");
            s4.setTipo("Danno Arredi Aule");
            s4.setData("22/11/2018");
            s4.setIdIntervento(InterventiFactory.getInterventoByType(s4.getTipo()));

            Segnalazione s5 = new Segnalazione();
            s5.setId(5);
            s5.setAutore(2);
            s5.setImage(v[5]);
            s5.setLuogo("Aula M.Fisica");
            s5.setTipo("Danno Arredi Aule");
            s5.setData("19/01/2018");
            s5.setTesto("Serranda non funzionante");
            s5.setIdIntervento(InterventiFactory.getInterventoByType(s5.getTipo()));

            Segnalazione s6 = new Segnalazione();
            s6.setId(6);
            s6.setAutore(1);
            s6.setLuogo("Laboratorio T");
            s6.setImage(v[6]);
            s6.setData("11/02/2018");
            s6.setTipo("Danno Arredi Aule");
            s6.setTesto("Il perno della porta non funziona");
            s6.setConfirmed(true);
            s6.setIdIntervento(InterventiFactory.getInterventoByType(s6.getTipo()));

            Segnalazione s7 = new Segnalazione();
            s7.setId(7);
            s7.setLuogo("Laboratorio T");
            s7.setAutore(4);
            s7.setImage(v[7]);
            s7.setTesto("Il segnale wifi è estremamente debole o peggio...");
            s7.setTipo("Danno Connettività");
            s7.setData("13/02/2019");
            s7.setIdIntervento(InterventiFactory.getInterventoByType(s7.getTipo()));

            Segnalazione s8 = new Segnalazione();
            s8.setId(8);
            s8.setAutore(2);
            s8.setImage(v[8]);
            s8.setLuogo("Scale interne");
            s8.setTipo("Danno Arredi Aule");
            s8.setData("15/01/2017");
            s8.setTesto("La finestra è spaccata e pericolosa");
            s8.setIdIntervento(InterventiFactory.getInterventoByType(s8.getTipo()));

            Segnalazione s9 = new Segnalazione();
            s9.setId(9);
            s9.setAutore(1);
            s9.setLuogo("SimAz");
            s9.setImage(v[9]);
            s9.setData("10/02/2019");
            s9.setTipo("Danno Pavimento");
            s9.setTesto("Il pavimento è rotto");
            s9.setConfirmed(true);
            s9.setIdIntervento(InterventiFactory.getInterventoByType(s9.getTipo()));

            Segnalazione s10 = new Segnalazione();
            s10.setId(10);
            s10.setLuogo("Andito Primo Piano");
            s10.setAutore(4);
            s10.setImage(v[10]);
            s10.setTesto("Il muro presenta un grosso buco");
            s10.setTipo("Danno Intonaco");
            s10.setData("17/12/2012");
            s10.setIdIntervento(InterventiFactory.getInterventoByType(s10.getTipo()));

            Segnalazione s11 = new Segnalazione();
            s11.setId(11);
            s11.setAutore(2);
            s11.setImage(v[1]);
            s11.setLuogo("Aula C");
            s11.setTipo("Danno Elettrico");
            s11.setData("15/01/2017");
            s11.setTesto("CCCedimento del soffitto dell'aula D");
            s11.setIdIntervento(InterventiFactory.getInterventoByType(s11.getTipo()));

            Segnalazione s12 = new Segnalazione();
            s12.setId(12);
            s12.setAutore(1);
            s12.setLuogo("Aula A");
            s12.setImage(v[2]);
            s12.setData("10/02/2019");
            s12.setTipo("Danno Idraulico");
            s12.setTesto("DDCedimento del soffitto dell'aula D");
            s12.setConfirmed(true);
            s12.setIdIntervento(InterventiFactory.getInterventoByType(s12.getTipo()));




            listaSegnalazioni.add(s1);
            listaSegnalazioni.add(s2);
            listaSegnalazioni.add(s3);
            listaSegnalazioni.add(s4);
            listaSegnalazioni.add(s5);
            listaSegnalazioni.add(s6);
            listaSegnalazioni.add(s7);
            listaSegnalazioni.add(s8);
            listaSegnalazioni.add(s9);
            listaSegnalazioni.add(s10);
            listaSegnalazioni.add(s11);
            listaSegnalazioni.add(s12);
            for(Segnalazione s : listaSegnalazioni){
                addBitmapToMemoryCache(String.valueOf(s.getId()),s.getImage().get((s.getImage().size())-1));
            }

        }
        public static SegnFactory getInstance(){
            if (instance == null)
                instance = new SegnFactory();
            return instance;
        }
        public static ArrayList<Segnalazione> getListaSegnalazioni(){
            SegnFactory factory = SegnFactory.getInstance();
            ArrayList<Segnalazione> not = new ArrayList<>();
            for(Segnalazione s:listaSegnalazioni){
                if (!s.isSolved())
                    not.add(s);
            }
            return not;
        }
        public static ArrayList<Segnalazione> getListaSegnalazioniByAuthor(int id){
        SegnFactory factory = SegnFactory.getInstance();
        ArrayList<Segnalazione> mysegnalations = new ArrayList<>();
        for(Segnalazione s : getListaSegnalazioni()){
            if (s.getAutore() == id){
                mysegnalations.add(s);
            }
        }
        return mysegnalations;
    }

    public static ArrayList<Segnalazione> getListaSegnalazioniByType(String type){
        SegnFactory factory = SegnFactory.getInstance();
        ArrayList<Segnalazione> typedsegnalations = new ArrayList<>();
        for(Segnalazione s : getListaSegnalazioni()){
            if (s.getTipo().equals(type)){
                typedsegnalations.add(s);
            }
        }
        return typedsegnalations;
    }
    public static Segnalazione getSegnalazioneById(int id){
        SegnFactory factory = SegnFactory.getInstance();
        for(Segnalazione s : getListaSegnalazioni()){
            if (s.getId() == id){
                return  s;
            }
        }
        return null;
    }
    public static int getLastSegn(){
        SegnFactory factory = SegnFactory.getInstance();
        int m = 0;
        for(Segnalazione s:getListaSegnalazioni()){
            if (m<s.getId())
                m = s.getId();
        }
        return m;
    }
    public static void addSegnalazione(Segnalazione s){
        SegnFactory factory = SegnFactory.getInstance();
        int max = getLastSegn();
        s.setId(max+1);
        if (s.getImage().size()>0)
            addBitmapToMemoryCache(String.valueOf(s.getId()),Bitmap.createScaledBitmap(s.getImage().get((s.getImage().size())-1), 100, 100, false));
        s.setData(new Date());
        listaSegnalazioni.add(s);

    }
    public static void setBitmaps (Bitmap [] a){
            v[0] = a[0];
            v[1] = a[1];
            v[2] = a[2];
            v[3] = a[3];
            v[4] = a[4];
            v[5] = a[5];
            v[6] = a[6];
            v[7] = a[7];
            v[8] = a[8];
            v[9] = a[9];
            v[10] = a[10];
    }
    public static void remSegnalazioneById(int id){
        SegnFactory factory = SegnFactory.getInstance();
        ArrayList<Segnalazione> tmp = new ArrayList<>();
        for(Segnalazione s : getListaSegnalazioni()){
            tmp.add(s);
        }
        for(Segnalazione s : tmp){
            if (s.getId() == id){
                listaSegnalazioni.remove(s);
            }
        }
        factory = SegnFactory.getInstance();
    }

    public static String[] getListaTItoli (){
        SegnFactory factory = SegnFactory.getInstance();
        ArrayList<String> lista = new ArrayList<>();
        int i = 0;
        for(Segnalazione s : getListaSegnalazioni()){
            if(s.isConfirmed())
                lista.add("Segn. #" + String.valueOf(s.getId()) + " - " + s.getTipo() + " - € " + String.valueOf(InterventiFactory.getInterventoById(s.getIdIntervento()).getImporto())+"0");
        }
        String [] s = new String[lista.size()];
        for(String string : lista){
            s[i] = string;
            i++;
        }
        return s;

        }
        public static ArrayList<Segnalazione> sortByPriorita (){
            SegnFactory sf = SegnFactory.getInstance();
            ArrayList<Segnalazione> copia = new ArrayList<>();
            for(Segnalazione s : getListaSegnalazioni()){
                copia.add(s);
            }
            Collections.sort(copia);
            return copia;
        }
        public static String[] getConsigliati(){
            InterventiFactory ifact = InterventiFactory.getInstance();
            SegnFactory sfact = SegnFactory.getInstance();
            int i = 0;
            double temp;
            double budget = InterventiFactory.getBudget();
            ArrayList<Segnalazione> lista = SegnFactory.sortByPriorita();
            ArrayList<Segnalazione> consigliati = new ArrayList<>();
            for(Segnalazione s : lista){
                temp = InterventiFactory.getInterventoById(s.getIdIntervento()).getImporto();
                if (budget - temp >= 0 && !s.isConfirmed()){
                    budget = budget - temp;
                    consigliati.add(s);
                }
            }
            String[] c = new String[consigliati.size()];
            for(Segnalazione s : consigliati){
                c[i] = "Segn. #" + String.valueOf(s.getId()) + " - " + s.getTipo() + " - € " + String.valueOf(InterventiFactory.getInterventoById(s.getIdIntervento()).getImporto())+"0";
                i++;
            }
            return c;

        }
    public static void removeTemp(){
        SegnFactory sfact = SegnFactory.getInstance();
        for(Segnalazione s: getListaSegnalazioni()){
            if (s.isTemp())
                remSegnalazioneById(s.getId());
        }
        sfact = SegnFactory.getInstance();
    }
    public static String[] eseguiConsigliati(){
        InterventiFactory ifact = InterventiFactory.getInstance();
        SegnFactory sfact = SegnFactory.getInstance();
        double temp;
        double budget = InterventiFactory.getBudget();
        ArrayList<Segnalazione> lista = SegnFactory.sortByPriorita();
        for(Segnalazione s : lista){
            temp = InterventiFactory.getInterventoById(s.getIdIntervento()).getImporto();
            if (budget - temp >= 0 && !s.isConfirmed()){
                budget = budget - temp;
                s.setConfirmed(true);
            }
        }
        InterventiFactory.setBudget(budget);
        return getConsigliati();
    }
    public static int getUnchecked(){
            SegnFactory sf = SegnFactory.getInstance();
        ArrayList<Segnalazione> lista = new ArrayList<>();
        int consigliati = getConsigliati().length;
        for(Segnalazione s : getListaSegnalazioni()){
            if(consigliati==0) {
                if (!s.isConfirmed())
                    lista.add(s);
            }
        }
        return lista.size();
    }

    public static ArrayList<Segnalazione> getSegnalazioniperAnno(){
            SegnFactory sf = SegnFactory.getInstance();
            ArrayList<Segnalazione> anno = new ArrayList<>();
            for(Segnalazione s : getListaSegnalazioni()){
                if(s.getData().substring(7,11).equals("2018"))
                    anno.add(s);
            }
            return anno;

    }
        public static void prepareCache(){

                // Get max available VM memory, exceeding this amount will throw an
                // OutOfMemory exception. Stored in kilobytes as LruCache takes an
                // int in its constructor.
                final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

                // Use 1/8th of the available memory for this memory cache.
                final int cacheSize = maxMemory / 8;

                mMemoryCache = new LruCache<String, Bitmap>(cacheSize*10) {
                    @Override
                    protected int sizeOf(String key, Bitmap bitmap) {
                        // The cache size will be measured in kilobytes rather than
                        // number of items.
                        return bitmap.getByteCount() / 1024;
                    }
                };
            }

            public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
                mMemoryCache.put(key, Bitmap.createScaledBitmap(bitmap, 100, 100, false));

            }

            public static Bitmap getBitmapFromMemCache(String key) {
                return mMemoryCache.get(key);
            }





}
