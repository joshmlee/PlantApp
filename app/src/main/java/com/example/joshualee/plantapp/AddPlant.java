package com.example.joshualee.plantapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

public class AddPlant extends AppCompatActivity {

    String addPlant_Nickname;
    String addPlant_Species;
    String addPlant_addImage;

    Plant new_plant = new Plant ();


    private static int RESULT_LOAD_IMG = 1;
    //String imgDecodableString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
                Toast.makeText(this, "I got to print statement 1",
                        Toast.LENGTH_LONG).show();
                Uri selectedImage = data.getData();
               // imageFile = new File(getRealPathFromURI(selectedImage));
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Toast.makeText(this, "I got to print statement 2",
                        Toast.LENGTH_LONG).show();
                Toast.makeText(this, filePathColumn[0],
                        Toast.LENGTH_LONG).show();



                // Get the cursor   ****THIS IS WHERE THE PROGRAM THROWS AN ERROR****
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                //                  *****NEVER PASSES THIS POINT********

                // Move to first row
                Toast.makeText(this, "I got to print statement 2.5",
                        Toast.LENGTH_LONG).show();
                cursor.moveToFirst();
                Toast.makeText(this, "I got to print statement 3",
                        Toast.LENGTH_LONG).show();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                Toast.makeText(this, "I got to print statement 4",
                        Toast.LENGTH_LONG).show();
                ImageButton imgView = (ImageButton) findViewById(R.id.plantImage_input);
                // Set the Image in ImageView after decoding the String
                Bitmap bmp = null;
                try {
                    bmp = getBitmapFromUri(selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //imgView.setImageBitmap(BitmapFactory
                //        .decodeFile(imgDecodableString));

                imgView.setImageBitmap(bmp);
                Toast.makeText(this, "I got to print statement 5",
                        Toast.LENGTH_LONG).show();
               // new_plant.setPicture("WhateverThisWouldBe");

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

    }
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
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