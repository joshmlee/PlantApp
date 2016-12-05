package com.example.joshualee.plantapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

public class EditPlant extends AppCompatActivity {

    String addPlant_Nickname;
    String addPlant_Species;
    String addPlant_addImage;

    Plant new_plant = new Plant ();


    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_add);


    }

    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageButton imgView = (ImageButton) findViewById(R.id.plantImage_input);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));
                //new_plant.setPicture("WhateverThisWouldBe");

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }
    public void SubmitAddText(View view){

        //SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
        //SharedPreferences.Editor prefsEditor = mPrefs.edit();
        EditText nickname = (EditText) findViewById(R.id.plantName_input);
        EditText species = (EditText) findViewById(R.id.plantSpecies_input);
        addPlant_Nickname = (String) nickname.getText().toString();
        addPlant_Species = (String) species.getText().toString();

        new_plant.setName(addPlant_Nickname);
        new_plant.setSciName(addPlant_Species);

        Gson gson = new Gson();
        String json = gson.toJson(new_plant);

        //prefsEditior.putString("new_plant", json);
        //prefsEditior.commit();


        //new_plant.setPicture();

//        Toast.makeText(this, new_plant.getName().toString(),
//                Toast.LENGTH_LONG).show();

    }

}

//http://programmerguru.com/android-tutorial/how-to-pick-image-from-gallery/#code