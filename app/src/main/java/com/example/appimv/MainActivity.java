package com.example.appimv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button laptopsButton = findViewById(R.id.button_laptops);
        Button celularesButton = findViewById(R.id.button_celulares);
        Button radiosButton = findViewById(R.id.button_radios);
        Button impresorasButton = findViewById(R.id.button_impresoras);
        Button tabletsButton = findViewById(R.id.button_tablets);


        final Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale);


        laptopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleUp);
                Intent intent = new Intent(MainActivity.this, LaptopsActivity.class);
                intent.putExtra("deviceType", "Laptops");
                startActivity(intent);
            }
        });

        celularesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleUp);
                Intent intent = new Intent(MainActivity.this, CelularesActivity.class);
                intent.putExtra("deviceType", "Celulares");
                startActivity(intent);
            }
        });

        radiosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleUp);
                Intent intent = new Intent(MainActivity.this, RadiosActivity.class);
                intent.putExtra("deviceType", "Radios");
                startActivity(intent);
            }
        });

        impresorasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleUp);
                Intent intent = new Intent(MainActivity.this, ImpresorasActivity.class);
                intent.putExtra("deviceType", "Impresoras");
                startActivity(intent);
            }
        });

        tabletsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleUp);
                Intent intent = new Intent(MainActivity.this, TabletsActivity.class);
                intent.putExtra("deviceType", "Tablets");
                startActivity(intent);
            }
        });
    }
}
