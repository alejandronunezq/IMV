package com.example.appimv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentActualizarEquipo extends Fragment {

    private EditText etMarca, etModelo, etNumeroSerie, etMacAddress, etPrecio;
    private TextView tvIdEquipo;
    private Button btnGuardar;
    private Equipo equipo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actualizar_equipo, container, false);

        // Inicializar vistas
        etMarca = view.findViewById(R.id.etMarca);
        etModelo = view.findViewById(R.id.etModelo);
        etNumeroSerie = view.findViewById(R.id.etNumeroSerie);
        etMacAddress = view.findViewById(R.id.etMacAddress);
        etPrecio = view.findViewById(R.id.etPrecio);
        tvIdEquipo = view.findViewById(R.id.tvIdEquipo);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        // Obtener los datos del equipo del bundle
        if (getArguments() != null) {
            equipo = (Equipo) getArguments().getSerializable("equipo");
            llenarDatosEquipo();
        }

        // Configurar el botón "Guardar"
        btnGuardar.setOnClickListener(v -> actualizarEquipo());

        return view;
    }

    private void llenarDatosEquipo() {
        if (equipo != null) {
            etMarca.setText(equipo.getMarca());
            etModelo.setText(equipo.getModelo());
            etNumeroSerie.setText(equipo.getNumeroSerie());
            etMacAddress.setText(equipo.getMacAddress());
            etPrecio.setText(equipo.getPrecio());
            tvIdEquipo.setText("ID del Equipo: " + equipo.getId());
        }
    }

    private void actualizarEquipo() {
        equipo.setMarca(etMarca.getText().toString());
        equipo.setModelo(etModelo.getText().toString());
        equipo.setNumeroSerie(etNumeroSerie.getText().toString());
        equipo.setMacAddress(etMacAddress.getText().toString());
        equipo.setPrecio(etPrecio.getText().toString());

        ApiClient.actualizarEquipo(equipo, getContext(), response -> {
            Toast.makeText(getContext(), "Equipo actualizado correctamente", Toast.LENGTH_SHORT).show();
            requireActivity().onBackPressed();
        }, error -> Toast.makeText(getContext(), "Error al actualizar el equipo", Toast.LENGTH_SHORT).show());
    }
}
