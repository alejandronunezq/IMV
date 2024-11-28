package com.example.appimv;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAltas extends Fragment {

    private Spinner spinnerTipoEquipo;
    private LinearLayout layoutLaptop, layoutCelular;
    private EditText etMarca, etModelo, etNumeroSerie, etPrecio, etMacAddress;
    private EditText etProcesador, etRam, etAlmacenamiento; // Campos para Laptop
    private EditText etNumero, etCorreo, etImei; // Campos para Celular
    private Button btnGuardar;

    public FragmentAltas() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_altas, container, false);

        spinnerTipoEquipo = view.findViewById(R.id.spinner_tipo_equipo);
        layoutLaptop = view.findViewById(R.id.layout_laptop);
        layoutCelular = view.findViewById(R.id.layout_celular);
        etMarca = view.findViewById(R.id.et_marca);
        etModelo = view.findViewById(R.id.et_modelo);
        etNumeroSerie = view.findViewById(R.id.et_numero_serie);
        etPrecio = view.findViewById(R.id.et_precio);
        etMacAddress = view.findViewById(R.id.et_mac_address);
        btnGuardar = view.findViewById(R.id.btn_guardar);
        etProcesador = view.findViewById(R.id.et_procesador);
        etRam = view.findViewById(R.id.et_ram);
        etAlmacenamiento = view.findViewById(R.id.et_almacenamiento);
        etNumero = view.findViewById(R.id.et_numero);
        etCorreo = view.findViewById(R.id.et_correo);
        etImei = view.findViewById(R.id.et_imei);

        spinnerTipoEquipo.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    layoutLaptop.setVisibility(View.VISIBLE);
                    layoutCelular.setVisibility(View.GONE);
                } else if (position == 1) {
                    layoutLaptop.setVisibility(View.GONE);
                    layoutCelular.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });

        btnGuardar.setOnClickListener(v -> guardarEquipo());
        return view;
    }

    private void guardarEquipo() {
        String tipoEquipo = spinnerTipoEquipo.getSelectedItem().toString().toLowerCase(); // Convertir a minúsculas
        EquipoRequest equipoRequest;

        if (tipoEquipo.equals("laptop")) {
            Laptop laptop = new Laptop(
                    etMarca.getText().toString().trim(),
                    etModelo.getText().toString().trim(),
                    etNumeroSerie.getText().toString().trim(),
                    etMacAddress.getText().toString().trim(),
                    etProcesador.getText().toString().trim(),
                    etRam.getText().toString().trim(),
                    etAlmacenamiento.getText().toString().trim(),
                    Double.parseDouble(etPrecio.getText().toString().trim())
            );
            equipoRequest = new EquipoRequest("laptop", laptop);

        } else if (tipoEquipo.equals("celular")) {
            Celular celular = new Celular(
                    etMarca.getText().toString().trim(),
                    etModelo.getText().toString().trim(),
                    etNumeroSerie.getText().toString().trim(),
                    etMacAddress.getText().toString().trim(),
                    etNumero.getText().toString().trim(),
                    etCorreo.getText().toString().trim(),
                    etImei.getText().toString().trim(),
                    Double.parseDouble(etPrecio.getText().toString().trim())
            );
            equipoRequest = new EquipoRequest("celular", celular);
        } else {
            Toast.makeText(getContext(), "Tipo de equipo no válido", Toast.LENGTH_SHORT).show();
            return;
        }

        enviarEquipo(equipoRequest);
    }

    private void enviarEquipo(EquipoRequest equipoRequest) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<GenericResponse> call = apiService.insertarEquipo(equipoRequest);

        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al guardar el equipo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
