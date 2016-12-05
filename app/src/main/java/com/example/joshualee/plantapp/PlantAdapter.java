package com.example.joshualee.plantapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PlantAdapter extends BaseAdapter {

/*
    private Context mContext;
/*
    preferences = myContext.getSharedPreferences("PRODUCT_APP", 0);
    ArrayList<Plant> sp_plants = new ArrayList<Plant>();
    sp_plants = sharedPreference.getFavorites(myContext);
    preferences.edit().clear().commit();

   ImageView fav_view = (ImageView) convertView.findViewById(R.id.);

   fav_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    preferences = myContext.getSharedPreferences("PRODUCT_APP", 0);
                    ArrayList<Place> fav_places = new ArrayList<Place>();
                    fav_places = sharedPreference.getFavorites(myContext);
                    preferences.edit().clear().commit();
                    boolean infavorites = false;
                    if(fav_places!=null){
                        for(int i=0; i<fav_places.size(); i++){
                            if(fav_places.get(i).getId().equals(place.getId())) {
                                fav_places.remove(i);
                                infavorites = true;
                                break;
                            }
                        }
                    }
                    if(fav_places != null){
                        if(!infavorites){
                            fav_places.add(place);
                        }
                        for(int n=0; n<fav_places.size(); n++){
                            sharedPreference.addFavorite(myContext, fav_places.get(n));
                        }
                    }
                    else{
                        sharedPreference.addFavorite(myContext, place);
                    }

                }
            });

    */

    public PlantAdapter(Context c) {
      //  mContext = c;
    }

    public int getCount() {
      //  return mThumbIds.length;
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


}
