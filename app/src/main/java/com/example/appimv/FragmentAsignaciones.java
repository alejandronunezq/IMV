package com.example.appimv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAsignaciones extends Fragment {

    private EditText etEmpleadoId, etLaptopId, etCelularId;
    private Button btnAsignar, btnDesasignar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asignaciones, container, false);

        etEmpleadoId = view.findViewById(R.id.etEmpleadoId);
        etLaptopId = view.findViewById(R.id.etLaptopId);
        etCelularId = view.findViewById(R.id.etCelularId);
        btnAsignar = view.findViewById(R.id.btnAsignar);
        btnDesasignar = view.findViewById(R.id.btnDesasignar);

        btnAsignar.setOnClickListener(v -> asignarEquipo());
        btnDesasignar.setOnClickListener(v -> desasignarEquipo());

        return view;
    }

    private void asignarEquipo() {
        int empleadoId = Integer.parseInt(etEmpleadoId.getText().toString());
        Integer laptopId = etLaptopId.getText().toString().isEmpty() ? null : Integer.parseInt(etLaptopId.getText().toString());
        Integer celularId = etCelularId.getText().toString().isEmpty() ? null : Integer.parseInt(etCelularId.getText().toString());

        ApiClient.asignarEquipo(empleadoId, laptopId, celularId, getContext(), response -> {
            try {
                if (response.getBoolean("success")) {
                    Toast.makeText(getContext(), "Equipo asignado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(getContext(), "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(getContext(), "Error al conectar con el servidor", Toast.LENGTH_SHORT).show());
    }

    private void desasignarEquipo() {
        int empleadoId = Integer.parseInt(etEmpleadoId.getText().toString());

        ApiClient.asignarEquipo(empleadoId, null, null, getContext(), response -> {
            try {
                if (response.getBoolean("success")) {
                    Toast.makeText(getContext(), "Equipo desasignado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(getContext(), "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(getContext(), "Error al conectar con el servidor", Toast.LENGTH_SHORT).show());
    }
}
