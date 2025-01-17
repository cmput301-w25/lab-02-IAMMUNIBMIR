package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        String[] cities = {"Edmonton","Paris","London","Ottawa"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        Button addButton = findViewById(R.id.add_city_button);
        EditText editCity = findViewById(R.id.edit_city);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = editCity.getText().toString().trim();

                if (!dataList.contains(cityName)) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, cityName + " added to the list", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "City already exists in the list", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button delButton = findViewById(R.id.delete_city_button);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = editCity.getText().toString().trim();

                if (dataList.contains(cityName)) {
                    dataList.remove(cityName);
                    cityAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, cityName + " deleted from the list", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "City already deleted from the list", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}