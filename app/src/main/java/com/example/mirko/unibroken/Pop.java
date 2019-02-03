package com.example.mirko.unibroken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class Pop extends Activity {
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ok = (Button) findViewById(R.id.ok);
        setContentView(R.layout.pop_login_fallito);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent undo = new Intent(Pop.this, MainActivity.class);
                startActivity(undo);    //bottone per tornare alla prima activity
            }
        });


    }
}
