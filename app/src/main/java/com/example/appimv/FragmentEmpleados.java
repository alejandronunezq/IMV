package com.example.appimv;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentEmpleados extends Fragment {

    private EditText etNombre, etCorreo, etDepartamento, etPuesto, etEmpresa, etUbicacion, etCelularId, etLaptopId;
    private Button btnGuardar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empleados, container, false);

        // Inicializar vistas
        etNombre = view.findViewById(R.id.et_nombre);
        etCorreo = view.findViewById(R.id.et_correo);
        etDepartamento = view.findViewById(R.id.et_departamento);
        etPuesto = view.findViewById(R.id.et_puesto);
        etEmpresa = view.findViewById(R.id.et_empresa);
        etUbicacion = view.findViewById(R.id.et_ubicacion);
        etCelularId = view.findViewById(R.id.et_celular_id);
        etLaptopId = view.findViewById(R.id.et_laptop_id);
        btnGuardar = view.findViewById(R.id.btn_guardar);

        // Configurar el botón guardar
        btnGuardar.setOnClickListener(v -> guardarEmpleado());

        return view;
    }

    private void guardarEmpleado() {
        // Capturar los datos desde los EditText
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String departamento = etDepartamento.getText().toString().trim();
        String puesto = etPuesto.getText().toString().trim();
        String empresa = etEmpresa.getText().toString().trim();
        String ubicacion = etUbicacion.getText().toString().trim();
        Integer celularId = TextUtils.isEmpty(etCelularId.getText().toString()) ? null : Integer.parseInt(etCelularId.getText().toString());
        Integer laptopId = TextUtils.isEmpty(etLaptopId.getText().toString()) ? null : Integer.parseInt(etLaptopId.getText().toString());

        // Validar campos obligatorios
        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(correo)) {
            Toast.makeText(getContext(), "El nombre y el correo son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear objeto Empleado
        Empleado empleado = new Empleado(
                nombre,
                correo,
                departamento,
                puesto,
                empresa,
                ubicacion,
                celularId,
                laptopId
        );

        // Llamar a la API
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<GenericResponse> call = apiService.insertarEmpleado(empleado);

        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    limpiarFormulario();
                } else {
                    Toast.makeText(getContext(), "Error al guardar el empleado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limpiarFormulario() {
        etNombre.setText("");
        etCorreo.setText("");
        etDepartamento.setText("");
        etPuesto.setText("");
        etEmpresa.setText("");
        etUbicacion.setText("");
        etCelularId.setText("");
        etLaptopId.setText("");
    }
}
