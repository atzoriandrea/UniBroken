package com.example.mirko.unibroken;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class ShowsegnRaga extends AppCompatActivity {
    TextView titolo;
    ImageView img, overlay;
    Button indietro;
    TextView descrizione;
    TextView testotitolo;
    TextView luogo;
    TextView costo;
    TextView impresa;
    Button conf;
    Button rconf;
    Persona p1;
    float scale;
    Segnalazione s;
    //Bitmap[] array = new Bitmap[1];
    public static final String BITMAP_EXTRA="java.lang.Integer";
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsegn_raga);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(ListaSegnRaga.SEGN);
        Bundle bundle = getIntent().getExtras();
        SegnFactory sf = SegnFactory.getInstance();
        InterventiFactory ifact = InterventiFactory.getInstance();
        int i = (Integer) obj;
        Serializable obj2 = intent.getSerializableExtra(HomeRaga.PERSONA_EXTRA);
        p1 = (Persona)obj2;
        s = SegnFactory.getSegnalazioneById(i);
        scale = getResources().getDisplayMetrics().density;
        indietro = (Button)findViewById(R.id.back);
        titolo = (TextView)findViewById(R.id.tit);
        titolo.setText("Segnalazione #"+String.valueOf(s.getId()));
        img = (ImageView)findViewById(R.id.imgdett);
        impresa =(TextView)findViewById(R.id.impresa);
        conf = (Button)findViewById(R.id.intconf);
        rconf = (Button)findViewById(R.id.intrem);
        overlay = (ImageView)findViewById(R.id.overlay);
        descrizione = (TextView)findViewById(R.id.des);
        luogo = (TextView)findViewById(R.id.loc);
        //testoluogo = (TextView)findViewById(R.id.loctext);
        descrizione.setText(s.getTesto());
        impresa.setText("Impresa convenzionata: " + InterventiFactory.getImpresaByType(s.getTipo()));
        luogo.setText("Luogo: " + s.getLuogo());
        costo = (TextView)findViewById(R.id.costo);
        costo.setText("Costo approssimato: € "+String.valueOf((ifact.getInterventoById(s.getIdIntervento()).getImporto()))+"0");
        if(s.getImage().size()>0) {
            img.setImageBitmap(s.getImage().get(s.getImage().size() - 1));
            if (s.getImage().size()>1) {
                Bitmap b2 = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(b2);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                // text color - #3D3D3D
                paint.setColor(Color.rgb(255, 255, 255));
                // text size in pixels
                paint.setTextSize((int) (14 * scale));
                Rect bounds = new Rect();
                RectF rectF = new RectF(bounds);
                String str = "+" + String.valueOf(s.getImage().size()-1);
                paint.getTextBounds(str, 0, str.length(), bounds);
                int x = (b2.getWidth() - bounds.width())/2;
                int y = (b2.getHeight() + bounds.height())/2;

                canvas.drawText(str, x, y, paint);
                canvas.drawRoundRect(rectF, 100, 100, paint);
                overlay.setBackgroundColor(Color.BLACK);
                overlay.setImageBitmap(b2);
                overlay.setAlpha(0.5f);
            }

        }else
            img.setImageResource(R.drawable.dummy_image_square);
        //array[0] = s.getImage();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switcher = new Intent(ShowsegnRaga.this,ImgSwitcherShow.class);
                switcher.putExtra(BITMAP_EXTRA, s.getId());
                startActivity(switcher);
            }
        });
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(ShowsegnRaga.this)
                        .setTitle("Aggiungi ai preventivi")
                        .setMessage("Aggiungere la segnalazione ai preventivi?\n\n" +
                                "Il costo della segnalazione verrà scalato dal budget disponibile")
                        .setPositiveButton("CONFERMA", null)
                        .setNegativeButton("ANNULLA", null)
                        .show();


                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s.setConfirmed(true);
                        showPositiveFeedbackWindows(ShowsegnRaga.this);
                        dialog.hide();
                        if(s.isConfirmed() == true){
                            conf.setVisibility(View.GONE);
                            rconf.setVisibility(View.VISIBLE);
                        }else{
                            rconf.setVisibility(View.GONE);
                            conf.setVisibility(View.VISIBLE);

                        }
                    }
                });

            }
        });
        rconf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(ShowsegnRaga.this)
                        .setTitle("Rimuovi dai preventivi")
                        .setMessage("Rimuovere la segnalazione dai preventivi?\n\n" +
                                "Il costo della segnalazione verrà aggiunto al budget disponibile")
                        .setPositiveButton("CONFERMA", null)
                        .setNegativeButton("ANNULLA", null)
                        .show();


                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s.setConfirmed(false);
                        showNegativeFeedbackWindows(ShowsegnRaga.this);
                        dialog.hide();
                        if(s.isConfirmed() == true){
                            conf.setVisibility(View.GONE);
                            rconf.setVisibility(View.VISIBLE);
                        }else{
                            rconf.setVisibility(View.GONE);
                            conf.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
        if(s.isConfirmed() == true){
            conf.setVisibility(View.GONE);
            rconf.setVisibility(View.VISIBLE);
        }else{
            rconf.setVisibility(View.GONE);
            conf.setVisibility(View.VISIBLE);

        }
    }
    public void showPositiveFeedbackWindows(Context c){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        final AlertDialog alert = dialog.create();
        alert.setCanceledOnTouchOutside(false);
        TextView titoloCustom = new TextView(c);
        titoloCustom.setText("Preventivo Aggiunto");
        titoloCustom.setPadding(10, 20, 10, 0);
        titoloCustom.setGravity(Gravity.CENTER);
        titoloCustom.setTextColor(Color.rgb(2, 45, 126));
        titoloCustom.setTextSize(20);
        alert.setCustomTitle(titoloCustom);
        alert.show();
        alert.getWindow().setLayout(800, 210);  //Per settare le dimensioni del dialog
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                }
            }
        };
        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);

            }
        });
        handler.postDelayed(runnable, 1500);
        //isok = false;//Ritardo di scomparsa della finestra a 1 secondoo
    }
    public void showNegativeFeedbackWindows(Context c){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        final AlertDialog alert = dialog.create();
        alert.setCanceledOnTouchOutside(false);
        TextView titoloCustom = new TextView(c);
        titoloCustom.setText("Preventivo Rimosso");
        titoloCustom.setPadding(10, 20, 10, 0);
        titoloCustom.setGravity(Gravity.CENTER);
        titoloCustom.setTextColor(Color.rgb(2, 45, 126));
        titoloCustom.setTextSize(20);
        alert.setCustomTitle(titoloCustom);
        alert.show();
        alert.getWindow().setLayout(800, 210);  //Per settare le dimensioni del dialog
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                }
            }
        };
        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });
        handler.postDelayed(runnable, 1500);
        //isok = false;//Ritardo di scomparsa della finestra a 1 secondoo
    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(ShowsegnRaga.this, ListaSegnRaga.class);
        indietro.putExtra(PERSONA_EXTRA,p1);
        startActivity(indietro);
    }
    public void logout(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
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
                Intent logout = new Intent(ShowsegnRaga.this, MainActivity.class);
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
