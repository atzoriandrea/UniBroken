package com.example.mirko.unibroken;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Bitmap[] mImageIds;

    public ImageAdapter(Context c, Bitmap[] mImageIds) {
        this.mImageIds = mImageIds;
        mContext = c;
    }

    @Override
    public int getCount() {

        return mImageIds.length;
    }

    @Override
    public Object getItem(int pos) {

        return mImageIds[pos];
    }

    @Override
    public long getItemId(int pos) {

        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Adding dynamic image simillarly we had added to Image Switcher
        ImageView i = new ImageView(mContext);
        i.setImageBitmap(mImageIds[position]);
        i.setAdjustViewBounds(true);
        i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        // Setting background resource
        i.setBackground(new BitmapDrawable(mImageIds[0]));

        return i;
    }

}