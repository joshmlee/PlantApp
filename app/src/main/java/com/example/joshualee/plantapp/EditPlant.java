package com.example.joshualee.plantapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

public class EditPlant extends AppCompatActivity {

    Context myContext;
    int   MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;
    String editPlant_Nickname;
    String editPlant_Species;
    String editPlant_addImage;
    SharedPreference sharedPreference = new SharedPreference();
    int position;

    ArrayList<Plant> fav_plants = new ArrayList<Plant>();






    private static int RESULT_LOAD_IMG = 1;
    //String imgDecodableString;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_add);
        Bundle extras = getIntent().getExtras();
        position = extras.getInt("position");


        fav_plants = sharedPreference.getFavorites(this);

        ImageButton imgView = (ImageButton) findViewById(R.id.plantImage_input);
        Bitmap plantBitmap = BitmapFactory.decodeFile(fav_plants.get(position).getPicture());
        imgView.setImageBitmap(resize(plantBitmap, 250, 250));

        EditText nickname = (EditText) findViewById(R.id.plantName_input);
        EditText species = (EditText) findViewById(R.id.plantSpecies_input);

        nickname.setText(fav_plants.get(position).getName());
        species.setText(fav_plants.get(position).getSciName());


        ImageView homeButton = (ImageView) findViewById(R.id.imageView5);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent3 = new Intent(EditPlant.this, MainActivity.class);
                startActivity(intent3);
            }
        });



        if(Build.VERSION.SDK_INT== Build.VERSION_CODES.M) {
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
//                Toast.makeText(this, "I got to print statement 1",
//                        Toast.LENGTH_LONG).show();
                Uri selectedImage = data.getData();
                // imageFile = new File(getRealPathFromURI(selectedImage));
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

//                Toast.makeText(this, "I got to print statement 2",
//                        Toast.LENGTH_LONG).show();
//                Toast.makeText(this, filePathColumn[0],
//                        Toast.LENGTH_LONG).show();
//


                // Get the cursor   ****THIS IS WHERE THE PROGRAM THROWS AN ERROR****
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                //                  *****NEVER PASSES THIS POINT********

                // Move to first row
//                Toast.makeText(this, "I got to print statement 2.5",
//                        Toast.LENGTH_LONG).show();
                cursor.moveToFirst();
//                Toast.makeText(this, "I got to print statement 3",
//                        Toast.LENGTH_LONG).show();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

//                Toast.makeText(this, "I got to print statement 4",
                //         Toast.LENGTH_LONG).show();

                ImageButton imgView = (ImageButton) findViewById(R.id.plantImage_input);
                // Set the Image in ImageView after decoding the String
                Bitmap bmp = null;
                try {
                    bmp = resize(getBitmapFromUri(selectedImage), 250, 250);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //imgView.setImageBitmap(BitmapFactory
                //        .decodeFile(imgDecodableString));

                imgView.setImageBitmap(bmp);

               // editPlant_addImage = encodeToBase64(bmp, Bitmap.CompressFormat.JPEG, 20);

                fav_plants.get(position).setPicture(picturePath);
//                Toast.makeText(this, picturePath,
//                     Toast.LENGTH_LONG).show();
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

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
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

        myContext = view.getContext();

        //SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
        //SharedPreferences.Editor prefsEditor = mPrefs.edit();
        EditText nickname = (EditText) findViewById(R.id.plantName_input);
        EditText species = (EditText) findViewById(R.id.plantSpecies_input);
        editPlant_Nickname = (String) nickname.getText().toString();
        editPlant_Species = (String) species.getText().toString();

        fav_plants.get(position).setName(editPlant_Nickname);
        fav_plants.get(position).setSciName(editPlant_Species);

        SharedPreferences clearSHP = myContext.getSharedPreferences("PRODUCT_APP", 0);

       clearSHP.edit().clear().commit();
       sharedPreference.saveFavorites(myContext, fav_plants);

        //sharedPreference.addFavorite(this, new_plant);
//        Gson gson = new Gson();
        // String json = gson.toJson(new_plant);

        //prefsEditior.putString("new_plant", json);
        //prefsEditior.commit();


        //new_plant.setPicture();

//        Toast.makeText(this, new_plant.getName().toString(),
//              Toast.LENGTH_LONG).show();
        Intent intent1 = new Intent(EditPlant.this, MainActivity.class);
        startActivity(intent1);
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

//http://programmerguru.com/android-tutorial/how-to-pick-image-from-gallery/#code