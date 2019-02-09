package com.example.mirko.unibroken;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.io.Serializable;

public class Segnalazione implements Serializable {
    private int id;
    private int autore;
    public Bitmap image;
    private String tipo;
    private String testo;
    private String luogo;
    private int idIntervento;

    public Segnalazione(){

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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
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
