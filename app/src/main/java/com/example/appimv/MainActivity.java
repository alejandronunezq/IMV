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

    /**
     * Configura el modo de pantalla completa dependiendo de la versión de Android.
     */
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

    /**
     * Inicializa las vistas de la interfaz de usuario.
     */
    private void initializeUI() {
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
    }

    /**
     * Ejecuta la operación de inicio de sesión al presionar el botón.
     */
    private void performLogin() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validar que los campos no estén vacíos
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Llamar a la API para el inicio de sesión
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<LoginResponse> call = apiService.loginUser(username, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    handleLoginResponse(response.body());
                } else {
                    Toast.makeText(MainActivity.this, "Error en la respuesta del servidor. Código: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión: Verifique su conexión a Internet.", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    /**
     * Maneja la respuesta de la API de inicio de sesión.
     *
     * @param loginResponse La respuesta de la API.
     */
    private void handleLoginResponse(LoginResponse loginResponse) {
        if (loginResponse.isSuccess()) {
            // Redirigir a GateActivity con el nombre del usuario
            String nombre = loginResponse.getNombre();
            Intent intent = new Intent(MainActivity.this, GateActivity.class);
            intent.putExtra("NOMBRE", nombre);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
