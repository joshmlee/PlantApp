package com.example.joshualee.plantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlantProfile extends AppCompatActivity {

    private Button plantWellness;
    private Button plantFacts;
    private Button edit;
    private Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_profile);

        plantWellness = (Button) findViewById(R.id.WellnessButton);
        plantWellness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantProfile.this, plantwellness.class);
                startActivity(intent);
            }
        });

        plantFacts = (Button) findViewById(R.id.FactsButton);
        plantFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantProfile.this, plantfacts.class);
                startActivity(intent);
            }
        });

        home = (Button) findViewById(R.id.Homebutton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        edit = (Button) findViewById(R.id.EditButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
