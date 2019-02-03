package com.example.mirko.unibroken;

import java.util.ArrayList;

public class PersonaFactory {

    private static PersonaFactory instance;
    private static ArrayList<Persona> listaPersone = new ArrayList<>();
    private PersonaFactory(){
        Persona p1 = new Persona();
        p1.setUsername("mirko");
        p1.setPassword("mirko");

        listaPersone.add(p1);

    }
    public static PersonaFactory getInstance(){
        if (instance == null)
            instance = new PersonaFactory();
        return instance;
    }



    public static Persona login(String username, String password){
        PersonaFactory factory = PersonaFactory.getInstance();
        for(Persona p : listaPersone)
            if (p.getUsername().equals(username) && p.getPassword().equals(password))
                return p;
        return null;
    }
}
