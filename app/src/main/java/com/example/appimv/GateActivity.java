package com.example.appimv;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class GateActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);

        // Configurar Toggle para el botón de menú
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configurar NavigationView
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_account) {
                Toast.makeText(GateActivity.this, "Mi cuenta seleccionada", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_absences) {
                Toast.makeText(GateActivity.this, "Faltas seleccionadas", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_grades) {
                Toast.makeText(GateActivity.this, "Calificaciones seleccionadas", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_schedule) {
                Toast.makeText(GateActivity.this, "Horario seleccionado", Toast.LENGTH_SHORT).show();
            }
            drawerLayout.closeDrawer(GravityCompat.START); // Cerrar el Drawer después de seleccionar
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START); // Cerrar el Drawer si está abierto
        } else {
            super.onBackPressed();
        }
    }
}
