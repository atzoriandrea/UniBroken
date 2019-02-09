package com.example.mirko.unibroken;

public class Intervento implements Comparable<Intervento>{
    private int id;
    private String impresa;
    private double importo;
    private String tipoIntervento;
    private int priorita;

    public Intervento(){

    }

    public int getId() {
        return id;
    }

    public String getImpresa() {
        return impresa;
    }

    public double getImporto() {
        return importo;
    }

    public String getTipoIntervento() {
        return tipoIntervento;
    }

    public int getPriorita(){ return priorita; }

    public void setId(int id) {
        this.id = id;
    }

    public void setImpresa(String impresa) {
        this.impresa = impresa;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public void setTipoIntervento(String tipoIntervento) {
        this.tipoIntervento = tipoIntervento;
    }

    public void setPriorita(int priorita) {
        this.priorita = priorita;
    }

    @Override
    public int compareTo(Intervento o) {
        if(importo==o.importo)
            return 0;
        else if(importo < o.importo)
            return -1;
        else
            return 1;

    }
}

