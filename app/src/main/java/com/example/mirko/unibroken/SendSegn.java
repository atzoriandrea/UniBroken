package com.example.mirko.unibroken;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.Serializable;

public class SendSegn extends AppCompatActivity {
    private ImageView imageHolder;
    private final int requestCode = 20;
    Button capturedImageButton;
    EditText t;
    Button save;
    public static final String PERSONA_EXTRA="com.example.mirko.esercitazionebonus.Persona";
    Persona p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_segn);
        save = (Button)findViewById(R.id.save);
        capturedImageButton= (Button)findViewById(R.id.addphoto);
        Spinner luogo = (Spinner) findViewById(R.id.luogo);
        imageHolder = (ImageView)findViewById(R.id.addedphoto);
        t = (EditText)findViewById(R.id.mydescription);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(MainActivity.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();

        p = (Persona)obj;
        // Create an ArrayAdapter using the string array and a default spinner layout
        Spinner dropdown = findViewById(R.id.tipologia);
        String[] items = new String[]{"Danno Intonaco",
                "Danno Finestre",
                "Cedimento Soffitto",
                "Danno Idraulico",
                "Danno Elettrico",
                "Danno Pavimento",
                "Danno Connettività",
                "Danno Condizionatore(i)",
                "Danno Arredi Aule"};

        String[] luoghi = new String[] {"Laboratorio T","Laboratorio M", "Aula Costa", "Aula M.Matematica", "Aula M.Fisica", "Aula Studio", "Laboratorio GARR", "Aula A", "Aula B", "Aula C", "Bagni P.Terra",
        "Bagni 1° Piano", "Bagni 2° Piano", "Bagni seminterrato"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, luoghi);

        dropdown.setAdapter(adapter);
        luogo.setAdapter(adapter2);



        capturedImageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SegnFactory factory = SegnFactory.getInstance();
                Segnalazione s = new Segnalazione();
                s.setAutore(p.getId());
                s.setImage(imageHolder.getId());
                s.setTesto(t.getText().toString());
                SegnFactory.addSegnalazione(s);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageHolder.setImageBitmap(bitmap);

        }
    }

}
