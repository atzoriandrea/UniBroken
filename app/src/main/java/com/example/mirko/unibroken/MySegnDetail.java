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

public class MySegnDetail extends AppCompatActivity {
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";

    TextView titolo;
    ImageView img, overlay;
    TextView descrizione;
    TextView luogo;
    Button indietro;
    Segnalazione s;
    Button elimina;
    Persona p;
    Context c;
    float scale;
    TextView testotitolo,data,testoluogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_segn_detail);
        SegnFactory factory = SegnFactory.getInstance();
        scale = getResources().getDisplayMetrics().density;
        c = this;
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(MySegn.SEGN);
        Serializable obj2 = intent.getSerializableExtra(MySegn.PERSONA_EXTRA);

        Bundle bundle = getIntent().getExtras();
        int i = (Integer) obj;
        s = SegnFactory.getSegnalazioneById(i);
        testotitolo = (TextView)findViewById(R.id.tittext);
        p = (Persona) obj2;
        titolo = (TextView)findViewById(R.id.tit);
        img = (ImageView)findViewById(R.id.imgdett);
        descrizione = (TextView)findViewById(R.id.des);
        luogo = (TextView)findViewById(R.id.loc);
        elimina = (Button)findViewById(R.id.elimina);
        indietro = (Button)findViewById(R.id.back);
        overlay = (ImageView)findViewById(R.id.overlay);
        descrizione.setText(s.getTesto());
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
        testotitolo.setText("Segnalazione #"+String.valueOf(s.getId()));
        data = (TextView)findViewById(R.id.datetext);
        data.setText(s.getData());
        testoluogo = (TextView)findViewById(R.id.loctext);
        overlay = (ImageView)findViewById(R.id.overlay);
        testoluogo.setText(s.getLuogo());
        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(c)
                        .setTitle("Elimina segnalazione")
                        .setMessage("Sei sicuro di voler rimuovere la segnalazione?")
                        .setPositiveButton("CONFERMA", null)
                        .setNegativeButton("ANNULLA", null)
                        .show();
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                        showFeedbackWindows(MySegnDetail.this);
                    }
                });
            }
        });
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
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switcher = new Intent(MySegnDetail.this,ImgSwitcherShow.class);
                switcher.putExtra(Showsegn.BITMAP_EXTRA, s.getId());
                startActivity(switcher);
            }
        });
    }
    public void showFeedbackWindows(Context c){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        final AlertDialog alert = dialog.create();
        alert.setCanceledOnTouchOutside(false);
        TextView titoloCustom = new TextView(c);
        titoloCustom.setText("Segnalazione Eliminata");
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
                SegnFactory.remSegnalazioneById(s.getId());
                Intent segnalazioni = new Intent(MySegnDetail.this, MySegn.class);
                segnalazioni.putExtra(PERSONA_EXTRA, p);
                startActivity(segnalazioni);
            }
        });
        handler.postDelayed(runnable, 1500);
        //isok = false;//Ritardo di scomparsa della finestra a 1 secondoo
    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(MySegnDetail.this, MySegn.class);
        indietro.putExtra(PERSONA_EXTRA, p);
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
                Intent logout = new Intent(MySegnDetail.this, MainActivity.class);
                // Start the Intent
                startActivity(logout);
            }
        });
    }
    public void back(View view) {
        onBackPressed();
    }

}
