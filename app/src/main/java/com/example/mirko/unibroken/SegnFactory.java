package com.example.mirko.unibroken;

import java.util.ArrayList;

public class SegnFactory {

        private static SegnFactory instance;
        private static ArrayList<Segnalazione> listaSegnalazioni = new ArrayList<>();
        private SegnFactory(){
            Segnalazione s1 = new Segnalazione();
            s1.setAutore(4);
            s1.setTesto("Cedimento del soffitto dell'aula D");

            Segnalazione s2 = new Segnalazione();
            s1.setAutore(2);
            s1.setTesto("CCCedimento del soffitto dell'aula D");

            Segnalazione s3 = new Segnalazione();
            s1.setAutore(1);
            s1.setTesto("DDCedimento del soffitto dell'aula D");

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

}
