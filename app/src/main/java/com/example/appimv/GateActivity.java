package com.example.appimv;

import static android.content.Intent.getIntent;



import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class GateActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }

        setContentView(R.layout.activity_gate);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        String nombre = getIntent().getStringExtra("NOMBRE");
        String departamento = getIntent().getStringExtra("DEPARTAMENTO");

        if (savedInstanceState == null) {
            Fragment fragmentMiCuenta = new FragmentMiCuenta();
            Bundle bundle = new Bundle();
            bundle.putString("NOMBRE", nombre);
            fragmentMiCuenta.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragmentMiCuenta)
                    .commit();
        }

        NavigationView navigationView = findViewById(R.id.navigation_view);
        configureNavigationMenu(navigationView, departamento);

        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            int id = item.getItemId();

            if ("TI".equalsIgnoreCase(departamento)) {
                if (id == R.id.nav_account) {
                    fragment = new FragmentMiCuenta();
                } else if (id == R.id.nav_equipos) {
                    fragment = new FragmentEquipos();
                } else if (id == R.id.nav_personal) {
                    fragment = new FragmentEmpleados();
                } else if (id == R.id.nav_altas) {
                    fragment = new FragmentAltas();
                } else if (id == R.id.nav_asignaciones) {
                    fragment = new FragmentAsignaciones();
                } else if (id == R.id.nav_exit) { // Botón Salir
                    finishAffinity(); // Finaliza todas las actividades y cierra la aplicación
                    return true; // Salimos del listener
                }
            } else {
                if (id == R.id.nav_account) {
                    fragment = new FragmentMiCuenta();
                } else if (id == R.id.nav_personal) {
                    fragment = new FragmentEmpleados();
                } else if (id == R.id.nav_exit) { // Botón Salir
                    new AlertDialog.Builder(this)
                            .setTitle("Salir")
                            .setMessage("¿Estás seguro de que deseas salir?")
                            .setPositiveButton("Sí", (dialog, which) -> finishAffinity())
                            .setNegativeButton("No", null)
                            .show();
                    return true;
                }
                else {
                    Toast.makeText(this, "Acceso restringido", Toast.LENGTH_SHORT).show();
                }
            }

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });


    }

    private void configureNavigationMenu(NavigationView navigationView, String departamento) {
        Menu menu = navigationView.getMenu();
        if (!"TI".equalsIgnoreCase(departamento)) {
            menu.findItem(R.id.nav_equipos).setVisible(false);
            menu.findItem(R.id.nav_altas).setVisible(false);
            menu.findItem(R.id.nav_asignaciones).setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
