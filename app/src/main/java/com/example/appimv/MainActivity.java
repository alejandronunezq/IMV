package com.example.appimv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button laptopsButton = findViewById(R.id.button_laptops);
        Button celularesButton = findViewById(R.id.button_celulares);
        Button radiosButton = findViewById(R.id.button_radios);
        Button impresorasButton = findViewById(R.id.button_impresoras);
        Button tabletsButton = findViewById(R.id.button_tablets);

        laptopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LaptopsActivity.class);
                startActivity(intent);
            }
        });

        celularesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CelularesActivity.class);
                startActivity(intent);
            }
        });

        radiosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RadiosActivity.class);
                startActivity(intent);
            }
        });

        impresorasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImpresorasActivity.class);
                startActivity(intent);
            }
        });

        tabletsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabletsActivity.class);
                startActivity(intent);
            }
        });
    }
}