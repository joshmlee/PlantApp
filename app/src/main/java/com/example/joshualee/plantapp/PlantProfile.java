package com.example.joshualee.plantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class PlantProfile extends AppCompatActivity {

    private Button plantWellness;
    private Button plantFacts;
    private ImageView edit;
    private ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_profile);

        Bundle extras = getIntent().getExtras();
        int position = extras.getInt("position");

        final Intent i = new Intent(PlantProfile.this, EditPlant.class);
        i.putExtra("position", position);


        SharedPreference sharedPreference = new SharedPreference();
        ArrayList<Plant> fav_plants = new ArrayList<Plant>();
        fav_plants = sharedPreference.getFavorites(this);
//        Log.v("plant name", fav_plants.get(position).getName());

        ImageView plantImage = (ImageView) findViewById(R.id.imageViewPlant);
        Bitmap plantBitmap = BitmapFactory.decodeFile(fav_plants.get(position).getPicture());
        plantImage.setImageBitmap(resize(plantBitmap, 250, 250));

        TextView name = (TextView) findViewById(R.id.PlantName);
        name.setText(fav_plants.get(position).getName());

        TextView species = (TextView) findViewById(R.id.PlantSpecies);
        species.setText(fav_plants.get(position).getSciName());



        plantWellness = (Button) findViewById(R.id.WellnessButton);
        plantWellness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PlantProfile.this, plantwellness.class);
                startActivity(intent1);
            }
        });

        plantFacts = (Button) findViewById(R.id.FactsButton);
        plantFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
//                Intent intent2 = new Intent(PlantProfile.this, plantfacts.class);
//                startActivity(intent2);
            }
        });

        home = (ImageView) findViewById(R.id.Homebutton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(PlantProfile.this, MainActivity.class);
                startActivity(intent3);
            }
        });

        edit = (ImageView) findViewById(R.id.Editbutton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);

            }
        });
    }

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
