package com.example.joshualee.plantapp;

/**
 * Created by joshualee on 11/21/16.
 */

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    SharedPreference sharedPreference = new SharedPreference();
    ArrayList<Plant> fav_plants = new ArrayList<Plant>();

    public ImageAdapter(Context c) {
        mContext = c;
        //        Log.v("size object", toString(fav_plants.size()));
        fav_plants = sharedPreference.getFavorites(mContext);
    }




    @Override
    public int getCount() {
        return fav_plants.size();
    }

    @Override
    public Object getItem(int position) {
        return fav_plants.get(position);
    }

    @Override
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

//        File imgFile = new File(fav_plants.get(position).getPicture());
//        Log.v("hi", imgFile.toString());
        Bitmap plantBitmap = BitmapFactory.decodeFile(fav_plants.get(position).getPicture());
//       try
//       {
//           holder.mainImage.setImageBitmap(resize(plantBitmap, 250, 250));
//       }
//       catch(Exception e)
//       {
//           holder.mainImage.setImageBitmap(plantBitmap);
//       }
        holder.mainImage.setImageBitmap(resize(plantBitmap, 250, 250));

        holder.nameText.setText(fav_plants.get(position).getName());

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


    private static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > 1) {
                finalWidth = (int) ((float)maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float)maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }

}
