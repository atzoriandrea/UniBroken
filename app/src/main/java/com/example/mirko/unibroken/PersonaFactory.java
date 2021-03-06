package com.example.mirko.unibroken;

import java.util.ArrayList;

public class PersonaFactory {

    private static PersonaFactory instance;
    private static ArrayList<Persona> listaPersone = new ArrayList<>();
    private PersonaFactory(){
        Persona p1 = new Persona();
        p1.setId(1);
        p1.setNome("Mirko");
        p1.setCognome("Muscas");
        p1.setUsername("m.mirko21");
        p1.setPassword("mirko");

        Persona p4 = new Persona();
        p4.setNome("Andrea");
        p4.setCognome("Atzori");
        p4.setId(4);
        p4.setUsername("a.atzori28");
        p4.setPassword("andrea");

        Persona p2 = new Persona();
        p2.setNome("Riccardo");
        p2.setCognome("Scateni");
        p2.setId(2);
        p2.setUsername("r.scateni23");
        p2.setPassword("ricscat");

        Persona p3 = new Persona();
        p3.setNome("Francesco");
        p3.setCognome("Raga");
        p3.setId(3);
        p3.setUsername("ragafrancesco");
        p3.setPassword("unica");
        p3.setRaga(true);

        listaPersone.add(p1);
        listaPersone.add(p2);
        listaPersone.add(p3);
        listaPersone.add(p4);

    }
    public static PersonaFactory getInstance(){
        if (instance == null)
            instance = new PersonaFactory();
        return instance;
    }



    public static Persona login(String username, String password){
        PersonaFactory factory = PersonaFactory.getInstance();

        for(Persona p : listaPersone) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password))
                return p;
        }
        return null;
    }

    public static Persona getPersonaById(int id){
        PersonaFactory factory = PersonaFactory.getInstance();
        for(Persona p : listaPersone){
            if (p.getId() == id)
                return p;

        }
        return  null;
    }
}
