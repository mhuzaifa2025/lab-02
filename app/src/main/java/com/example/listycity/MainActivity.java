package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    // Tracking variables
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize Views
        cityList = findViewById(R.id.city_list);
        Button btnAddCity = findViewById(R.id.button_add);
        Button btnDeleteCity = findViewById(R.id.button_delete);
        Button btnConfirm = findViewById(R.id.button_confirm);
        EditText cityInput = findViewById(R.id.edit_text_name);
        LinearLayout footerInput = findViewById(R.id.footer_input);


        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>(Arrays.asList(cities));


        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        btnAddCity.setOnClickListener(v -> {

            footerInput.setVisibility(View.VISIBLE);
        });


        btnConfirm.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged(); // Updates UI
                cityInput.setText(""); // Clear field
                footerInput.setVisibility(View.GONE); // Hide after adding
            }
        });


        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
        });


        btnDeleteCity.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged(); // Updates UI
                selectedPosition = -1; // Reset selection
            }
        });
    }
}
