package com.example.joshualee.plantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlantProfile extends AppCompatActivity {

    private Button plantWellness;
    private Button plantFacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_profile);

        plantWellness = (Button) findViewById(R.id.WellnessButton);
        plantFacts = (Button) findViewById(R.id.FactsButton);

        plantWellness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantProfile.this, plantwellness.class);
                startActivity(intent);
            }
        });

        plantFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantProfile.this, plantfacts.class);
                startActivity(intent);
            }
        });
    }
}
