package com.example.joshualee.plantapp;

/**
 * Created by joshualee on 11/21/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

//        ImageView imageView;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
            holder = new ViewHolder();
            // if it's not recycled, initialize some attributes
            holder.mainImage = (ImageView) convertView.findViewById(R.id.plantImage);  // new ImageView(mContext);
            holder.nameText = (TextView) convertView.findViewById(R.id.name);
//            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SharedPreference sharedPreference = new SharedPreference();
        ArrayList<Plant> fav_plants = new ArrayList<Plant>();
        fav_plants = sharedPreference.getFavorites(mContext);
        holder.mainImage.setImageResource(mThumbIds[position]);
        holder.nameText.setText("Hola");
        return convertView;
//        return imageView;
    }

    private static class ViewHolder{
        ImageView mainImage;
        TextView nameText;
    }
    // references to our images
    private Integer[] mThumbIds = {

            R.drawable.bunnyearcactus, R.drawable.bunnyearcactus,
            R.drawable.bunnyearcactus, R.drawable.bunnyearcactus,
            R.drawable.bunnyearcactus, R.drawable.bunnyearcactus,
            R.drawable.bunnyearcactus, R.drawable.bunnyearcactus
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_1,
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7
    };

}
