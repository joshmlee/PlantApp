package com.example.joshualee.plantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class plantwellness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantwellness);

        ImageView homeButton = (ImageView) findViewById(R.id.imageView3);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(plantwellness.this, MainActivity.class);
                startActivity(intent3);
            }
        });
    }
}
