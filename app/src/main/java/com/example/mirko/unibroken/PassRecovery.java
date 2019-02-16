package com.example.mirko.unibroken;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PassRecovery extends AppCompatActivity {
    Button indietro;
    EditText codf;
    Button send;
    Toast t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_recovery);
        indietro = (Button)findViewById(R.id.back);
        codf = (EditText)findViewById(R.id.codf);
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (codf.getText().toString().length() != 16){
                    error(v);
                }else{
                    done(v);
                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent indietro = new Intent(PassRecovery.this, MainActivity.class);
        startActivity(indietro);
    }

    public void back(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        onBackPressed();
    }

    public void error(View view){
        t = Toast.makeText(getApplicationContext(), "Il codice inserito non è corretto", Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER ,0 ,0);
        t.show();
    }
    public void done(View view){
        t = Toast.makeText(getApplicationContext(), "Una mail è stata spedita all'indirizzo associato a questo codice fiscale", Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER ,0 ,0);
        t.show();
    }

}
