package com.example.mirko.unibroken;

import java.util.ArrayList;

public class SegnFactory {

        private static com.example.mirko.unibroken.SegnFactory instance;
        private static ArrayList<Segnalazione> listaSegnalazioni = new ArrayList<>();
        private SegnFactory(){
            Segnalazione s1 = new Segnalazione();
            s1.setAutore(4);
            s1.setTesto("Cedimento del soffitto dell'aula D");
        }
}
