package com.example.mirko.unibroken;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.app.AlertDialog;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button accedi;
    TextView errore;
    TextView link;
    Persona p2;
    boolean p;
    Toast t;
    TextInputLayout show;
    static Bitmap[] array;
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";
    //AlertDialog.Builder builder=new AlertDialog.Builder(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (array == null){
            array = new Bitmap[11];
            //recupero gli ID
            array[0] = BitmapFactory.decodeResource(getResources(),R.drawable.foto_655402_908x560);
            array[1] = BitmapFactory.decodeResource(getResources(),R.drawable.foto_655404_514x318);
            array[2] = BitmapFactory.decodeResource(getResources(),R.drawable.foto_693184_908x560);
            array[3] = BitmapFactory.decodeResource(getResources(),R.drawable.fis_sedia1);
            array[4] = BitmapFactory.decodeResource(getResources(),R.drawable.fis_sedia2);
            array[5] = BitmapFactory.decodeResource(getResources(),R.drawable.fis_serr);
            array[6] = BitmapFactory.decodeResource(getResources(),R.drawable.labrt_porta);
            array[7] = BitmapFactory.decodeResource(getResources(),R.drawable.labrt_wifi);
            array[8] = BitmapFactory.decodeResource(getResources(),R.drawable.scale_fin);
            array[9] = BitmapFactory.decodeResource(getResources(),R.drawable.simaz_pav);
            array[10] = BitmapFactory.decodeResource(getResources(),R.drawable.spano_ufficio);
            SegnFactory.setBitmaps(array);
        }
        show = (TextInputLayout)findViewById(R.id.showP);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        accedi = (Button)findViewById(R.id.accedi);
        link = (TextView) findViewById(R.id.link);
        link.setPaintFlags(link.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (password.getText().length()>0)
                    show.setPasswordVisibilityToggleEnabled(true);
                else
                    show.setPasswordVisibilityToggleEnabled(false);
            }
        });
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recovery = new Intent(MainActivity.this, PassRecovery.class);
                startActivity(recovery);
            }
        });
        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()) {
                    Intent showResults = new Intent(MainActivity.this, Homepage.class);
                    Persona persona=new Persona();
                    persona.setUsername(""+username.getText().toString());
                    persona.setPassword(""+password.getText().toString());
                    p2 = PersonaFactory.login(persona.getUsername(),persona.getPassword());
                    if(p2 != null){
                        showResults.putExtra(PERSONA_EXTRA, p2);
                        startActivity(showResults);}
                    else{
                        text_msg(v);
                    }


                }
            }
        });
    }//CHIUDO ONCREATE
    public void text_msg(View view){
        if (checkInput()){
            final android.support.v7.app.AlertDialog dialog = new android.support.v7.app.AlertDialog.Builder(MainActivity.this)
                    .setTitle("Credenziali errate")
                    .setMessage("L'username e / o la password non sono corrette")
                    .setPositiveButton("RIPROVA", null)
                    .show();
            Button positiveButton = dialog.getButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.hide();
                }
            });
        }
    }

    private boolean checkInput(){
        int errors = 0;
        if(username.getText()==null || username.getText().length()==0) {
            username.setError("Inserire username");
            errors++;
        }
        else
            username.setError(null);
        if(password.getText()==null || password.getText().length()==0) {
            password.setError("Inserire la password");
            errors++;
        }
        else
            password.setError(null);
        return errors==0; // se non trova errori rende TRUE
    }
}
