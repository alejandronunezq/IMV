package com.example.appimv;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar UI en modo de pantalla completa según la versión de Android
        configureFullScreenMode();

        // Inicializar vistas
        initializeUI();

        // Configurar listener para el botón de login
        loginButton.setOnClickListener(v -> performLogin());
    }

    private void configureFullScreenMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }
    }

    private void initializeUI() {
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
    }

    private void performLogin() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear el objeto JSON para la solicitud
        LoginRequest loginRequest = new LoginRequest(username, password);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<LoginResponse> call = apiService.loginUser(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    handleLoginResponse(response.body());
                } else {
                    Toast.makeText(MainActivity.this, "Error en la respuesta del servidor.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleLoginResponse(LoginResponse loginResponse) {
        if (loginResponse.isSuccess()) {
            String nombre = loginResponse.getNombre();
            String departamento = loginResponse.getDepartamento();

            Intent intent = new Intent(MainActivity.this, GateActivity.class);
            intent.putExtra("NOMBRE", nombre);
            intent.putExtra("DEPARTAMENTO", departamento); // Pasar el departamento al siguiente Activity
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
