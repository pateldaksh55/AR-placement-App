package com.example.arplacement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner drillSpinner;
    Button btnDetails, btnStartAR;
    String selectedDrill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drillSpinner = findViewById(R.id.drillSpinner);
        btnDetails = findViewById(R.id.btnDetails);
        btnStartAR = findViewById(R.id.btnStartAR);

        String[] drills = {"Drill 1", "Drill 2", "Drill 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, drills);
        drillSpinner.setAdapter(adapter);

        btnDetails.setOnClickListener(v -> {
            selectedDrill = drillSpinner.getSelectedItem().toString();
            Intent intent = new Intent(MainActivity.this, DrillDetailsActivity.class);
            intent.putExtra("Drill_Name", selectedDrill);
            startActivity(intent);
        });

        btnStartAR.setOnClickListener(v -> {
            selectedDrill = drillSpinner.getSelectedItem().toString();
            Intent intent = new Intent(MainActivity.this, ArActivity.class);
            intent.putExtra("Drill_Name", selectedDrill);
            startActivity(intent);
        });
    }
}
