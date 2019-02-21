package com.example.mirko.unibroken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class InterventiFactory {
    private static InterventiFactory instance;
    private static ArrayList<Intervento> listaInterventi = new ArrayList<>();
    private static  double budget = 30000.00;
    private InterventiFactory() {
        Intervento i1 = new Intervento();
        Intervento i2 = new Intervento();
        Intervento i3 = new Intervento();
        Intervento i4 = new Intervento();
        Intervento i5 = new Intervento();
        Intervento i6 = new Intervento();
        Intervento i7 = new Intervento();
        Intervento i8 = new Intervento();
        Intervento i9 = new Intervento();

        i1.setId(1);
        i2.setId(2);
        i3.setId(3);
        i4.setId(4);
        i5.setId(5);
        i6.setId(6);
        i7.setId(7);
        i8.setId(8);
        i9.setId(9);

        i1.setImporto(800.00);
        i2.setImporto(1000.00);
        i3.setImporto(20000.00);
        i4.setImporto(450.00);
        i5.setImporto(5500.00);
        i6.setImporto(900.00);
        i7.setImporto(400.00);
        i8.setImporto(1200.00);
        i9.setImporto(300.00);

        i1.setImpresa("EdilCalaris s.r.l.");
        i2.setImpresa("PianuInfissi s.r.l.");
        i3.setImpresa("EdilCalaris s.r.l.");
        i4.setImpresa("IdraoCaralis s.r.l.");
        i5.setImpresa("Fulminati s.r.l.");
        i6.setImpresa("EdilCalaris s.r.l");
        i7.setImpresa("NetBusiness S.p.A");
        i8.setImpresa("Gi.MA Impianti");
        i9.setImpresa("ScuolArreda s.r.l.s.");


        i1.setTipoIntervento("Danno Intonaco");
        i2.setTipoIntervento("Danno Finestre");
        i3.setTipoIntervento("Cedimento Soffitto");
        i4.setTipoIntervento("Danno Idraulico");
        i5.setTipoIntervento("Danno Elettrico");
        i6.setTipoIntervento("Danno Pavimento");
        i7.setTipoIntervento("Danno Connettività");
        i8.setTipoIntervento("Danno Condizionatore(i)");
        i9.setTipoIntervento("Danno Arredi Aule");

        i1.setPriorita(3);
        i2.setPriorita(4);
        i3.setPriorita(10);
        i4.setPriorita(7);
        i5.setPriorita(8);
        i6.setPriorita(2);
        i7.setPriorita(6);
        i8.setPriorita(5);
        i9.setPriorita(4);

        listaInterventi.add(i1);
        listaInterventi.add(i2);
        listaInterventi.add(i3);
        listaInterventi.add(i4);
        listaInterventi.add(i5);
        listaInterventi.add(i6);
        listaInterventi.add(i7);
        listaInterventi.add(i8);
        listaInterventi.add(i9);

    }
    public static String getImpresaByType(String tipo){
        return listaInterventi.get(getInterventoByType(tipo)-1).getImpresa();
    }
    public static InterventiFactory getInstance() {
        if (instance == null)
            instance = new InterventiFactory();
        return instance;
    }
    public static ArrayList<String> getListaTipi(){
        InterventiFactory factory = InterventiFactory.getInstance();
        ArrayList<String> list = new ArrayList<>();
        for(Intervento i : listaInterventi){
            list.add(i.getTipoIntervento());
        }
        return list;
    }
    public static ArrayList<Intervento> getListaInterventi() {
        InterventiFactory factory = InterventiFactory.getInstance();
        return listaInterventi;
    }

    public static ArrayList<Intervento> getListaInterventiByType(String tipo) {
        InterventiFactory factory = InterventiFactory.getInstance();
        ArrayList<Intervento> interventi = new ArrayList<>();
        for (Intervento i : listaInterventi) {
            if (i.getTipoIntervento().equals(tipo)) {
                interventi.add(i);
            }
        }
        return interventi;
    }
    public static int getInterventoByType(String tipo) {
        InterventiFactory factory = InterventiFactory.getInstance();
        int id = 0;
        for (Intervento i : listaInterventi) {
            if (i.getTipoIntervento().equals(tipo)) {
                id = i.getId();
            }
        }
        return id;
    }


    public static Intervento getInterventoById(int id) {
        InterventiFactory factory = InterventiFactory.getInstance();
        for (Intervento i : listaInterventi) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public static int getLastInt(){
        InterventiFactory factory = InterventiFactory.getInstance();
        int m = 0;
        for(Intervento i:listaInterventi){
            if (m<i.getId())
                m = i.getId();
        }
        return m;
    }

    public static void addIntervento(Intervento i){
        InterventiFactory factory = InterventiFactory.getInstance();
        int max = getLastInt();
        i.setId(max+1);
        listaInterventi.add(i);

    }
    public static void remInterventoById(int id){
        InterventiFactory factory = InterventiFactory.getInstance();
        ArrayList<Intervento> tmp = new ArrayList<>();
        for(Intervento i : listaInterventi){
            tmp.add(i);
        }
        for(Intervento i : tmp){
            if (i.getId() == id){
                listaInterventi.remove(i);
            }
        }
        factory = InterventiFactory.getInstance();
    }
    public static ArrayList<Intervento> getListaInterventibyPriceMax(){
        ArrayList<Intervento> retList = listaInterventi;
        Collections.sort(retList);
        return retList;
    }

    public static ArrayList<Intervento> getListaInterventibyPriceMin(){
        ArrayList<Intervento> retList = listaInterventi;
        Collections.sort(retList);
        Collections.reverse(retList);
        return retList;
    }

    public static ArrayList<Intervento> getListaInterventiByPriorita(int p) {
        InterventiFactory factory = InterventiFactory.getInstance();
        ArrayList<Intervento> interventi = new ArrayList<>();
        for (Intervento i : listaInterventi) {
            if (i.getPriorita() == p) {
                interventi.add(i);
            }
        }
        return interventi;
    }

    public static double getBudget() {
        return budget;
    }

    public static void setBudget(double budget) {
        InterventiFactory.budget = budget;
    }
}
