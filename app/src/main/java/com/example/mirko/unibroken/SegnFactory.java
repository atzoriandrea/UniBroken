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
import java.util.HashMap;

public class SegnFactory{
        private static SegnFactory instance;
        private static Bitmap [] v = new Bitmap[11];
        private static HashMap<String, Bitmap> mMemoryCache = new HashMap<>();;
        public static  Segnalazione stemp;
        private static String selectedCategory = "Tutte le segnalazioni";
    private static ArrayList<Segnalazione> listaSegnalazioni = new ArrayList<>();
        private SegnFactory(){
            InterventiFactory ifact = InterventiFactory.getInstance();
            stemp = new Segnalazione();
            stemp.setId(-1000);
            stemp.setLuogo("Aula D");
            stemp.setAutore(4);
            stemp.setSolved(true);
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
            s1.setData("12/12/2017");
            s1.setIdIntervento(InterventiFactory.getInterventoByType(s1.getTipo()));

            Segnalazione s2 = new Segnalazione();
            s2.setId(2);
            s2.setAutore(2);
            //s2.setImage(v[1]);
            s2.setLuogo("Aula C");
            s2.setTipo("Danno Elettrico");
            s2.setData("15/01/2018");
            s2.setTesto("Cavi scoperti in Aula C");
            s2.setIdIntervento(InterventiFactory.getInterventoByType(s2.getTipo()));

            Segnalazione s3 = new Segnalazione();
            s3.setId(3);
            s3.setAutore(1);
            s3.setLuogo("Aula A");
            //s3.setImage(v[2]);
            s3.setData("10/02/2017");
            s3.setTipo("Danno Idraulico");
            s3.setTesto("Esce acqua dal muro");
            s3.setConfirmed(true);
            s3.setIdIntervento(InterventiFactory.getInterventoByType(s3.getTipo()));

            Segnalazione s4 = new Segnalazione();
            s4.setId(4);
            s4.setLuogo("Aula M.Fisica");
            s4.setAutore(4);
            s4.setImage(v[3]);
            s4.setImage(v[4]);
            s4.setTesto("Sedie Mancandi in Aula Magna di Fisica");
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
            s7.setData("13/02/2017");
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
            s9.setSolved(true);
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
            s10.setData("17/12/2017");
            s10.setIdIntervento(InterventiFactory.getInterventoByType(s10.getTipo()));

            Segnalazione s11 = new Segnalazione();
            s11.setId(11);
            s11.setAutore(2);
            //s11.setImage(v[1]);
            s11.setLuogo("Bagni Primo Piano");
            s11.setTipo("Danno Idraulico");

            s11.setData("15/01/2018");
            s11.setTesto("Il pavimento è allagato per una perdita dal rubinetto");
            s11.setIdIntervento(InterventiFactory.getInterventoByType(s11.getTipo()));

            Segnalazione s12 = new Segnalazione();
            s12.setId(12);
            s12.setAutore(1);
            s12.setLuogo("Bagni P. Terra");
            //s12.setImage(v[2]);
            s12.setData("18/02/2017");
            s12.setTipo("Danno Idraulico");
            s12.setTesto("Manca l'acqua nei servizi");
            s12.setConfirmed(true);
            s12.setIdIntervento(InterventiFactory.getInterventoByType(s12.getTipo()));

            Segnalazione s13 = new Segnalazione();
            s13.setId(13);
            s13.setLuogo("Lab M");
            s13.setAutore(4);
            //s13.setSolved(true);
            s13.setTesto("Grosso buco sul soffitto");
            s13.setTipo("Cedimento Soffitto");
            s13.setData("15/09/2018");
            //s13.setSolved(true);
            s13.setIdIntervento(InterventiFactory.getInterventoByType(s13.getTipo()));

            Segnalazione s14 = new Segnalazione();
            s14.setId(14);
            s14.setAutore(2);
            s14.setSolved(true);
            s14.setLuogo("Aula C");
            s14.setTipo("Danno Elettrico");
            s14.setData("05/02/2018");
            s14.setTesto("Nessuna apparecchiatura elettrica sembra funzionare");
            s14.setIdIntervento(InterventiFactory.getInterventoByType(s14.getTipo()));

            Segnalazione s15 = new Segnalazione();
            s15.setId(15);
            s15.setAutore(1);
            s15.setLuogo("Aula A");
            s15.setSolved(true);
            s15.setData("07/02/2019");
            s15.setTipo("Danno Pavimento");
            s15.setTesto("Le pianelle sono spezzate e taglienti");
            s15.setConfirmed(true);
            s15.setIdIntervento(InterventiFactory.getInterventoByType(s15.getTipo()));

            Segnalazione s16 = new Segnalazione();
            s16.setId(16);
            s16.setLuogo("Aula M.Fisica");
            s16.setAutore(4);
            s16.setTesto("Quasi tutte le finestre sono rotte");
            s16.setSolved(true);
            s16.setTipo("Danno Arredi Aule");
            s16.setData("22/11/2018");
            s16.setIdIntervento(InterventiFactory.getInterventoByType(s16.getTipo()));

            Segnalazione s17 = new Segnalazione();
            s17.setId(17);
            s17.setAutore(2);
            //s17.setImage(v[5]);
            s17.setLuogo("Aula M.Matem");
            s17.setTipo("Danno Arredi Aule");
            s17.setData("12/01/2018");
            s17.setTesto("Serranda non funzionante");
            s17.setIdIntervento(InterventiFactory.getInterventoByType(s17.getTipo()));

            Segnalazione s18 = new Segnalazione();
            s18.setId(18);
            s18.setAutore(1);
            s18.setLuogo("Laboratorio T");
            //s18.setImage(v[6]);
            s18.setData("11/02/2018");
            s18.setTipo("Danno Arredi Aule");
            s18.setTesto("Il perno della porta non funziona");
            s18.setConfirmed(true);
            s18.setSolved(true);
            s18.setIdIntervento(InterventiFactory.getInterventoByType(s18.getTipo()));

            Segnalazione s19 = new Segnalazione();
            s19.setId(19);
            s19.setLuogo("Andito docenti");
            s19.setAutore(4);
            s19.setSolved(true);
            s19.setTesto("Il segnale wifi è assente");
            s19.setTipo("Danno Connettività");
            s19.setData("13/05/2017");
            s19.setIdIntervento(InterventiFactory.getInterventoByType(s19.getTipo()));

            Segnalazione s20 = new Segnalazione();
            s20.setId(20);
            s20.setAutore(2);
            s20.setSolved(true);
            s20.setLuogo("Scale interne");
            s20.setTipo("Danno Pavimento");
            s20.setData("15/01/2017");
            s20.setTesto("Il pavimento presenta buchi e pianelle mancanti");
            s20.setIdIntervento(InterventiFactory.getInterventoByType(s20.getTipo()));

            Segnalazione s21 = new Segnalazione();
            s21.setId(21);
            s21.setAutore(1);
            s21.setLuogo("Laboratorio GARR");
            //s21.setImage(v[9]);
            s21.setData("20/04/2018");
            s21.setTipo("Danno Pavimento");
            s21.setTesto("Il pavimento è rotto");
            s21.setConfirmed(true);
            s21.setIdIntervento(InterventiFactory.getInterventoByType(s21.getTipo()));

            Segnalazione s22 = new Segnalazione();
            s22.setId(22);
            s22.setLuogo("Ufficio dirigenza");
            s22.setAutore(4);
            //s22.setImage(v[10]);
            s22.setTesto("Il muro è completamente scrostato");
            s22.setTipo("Danno Intonaco");
            s22.setData("17/12/2017");
            s22.setIdIntervento(InterventiFactory.getInterventoByType(s22.getTipo()));

            Segnalazione s23 = new Segnalazione();
            s23.setId(23);
            s23.setAutore(2);
            //s23.setImage(v[1]);
            s23.setLuogo("Ufficio Segreteria");
            s23.setTipo("Danno Elettrico");
            s23.setData("25/01/2018");
            s23.setTesto("Tutte le prese producono scintille");
            s23.setIdIntervento(InterventiFactory.getInterventoByType(s23.getTipo()));

            Segnalazione s24 = new Segnalazione();
            s24.setId(24);
            s24.setAutore(1);
            s24.setLuogo("Aula A");
            s24.setSolved(true);
            s24.setData("16/07/2017");
            s24.setTipo("Danno Idraulico");
            s24.setTesto("I tubi del muro hanno allagato la classe");
            s24.setConfirmed(true);
            s24.setIdIntervento(InterventiFactory.getInterventoByType(s24.getTipo()));



            listaSegnalazioni.add(stemp);
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
            listaSegnalazioni.add(s13);
            listaSegnalazioni.add(s14);
            listaSegnalazioni.add(s15);
            listaSegnalazioni.add(s16);
            listaSegnalazioni.add(s17);
            listaSegnalazioni.add(s18);
            listaSegnalazioni.add(s19);
            listaSegnalazioni.add(s20);
            listaSegnalazioni.add(s21);
            listaSegnalazioni.add(s22);
            listaSegnalazioni.add(s23);
            listaSegnalazioni.add(s24);


        }
        public static SegnFactory getInstance(){
            if (instance == null) {
                instance = new SegnFactory();
                mMemoryCache = new HashMap<>();
                for(Segnalazione s : listaSegnalazioni){
                    if(s.getImage().size()>0)
                        try {
                            addBitmapToMemoryCache(String.valueOf(s.getId()), s.getImage().get((s.getImage().size()) - 1));
                        }catch(Exception e){
                                System.out.println(s.getId());
                        }
                }
            }
            return instance;
        }
        public static ArrayList<Segnalazione> getListaSegnalazioni(){
            //SegnFactory factory = SegnFactory.getInstance();
            ArrayList<Segnalazione> not = new ArrayList<>();
            for(Segnalazione s:listaSegnalazioni){
                if (!s.isSolved())
                    not.add(s);
            }
            return not;
        }
        public static ArrayList<Segnalazione> getListaSegnalazioniByAuthor(int id){
        //SegnFactory factory = SegnFactory.getInstance();
        ArrayList<Segnalazione> mysegnalations = new ArrayList<>();
        for(Segnalazione s : getListaSegnalazioni()){
            if (s.getAutore() == id){
                mysegnalations.add(s);
            }
        }
        return mysegnalations;
    }

    public static ArrayList<Segnalazione> getListaSegnalazioniByType(String type){
        //SegnFactory factory = SegnFactory.getInstance();
        ArrayList<Segnalazione> typedsegnalations = new ArrayList<>();
        for(Segnalazione s : getListaSegnalazioni()){
            if (s.getTipo().equals(type)){
                typedsegnalations.add(s);
            }
        }
        return typedsegnalations;
    }
    public static Segnalazione getSegnalazioneById(int id){
        //SegnFactory factory = SegnFactory.getInstance();
        for(Segnalazione s : listaSegnalazioni){
            if (s.getId() == id){
                return  s;
            }
        }
        return null;
    }
    public static int getLastSegn(){
        //SegnFactory factory = SegnFactory.getInstance();
        int m = 0;
        for(Segnalazione s:getListaSegnalazioni()){
            if (m<s.getId())
                m = s.getId();
        }
        return m;
    }
    public static void addSegnalazione(Segnalazione s){
        //SegnFactory factory = SegnFactory.getInstance();
        int max = getLastSegn();
        s.setId(max+1);
        if (s.getImage().size()>0)
            addBitmapToMemoryCache(String.valueOf(s.getId()),Bitmap.createScaledBitmap(s.getImage().get((s.getImage().size())-1), 100, 100, false));
        s.setData(new Date());
        listaSegnalazioni.add(s);

    }
    public static void modifyTemp(Segnalazione segn){
            ArrayList<Bitmap>t = segn.getImage();
            for(Bitmap b : t) {
                stemp.setImage(b);
            }
            if(segn.getTesto() != null && !segn.getTesto().equals(""))
            stemp.setTesto(segn.getTesto());
        if(segn.getTitolo() != null && !segn.getTitolo().equals(""))
            stemp.setTitolo(segn.getTitolo());
        if(segn.getLuogo() != null && !segn.getLuogo().equals(""))
            stemp.setLuogo(segn.getLuogo());

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
        //SegnFactory factory = SegnFactory.getInstance();
        ArrayList<Segnalazione> tmp = new ArrayList<>();
        for(Segnalazione s : getListaSegnalazioni()){
            tmp.add(s);
        }
        for(Segnalazione s : tmp){
            if (s.getId() == id){
                deleteBitmapToMemoryCache(String.valueOf(s.getId()));
                listaSegnalazioni.remove(s);
            }
        }
        //factory = SegnFactory.getInstance();
    }

    public static String[] getListaTItoli (){
        //SegnFactory factory = SegnFactory.getInstance();
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
            //SegnFactory sf = SegnFactory.getInstance();
            ArrayList<Segnalazione> copia = new ArrayList<>();
            for(Segnalazione s : getListaSegnalazioni()){
                copia.add(s);
            }
            Collections.sort(copia);
            return copia;
        }
        public static String[] getConsigliati(){
            InterventiFactory ifact = InterventiFactory.getInstance();
            //SegnFactory sfact = SegnFactory.getInstance();
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
        //SegnFactory sfact = SegnFactory.getInstance();
        for(Segnalazione s: getListaSegnalazioni()){
            if (s.isTemp()) {
                deleteBitmapToMemoryCache(String.valueOf(s.getId()));
                remSegnalazioneById(s.getId());
            }
        }
        //sfact = SegnFactory.getInstance();
    }
    public static String[] eseguiConsigliati(){
        InterventiFactory ifact = InterventiFactory.getInstance();
        //SegnFactory sfact = SegnFactory.getInstance();
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
           // SegnFactory sf = SegnFactory.getInstance();
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
    public static ArrayList<Segnalazione> getWaiting(boolean isWaiting, ArrayList<Segnalazione> input){
            //SegnFactory sf = SegnFactory.getInstance();
            ArrayList<Segnalazione> lista = new ArrayList<>();
            for(Segnalazione s : input){
             if(s.isConfirmed()!=isWaiting)
                 lista.add(s);
            }
            return lista;
    }
    public static ArrayList<Segnalazione> getSegnalazioniperAnno(String year){
            //SegnFactory sf = SegnFactory.getInstance();
            ArrayList<Segnalazione> anno = new ArrayList<>();
            for(Segnalazione s : sortByPriorita()){
                if(s.getData().substring(7,11).equals(year))
                    anno.add(s);
            }
            return anno;

    }


            public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
                mMemoryCache.put(key, Bitmap.createScaledBitmap(bitmap, 100, 100, false));

            }
            public static void deleteBitmapToMemoryCache(String key) {
                mMemoryCache.remove(key);

            }

            public static Bitmap getBitmapFromMemCache(String key) {
                return mMemoryCache.get(key);
            }


    public static String getSelectedCategory() {
        return selectedCategory;
    }

    public static void setSelectedCategory(String selectedCategory) {
        SegnFactory.selectedCategory = selectedCategory;
    }
}
