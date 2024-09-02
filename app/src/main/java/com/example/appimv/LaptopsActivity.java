package com.example.appimv;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LaptopsActivity extends AppCompatActivity {

    private EditText etNumeroSerie;
    private TextView tvDetallesTitulo, tvDetalles;
    private Button btnBuscar;
    private RequestQueue requestQueue;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptops);

        etNumeroSerie = findViewById(R.id.etNumeroSerie);
        tvDetallesTitulo = findViewById(R.id.tvDetallesTitulo);
        tvDetalles = findViewById(R.id.tvDetalles);
        btnBuscar = findViewById(R.id.btnBuscar);

        requestQueue = Volley.newRequestQueue(this);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarLaptop();
            }
        });
    }

    private void buscarLaptop() {
        String numeroSerie = etNumeroSerie.getText().toString().trim();

        if (numeroSerie.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa el número de serie", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://192.168.0.100/get_laptop.php?n_s=" + numeroSerie;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.has("error")) {
                                Toast.makeText(LaptopsActivity.this, response.getString("error"), Toast.LENGTH_SHORT).show();
                                tvDetallesTitulo.setVisibility(View.GONE);
                                tvDetalles.setVisibility(View.GONE);
                            } else {
                                String detalles = "Marca: " + response.getString("marca") + "\n" +
                                        "Modelo: " + response.getString("modelo") + "\n" +
                                        "MAC: " + response.getString("mac") + "\n" +
                                        "RAM: " + response.getString("ram") + "\n" +
                                        "Almacenamiento: " + response.getString("almacenamiento") + "\n" +
                                        "Anydesk: " + response.getString("anydesk") + "\n" +
                                        "Usuario Asignado: " + response.getString("usuarioAsignado") + "\n" +
                                        "Correo: " + response.getString("correo") + "\n" +
                                        "Puesto: " + response.getString("puesto") + "\n" +
                                        "Departamento: " + response.getString("departamento");

                                tvDetallesTitulo.setVisibility(View.VISIBLE);
                                tvDetalles.setVisibility(View.VISIBLE);
                                tvDetalles.setText(detalles);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LaptopsActivity.this, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Muestra un mensaje Toast para indicar el error
                        Toast.makeText(LaptopsActivity.this, "Error en la conexión. Verifique su red.", Toast.LENGTH_SHORT).show();
                        // Registra el error en el Logcat para un diagnóstico más detallado
                        Log.e("LaptopsActivity", "Error en la respuesta de la API", error);

                        // Puedes incluir un manejo de excepciones más específico aquí
                        if (error.networkResponse != null) {
                            Log.e("LaptopsActivity", "Código de error HTTP: " + error.networkResponse.statusCode);
                        } else if (error instanceof NoConnectionError) {
                            Log.e("LaptopsActivity", "No se pudo conectar al servidor.");
                        } else if (error instanceof TimeoutError) {
                            Log.e("LaptopsActivity", "Tiempo de espera agotado.");
                        }
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

}