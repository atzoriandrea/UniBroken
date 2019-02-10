package com.example.mirko.unibroken;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ViewSwitcher;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

import java.io.Serializable;
import java.util.ArrayList;

public class ImgSwitcherShow extends Activity implements OnItemSelectedListener,ViewFactory {

    private ImageSwitcher mswitcher;
    //All Images stored im integer array
    private Bitmap[] mImageIds;// = { R.drawable.foto_655402_908x560,R.drawable.foto_655404_514x318,R.drawable.foto_693184_908x560 };
    private ArrayList<Bitmap> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i = 0;
        super.onCreate(savedInstanceState);
        SegnFactory sf = SegnFactory.getInstance();
        //Setting no title on window, it should be written before setting cotent view
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_img_switcher_show);
        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Showsegn.BITMAP_EXTRA);
        Bundle bundle = getIntent().getExtras();
        mImageIds = new Bitmap[SegnFactory.getSegnalazioneById((int)obj).getImage().size()];
        array = SegnFactory.getSegnalazioneById((int)obj).getImage();
        for(Bitmap b : array){
            mImageIds[i] = b;
            i++;
        }
        i = 0;
        //Getting id for Image switcher and it is used to switch between images
        mswitcher = (ImageSwitcher) findViewById(R.id.image);
        mswitcher.setFactory(this);

        //Gallery for placing images
        Gallery g = (Gallery) findViewById(R.id.gallery);

        //Setting adapter over gallery
        g.setAdapter(new ImageAdapter(ImgSwitcherShow.this, mImageIds));

        //Implementing itemselected listener over gallery
        g.setOnItemSelectedListener(ImgSwitcherShow.this);

    }


    @Override
    public void onItemSelected(AdapterView arg0, View arg1, int position,
                               long arg3) {

        //On item selected from gallery the image switcher will get the position and set it to background
        mswitcher.setImageDrawable(new BitmapDrawable(mImageIds[position]));


    }

    @Override
    public void onNothingSelected(AdapterView arg0) {


    }

    @Override
    public View makeView() {
        //Adding dynamic image
        ImageView i = new ImageView(this);

        //Setting background color
        i.setBackgroundColor(0xFF000000);

        //Setting scale type
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);

        //Now setting layout parameters for image view
        i.setLayoutParams(new ImageSwitcher.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

        //Returning imageview
        return i;
    }
}
