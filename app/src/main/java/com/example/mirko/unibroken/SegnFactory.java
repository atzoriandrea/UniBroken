package com.example.mirko.unibroken;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;

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

        private static ArrayList<Segnalazione> listaSegnalazioni = new ArrayList<>();
        private SegnFactory(){
            Segnalazione s1 = new Segnalazione();
            s1.setId(1);
            s1.setLuogo("Aula D");
            s1.setAutore(4);
            s1.setImage(v[0]);
            s1.setTesto("Cedimento del soffitto dell'aula D");
            s1.setTipo("Cedimento Soffitto");
            s1.setData("12/12/2012");
            s1.setIdIntervento(3);

            Segnalazione s2 = new Segnalazione();
            s2.setId(2);
            s2.setAutore(2);
            s2.setImage(v[1]);
            s2.setLuogo("Aula C");
            s2.setTipo("Danno Elettrico");
            s2.setData("15/01/2017");
            s2.setTesto("CCCedimento del soffitto dell'aula D");
            s2.setIdIntervento(3);

            Segnalazione s3 = new Segnalazione();
            s3.setId(3);
            s3.setAutore(1);
            s3.setLuogo("Aula A");
            s3.setImage(v[2]);
            s3.setConfirmed(true);
            s3.setData("10/02/2019");
            s3.setTipo("Danno Idraulico");
            s3.setTesto("DDCedimento del soffitto dell'aula D");
            s3.setIdIntervento(3);

            Segnalazione s4 = new Segnalazione();
            s4.setId(4);
            s4.setLuogo("Aula M.Fisica");
            s4.setAutore(4);
            s4.setImage(v[3]);
            s4.setImage(v[4]);
            s4.setTesto("Sedie Mancandi in Aula Magna di FIsica");
            s4.setTipo("Danno Arredi Aule");
            s4.setData("22/11/2018");
            s4.setIdIntervento(9);

            Segnalazione s5 = new Segnalazione();
            s5.setId(5);
            s5.setAutore(2);
            s5.setImage(v[5]);
            s5.setLuogo("Aula M.Fisica");
            s5.setTipo("Danno Arredi Aule");
            s5.setData("19/01/2017");
            s5.setTesto("Serranda non funzionante");
            s5.setIdIntervento(9);

            Segnalazione s6 = new Segnalazione();
            s6.setId(6);
            s6.setAutore(1);
            s6.setLuogo("Laboratorio T");
            s6.setImage(v[6]);
            s6.setConfirmed(true);
            s6.setData("11/02/2018");
            s6.setTipo("Danno Arredi Aule");
            s6.setTesto("Il perno della porta non funziona");
            s6.setIdIntervento(9);

            Segnalazione s7 = new Segnalazione();
            s7.setId(7);
            s7.setLuogo("Laboratorio T");
            s7.setAutore(4);
            s7.setImage(v[7]);
            s7.setTesto("Il segnale wifi è estremamente debole o peggio...");
            s7.setTipo("Danno Connettività");
            s7.setData("13/02/2019");
            s7.setIdIntervento(7);

            Segnalazione s8 = new Segnalazione();
            s8.setId(8);
            s8.setAutore(2);
            s8.setImage(v[8]);
            s8.setLuogo("Scale interne");
            s8.setTipo("Danno Arredi Aule");
            s8.setData("15/01/2017");
            s8.setTesto("La finestra è spaccata e pericolosa");
            s8.setIdIntervento(9);

            Segnalazione s9 = new Segnalazione();
            s9.setId(9);
            s9.setAutore(1);
            s9.setLuogo("SimAz");
            s9.setImage(v[9]);
            s9.setConfirmed(true);
            s9.setData("10/02/2019");
            s9.setTipo("Danno Pavimento");
            s9.setTesto("Il pavimento è rotto");
            s9.setIdIntervento(6);

            Segnalazione s10 = new Segnalazione();
            s10.setId(10);
            s10.setLuogo("Andito Primo Piano");
            s10.setAutore(4);
            s10.setImage(v[10]);
            s10.setTesto("Il muro presenta un grosso buco");
            s10.setTipo("Danno Intonaco");
            s10.setData("17/12/2012");
            s10.setIdIntervento(1);

            Segnalazione s11 = new Segnalazione();
            s11.setId(11);
            s11.setAutore(2);
            s11.setImage(v[1]);
            s11.setLuogo("Aula C");
            s11.setTipo("Danno ELettrico");
            s11.setData("15/01/2017");
            s11.setTesto("CCCedimento del soffitto dell'aula D");
            s11.setIdIntervento(3);

            Segnalazione s12 = new Segnalazione();
            s12.setId(12);
            s12.setAutore(1);
            s12.setLuogo("Aula A");
            s12.setImage(v[2]);
            s12.setConfirmed(true);
            s12.setData("10/02/2019");
            s12.setTipo("Danno Idraulico");
            s12.setTesto("DDCedimento del soffitto dell'aula D");
            s12.setIdIntervento(3);




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

    public static String[] getListaTItoli (){
        SegnFactory factory = SegnFactory.getInstance();
        ArrayList<String> lista = new ArrayList<>();
        int i = 0;
        for(Segnalazione s : listaSegnalazioni){
            if(s.isConfirmed())
                lista.add(s.getTipo());
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
            for(Segnalazione s : listaSegnalazioni){
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
                if (budget - temp >= 0){
                    budget = budget - temp;
                    consigliati.add(s);
                }
            }
            String[] c = new String[consigliati.size()];
            for(Segnalazione s : consigliati){
                c[i] = s.getTipo();
                i++;
            }
            return c;

        }



}
