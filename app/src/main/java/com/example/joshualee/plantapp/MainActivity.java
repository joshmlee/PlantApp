package com.example.joshualee.plantapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.ImageView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        SharedPreferences sp = this.getSharedPreferences("PRODUCT_APP", 0);;
//        sp.edit().clear().commit();

        int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;

        SharedPreference sharedPreference = new SharedPreference();
        ArrayList<Plant> fav_plants = new ArrayList<Plant>();
        fav_plants = sharedPreference.getFavorites(this);

        if(fav_plants == null){
            if(Build.VERSION.SDK_INT > 23) {
//call the request permission here

                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (shouldShowRequestPermissionRationale(
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        // Explain to the user why we need to read the contacts
                    }

                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                    // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                    // app-defined int constant


                }
            }
            setContentView(R.layout.grid_place_holder);
            ImageView imgFavorite = (ImageView) findViewById(R.id.firstAdd);
            imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, AddPlant.class));
                    //Toast.makeText(getApplicationContext(), "adding item2", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            setContentView(R.layout.activity_main);

            GridView gridview = (GridView) findViewById(R.id.gridview);
            gridview.setAdapter(new ImageAdapter(this));
//        gridview.setAdapter(new PlaceAdapter(this));

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Intent i = new Intent(MainActivity.this, PlantProfile.class);
                    i.putExtra("position", position);
                    startActivity(i);

//                    Toast.makeText(MainActivity.this, "" + position,
//                            Toast.LENGTH_SHORT).show();
                }
            });

            ImageView imgFavorite = (ImageView) findViewById(R.id.plusImage);
            imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, AddPlant.class));
                    //Toast.makeText(getApplicationContext(), "adding item2", Toast.LENGTH_SHORT).show();
                }
            });
        }



    }
}
