package com.taras_overmind.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.taras_overmind.myapplication.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Get references to the buttons
        Button buttonContacts = findViewById(R.id.contactButton);
        Button buttonCar = findViewById(R.id.carButton);
        Button buttonPersonalPage = findViewById(R.id.buttonPersonalPage);
        Button buttonGeoService = findViewById(R.id.geoServiceButton);

        // Set click listeners for the buttons
        buttonContacts.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, MainActivity2.class);
            startActivity(intent);
        });
        buttonCar.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
        });
        buttonGeoService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        buttonPersonalPage.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, PersonalPageActivity.class);
            startActivity(intent);
        });
    }
}