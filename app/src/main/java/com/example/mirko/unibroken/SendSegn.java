package com.example.mirko.unibroken;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class SendSegn extends AppCompatActivity {
    private ImageView imageHolder;
    private final int requestCode = 20;
    Button capturedImageButton;
    EditText t;
    Button save;
    Button galleria;
    Bitmap bitmap;
    Button indietro;
    BitmapDrawable bd;
    String titolo;
    String imgDecodableString = null;
    Bitmap [] array = new Bitmap[3];
    Context c;
    private static final int PICK_IMAGE_REQUEST = 100;
    private static int RESULT_LOAD_IMG = 1;
    //Segnalazione s = new Segnalazione();
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";
    Persona p;
    ArrayList<Bitmap> tmp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_segn);
        save = (Button)findViewById(R.id.save);
        capturedImageButton= (Button)findViewById(R.id.addphoto);
        final Spinner luogo = (Spinner) findViewById(R.id.luogo);
        imageHolder = (ImageView)findViewById(R.id.addedphoto);
        galleria = (Button)findViewById(R.id.addImage);
        t = (EditText)findViewById(R.id.mydescription);
        indietro = (Button)findViewById(R.id.back);
        Intent intent = getIntent();
        c = this;
        Serializable obj = intent.getSerializableExtra(Homepage.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();
        p = (Persona)obj;
        // Create an ArrayAdapter using the string array and a default spinner layout
        final Spinner dropdown = findViewById(R.id.tipologia);
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
                AlertDialog dialog = new AlertDialog.Builder(c)
                        .setTitle("Invia Segnalazione")
                        .setMessage("Sei sicuro di voler inviare la segnalazione?")
                        .setPositiveButton("CONFERMA", null)
                        .setNegativeButton("ANNULLA", null)
                        .show();


                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SegnFactory factory = SegnFactory.getInstance();
                        Segnalazione s = new Segnalazione();
                        s.setAutore(p.getId());
                        //getResourseId(SendSegn.this, "myIcon", "drawable", getPackageName());
                        //bd = new BitmapDrawable(getResources(), bitmap);
                        //MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, titolo , "foto");
                        s.setTipo(dropdown.getSelectedItem().toString());
                        s.setTesto(t.getText().toString());
                        //s.setData("11/02/2019");
                        s.setLuogo(luogo.getSelectedItem().toString());
                        for (Bitmap b : tmp){
                            s.setImage(b);
                        }
                        SegnFactory.addSegnalazione(s);
                        factory = SegnFactory.getInstance();
                        Intent menu = new Intent(SendSegn.this, Homepage.class);
                        menu.putExtra(PERSONA_EXTRA,p);
                        startActivity(menu);
                    }
                });

            }
        });
    }
    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if(this.requestCode == requestCode && resultCode == RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
                tmp.add(bitmap);
                imageHolder.setImageBitmap(bitmap);
            }
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                //String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                //Cursor cursor = getContentResolver().query(selectedImage,
                       // filePathColumn, null, null, null);
                // Move to first row
                //cursor.moveToFirst();

                //int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                //imgDecodableString = cursor.getString(columnIndex);
                //cursor.close();
                //imageHolder.setImageBitmap(decodeFile(imgDecodableString));
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    Toast.makeText(SendSegn.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    tmp.add(bitmap);
                    imageHolder.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(SendSegn.this, "Failed!", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }
    public static Bitmap decodeFile(String photoPath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, options);

        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inPreferQualityOverSpeed = true;

        return BitmapFactory.decodeFile(photoPath, options);
    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(SendSegn.this, Homepage.class);
        indietro.putExtra(Homepage.PERSONA_EXTRA,p);
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
                Intent logout = new Intent(SendSegn.this, MainActivity.class);
                // Start the Intent
                startActivity(logout);
            }
        });

    }
    public void back(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        indietro.setBackgroundColor(Color.GREEN);
        onBackPressed();
    }






}
