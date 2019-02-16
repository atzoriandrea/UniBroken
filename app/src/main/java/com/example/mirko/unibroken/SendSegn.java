package com.example.mirko.unibroken;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
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
import java.util.Date;

public class SendSegn extends AppCompatActivity {
    private ImageView imageHolder;
    ImageView overlay;
    private final int requestCode = 20;
    Button capturedImageButton;
    static EditText t;
    Button save;
    Button galleria;
    Bitmap bitmap;
    Button indietro;
    BitmapDrawable bd;
    String titolo;
    String imgDecodableString = null;
    Bitmap [] array = new Bitmap[3];
    static Context c;
    private static final int PICK_IMAGE_REQUEST = 100;
    private static int RESULT_LOAD_IMG = 1;
    //Segnalazione s = new Segnalazione();
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";
    Persona p;
    ArrayList<Bitmap> tmp = new ArrayList<>();
    float scale;
    static Spinner dropdown;
    static Spinner luogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_segn);
        scale = getResources().getDisplayMetrics().density;
        save = (Button)findViewById(R.id.save);
        capturedImageButton= (Button)findViewById(R.id.addphoto);
        luogo  = (Spinner) findViewById(R.id.luogo);
        imageHolder = (ImageView)findViewById(R.id.addedphoto);
        overlay = (ImageView)findViewById(R.id.overlay);
        galleria = (Button)findViewById(R.id.addImage);
        t = (EditText)findViewById(R.id.mydescription);
        indietro = (Button)findViewById(R.id.back);
        Intent intent = getIntent();
        c = this;
        Serializable obj = intent.getSerializableExtra(Homepage.PERSONA_EXTRA);
        Bundle bundle = getIntent().getExtras();
        p = (Persona)obj;
        // Create an ArrayAdapter using the string array and a default spinner layout
        dropdown = findViewById(R.id.tipologia);
        String[] items = new String[]{"Seleziona un'opzione","Danno Intonaco",
                "Danno Finestre",
                "Cedimento Soffitto",
                "Danno Idraulico",
                "Danno Elettrico",
                "Danno Pavimento",
                "Danno Connettività",
                "Danno Condizionatore(i)",
                "Danno Arredi Aule"};

        String[] luoghi = new String[] {"Seleziona un'opzione","Laboratorio T","Laboratorio M", "Aula Costa", "Aula M.Matematica", "Aula M.Fisica", "Aula Studio", "Laboratorio GARR", "Aula A", "Aula B", "Aula C", "Bagni P.Terra",
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
                if (checkInput()){
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
                        Date d = new Date();
                        SegnFactory factory = SegnFactory.getInstance();
                        InterventiFactory ifact = InterventiFactory.getInstance();
                        SegnFactory.removeTemp();
                        Segnalazione s = new Segnalazione();
                        s.setAutore(p.getId());
                        s.setTipo(dropdown.getSelectedItem().toString());
                        s.setTesto(t.getText().toString());
                        s.setLuogo(luogo.getSelectedItem().toString());
                        for (Bitmap b : tmp) {
                            s.setImage(b);
                        }
                        s.setData(d);
                        s.setIdIntervento(InterventiFactory.getInterventoByType(dropdown.getSelectedItem().toString()));
                        SegnFactory.addSegnalazione(s);
                        factory = SegnFactory.getInstance();
                        Intent menu = new Intent(SendSegn.this, Homepage.class);
                        menu.putExtra(PERSONA_EXTRA, p);
                        startActivity(menu);
                    }
                });
            }
            }

        });
        imageHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tmp.size()>0) {
                    SegnFactory sf = SegnFactory.getInstance();
                    Segnalazione s = new Segnalazione();
                    for (Bitmap b : tmp){
                        s.setImage(b);
                    }
                    s.setData(new Date());
                    s.setTemp(true);
                    SegnFactory.addSegnalazione(s);
                    sf = SegnFactory.getInstance();
                    Intent switcher = new Intent(SendSegn.this, ImgSwitcherShow.class);
                    switcher.putExtra(Showsegn.BITMAP_EXTRA, SegnFactory.getLastSegn());
                    startActivity(switcher);
                }


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
                if (tmp.size()>1) {
                    Bitmap b2 = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(b2);
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    // text color - #3D3D3D
                    paint.setColor(Color.rgb(255, 255, 255));
                    // text size in pixels
                    paint.setTextSize((int) (14 * scale));
                    Rect bounds = new Rect();
                    RectF rectF = new RectF(bounds);
                    String s = "+" + String.valueOf(tmp.size()-1);
                    paint.getTextBounds(s, 0, s.length(), bounds);
                    int x = (b2.getWidth() - bounds.width())/2;
                    int y = (b2.getHeight() + bounds.height())/2;

                    canvas.drawText(s, x, y, paint);
                    canvas.drawRoundRect(rectF, 100, 100, paint);
                    overlay.setBackgroundColor(Color.BLACK);
                    overlay.setImageBitmap(b2);
                    overlay.setAlpha(0.5f);
                }
            }
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    Toast.makeText(SendSegn.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    tmp.add(bitmap);
                    imageHolder.setImageBitmap(bitmap);
                    if (tmp.size()>1) {
                        Bitmap b2 = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(b2);
                        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                        paint.setColor(Color.rgb(255, 255, 255));
                        paint.setTextSize((int) (14 * scale));
                        Rect bounds = new Rect();
                        RectF rectF = new RectF(bounds);
                        String s = "+" + String.valueOf(tmp.size()-1);
                    paint.getTextBounds(s, 0, s.length(), bounds);
                    int x = (b2.getWidth() - bounds.width())/2;
                    int y = (b2.getHeight() + bounds.height())/2;
                    canvas.drawText(s, x, y, paint);
                    canvas.drawRoundRect(rectF, 100, 100, paint);
                    overlay.setBackgroundColor(Color.BLACK);
                    overlay.setImageBitmap(b2);
                    overlay.setAlpha(0.5f);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(SendSegn.this, "Errore nel caricamento dell'immagine", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Foto importata con successo",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Qualcosa è andato storto", Toast.LENGTH_LONG)
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
        onBackPressed();
    }
    public boolean checkInput(){
        int errori = 0;
        boolean dd = false;
        boolean l = false;
        if(t.getText() == null || t.getText().length() == 0) {
            t.setError("Inserire un commento");
            errori ++;
        }
        if(dropdown.getSelectedItem().toString().equals("Seleziona un'opzione")) {
            dd = true;
            errori++;
        }
        if(luogo.getSelectedItem().toString().equals("Seleziona un'opzione")) {
            l = true;
            errori++;
        }
        if (l || dd) {
            if (l && dd)
                Toast.makeText(SendSegn.this, "Non hai inserito la tipologia e il luogo",Toast.LENGTH_LONG).show();
            if (l && !dd)
                Toast.makeText(SendSegn.this, "Non hai inserito il luogo",Toast.LENGTH_LONG).show();
            if(!l && dd)
                Toast.makeText(SendSegn.this, "Non hai inserito la tipologia",Toast.LENGTH_LONG).show();
        }
        return errori==0;
    }





}
