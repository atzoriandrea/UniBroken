package com.example.mirko.unibroken;

import java.io.Serializable;

public class Segnalazione implements Serializable {
    private int autore;
    public int image;
    private String testo;
    public Segnalazione(){

    }

    public int getAutore() {
        return autore;
    }

    public void setAutore(int autore) {
        this.autore = autore;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }


}
