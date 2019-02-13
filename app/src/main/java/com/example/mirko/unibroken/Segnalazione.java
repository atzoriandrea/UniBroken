package com.example.mirko.unibroken;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Segnalazione implements Serializable, Comparable<Segnalazione> {
    private int id;
    private int autore;
    private String titolo;
    public ArrayList<Bitmap> images;
    private String tipo;
    private boolean isConfirmed;
    private String testo;
    private String luogo;
    private Date data;
    private int idIntervento;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");



    public Segnalazione(){
        images = new ArrayList<>();
        setConfirmed(false);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {return id;}

    public void setId(int id){this.id = id;}

    public int getAutore() {
        return autore;
    }

    public int getIdIntervento(){ return idIntervento; }

    public void setIdIntervento(int idIntervento) {
        this.idIntervento = idIntervento;
    }

    public void setAutore(int autore) {
        this.autore = autore;
    }

    public ArrayList<Bitmap> getImage() {
        return images;
    }

    public void setImage(Bitmap image) {
        this.images.add(image);
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getData() {
        return data.toString().substring(8,10)+ " " + traduciData(data.toString().substring(4,7))+ " " + data.toString().substring(30,34);
    }

    public void setData(String data)  {
        try {
            this.data = df.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setData(Date data)  {
        this.data = data;
    }

    public String traduciData (String engDate){
        switch (engDate){
            case "Jan" : return "Gen";
            case "May" : return "Mag";
            case "Jun" : return "Giu";
            case "Jul" : return "Lug";
            case "Aug" : return "Ago";
            case "Sep" : return "Set";
            case "Oct" : return "Ott";
            case "Dec" : return "Dic";
            default: return engDate;
        }
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    @Override
    public int compareTo(@NonNull Segnalazione o) {
        InterventiFactory ifact = InterventiFactory.getInstance();
        if (InterventiFactory.getInterventoById(this.getIdIntervento()).getPriorita() < InterventiFactory.getInterventoById(o.getIdIntervento()).getPriorita())
            return 1;
        if (InterventiFactory.getInterventoById(this.getIdIntervento()).getPriorita() > InterventiFactory.getInterventoById(o.getIdIntervento()).getPriorita())
            return -1;
        else
            return 0;
    }
}
