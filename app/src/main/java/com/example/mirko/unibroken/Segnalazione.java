package com.example.mirko.unibroken;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.io.Serializable;
import java.util.ArrayList;

public class Segnalazione implements Serializable {
    private int id;
    private int autore;
    public ArrayList<Bitmap> images;
    private String tipo;
    private String testo;
    private String luogo;
    private int idIntervento;

    public Segnalazione(){
        images = new ArrayList<>();
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


}
