package com.example.mirko.unibroken;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class Raga_Budget extends AppCompatActivity {

    Button indietro;
    Persona p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raga__budget);
        indietro = (Button)findViewById(R.id.back);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Homepage.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();
        p = (Persona)obj;






    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(Raga_Budget.this, HomeRaga.class);
        indietro.putExtra(Homepage.PERSONA_EXTRA, p);
        startActivity(indietro);
    }

}
