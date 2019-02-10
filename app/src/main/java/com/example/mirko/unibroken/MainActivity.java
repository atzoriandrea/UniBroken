package com.example.mirko.unibroken;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.app.AlertDialog;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
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

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button accedi;
    TextView errore;
    TextView link;
    Persona p2;
    boolean p;
    Toast t;
    Bitmap[] array = new Bitmap[3];
    public static final String PERSONA_EXTRA="com.example.mirko.unibroken.Persona";
    //AlertDialog.Builder builder=new AlertDialog.Builder(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recupero gli ID
        array[0] = BitmapFactory.decodeResource(getResources(),R.drawable.foto_655402_908x560);
        array[1] = BitmapFactory.decodeResource(getResources(),R.drawable.foto_655404_514x318);
        array[2] = BitmapFactory.decodeResource(getResources(),R.drawable.foto_693184_908x560);
        SegnFactory.setBitmaps(array);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        accedi = (Button)findViewById(R.id.accedi);
        errore = (TextView) findViewById(R.id.errore);
        errore.setVisibility(View.GONE);
        link = (TextView) findViewById(R.id.link);


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
                        //passo i dati del mio utente ed attivo la seconda activity
                        startActivity(showResults);}
                    else{
                        text_msg(v);
                        //builder.show();
                    }


                }
            }
        });
    }//CHIUDO ONCREATE
    public void text_msg(View view){
        t = Toast.makeText(getApplicationContext(), "Username e / o password non corretti", Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER ,0 ,0);
        t.show();
    }

    private boolean checkInput()
    {
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

        switch (errors){
            case 0:
                errore.setVisibility(View.GONE);
                errore.setText("");

                break;
            case 1:
                errore.setVisibility(View.VISIBLE);
                errore.setText("Si è verificato un errore");
                break;
            default:
                errore.setVisibility(View.VISIBLE);
                errore.setText("Si sono verificati " + errors+" errori" );
                break;
        }


        return errors==0; // se non trova errori rende TRUE
    }
}
