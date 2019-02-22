package com.example.mirko.unibroken;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.Serializable;
import java.util.ArrayList;

public class ChooseType extends AppCompatActivity {
    ArrayList<String> cat = new ArrayList<>();
    ArrayList<String> temp;
    ListView listaCat;
    CategoryOption.Adattatore a;
    SearchView searchView;
    Persona p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);
        listaCat = (ListView)findViewById(R.id.CatList);
        SegnFactory sf = SegnFactory.getInstance();
        InterventiFactory ifact = InterventiFactory.getInstance();
        cat.add("Tutte le segnalazioni");
        temp = InterventiFactory.getListaTipi();
        for(String s : temp){
            cat.add(s);
        }
        a = new CategoryOption.Adattatore(this,cat);
        searchView = (SearchView)findViewById(R.id.search_bar);
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                searchView.clearFocus();
            }
        }, 1);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Homepage.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();
        p1 = (Persona)obj;
        listaCat.setAdapter(a);
        listaCat.setScrollbarFadingEnabled(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String c = newText.toLowerCase();
                ArrayList<String> searched = new ArrayList<>();
                for (String s: cat){
                    if(s.toLowerCase().contains(c))
                        searched.add(s);
                }
                a = new CategoryOption.Adattatore(ChooseType.this,searched);
                listaCat.setAdapter(a);
                return false;
            }
        });

        listaCat.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                return false;
            }
        });
        listaCat.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                SegnFactory.setSelectedCategory((String) listaCat.getItemAtPosition(position));
                Intent backToList;
                if(p1.getId()==3){
                    backToList = new Intent(ChooseType.this,ListaSegnRaga.class);

                }
                else{
                    backToList = new Intent(ChooseType.this,ListaSegn.class);
                }
                backToList.putExtra(ListaSegn.PERSONA_EXTRA,p1);
                startActivity(backToList);

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent indietro;
        if(p1.getId()==3){
            indietro = new Intent(ChooseType.this,ListaSegnRaga.class);

        }
        else{
            indietro = new Intent(ChooseType.this, ListaSegn.class);        }

        SegnFactory.setSelectedCategory("Tutte le segnalazioni");
        indietro.putExtra(Homepage.PERSONA_EXTRA,p1);
        startActivity(indietro);
    }
    public void logout(View view) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Sei sicuro di voler eseguire il logout?")
                .setPositiveButton("CONFERMA", null)
                .setNegativeButton("ANNULLA", null)
                .show();


        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to Open Image applications like Gallery, Google Photos
                Intent logout = new Intent(ChooseType.this, MainActivity.class);
                // Start the Intent
                startActivity(logout);
            }
        });

    }
    public void back(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        onBackPressed();
    }
}
