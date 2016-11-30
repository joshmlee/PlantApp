package com.example.joshualee.plantapp;

/**
 * Created by joshualee on 11/27/16.
 * I took this code from this site http://androidopentutorials.com/android-how-to-store-list-of-values-in-sharedpreferences
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
//import com.androidopentutorials.spfavorites.beans.Product;
import com.google.gson.Gson;

public class SharedPreference {
    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<Plant> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, Plant plant) {
        List<Plant> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Plant>();
        Log.v("Position",plant.getName());
        favorites.add(plant);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Plant plant) {
        ArrayList<Plant> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(plant);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<Plant> getFavorites(Context context) {
        SharedPreferences settings;
        List<Plant> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Plant[] favoriteItems = gson.fromJson(jsonFavorites,
                    Plant[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Plant>(favorites);
        } else
            return null;

        return (ArrayList<Plant>) favorites;
    }
}
