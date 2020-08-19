package com.amanaggarwal1.instafire.Utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.amanaggarwal1.instafire.R;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class GridImageAdapter extends ArrayAdapter {

    private static final String TAG = "GridImageAdapter";
    private Context context;
    private int layoutResource;
    private LayoutInflater layoutInflater;
    private ArrayList<String> imageURLs;

    public GridImageAdapter(Context context, int layoutResource, ArrayList<String> imageURLs){
        super(context, layoutResource, imageURLs);
        this.context = context;
        this.layoutResource = layoutResource;
        this.imageURLs = imageURLs;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
        }

        Log.d(TAG, "getView: image url = " + imageURLs.get(position));
        Glide.with(context)
                .load(imageURLs.get(position))
            .into((ImageView) convertView);

        return convertView;
    }
}
