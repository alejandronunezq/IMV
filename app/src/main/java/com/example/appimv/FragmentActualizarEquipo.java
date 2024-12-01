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
    private TextView tvIdEquipo, tvTipoEquipo;
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
        tvTipoEquipo = view.findViewById(R.id.tvTipoEquipo);
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
            tvTipoEquipo.setText("Tipo: " + equipo.getTipo());
        }
    }

    private void actualizarEquipo() {
        if (equipo == null) {
            Toast.makeText(getContext(), "Error: no se pudo cargar el equipo.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Actualizar los datos del equipo con los valores ingresados
        equipo.setMarca(etMarca.getText().toString().trim());
        equipo.setModelo(etModelo.getText().toString().trim());
        equipo.setNumeroSerie(etNumeroSerie.getText().toString().trim());
        equipo.setMacAddress(etMacAddress.getText().toString().trim());
        equipo.setPrecio(etPrecio.getText().toString().trim());
        equipo.setTipo(tvTipoEquipo.getText().toString().replace("Tipo: ", "").trim());

        // Llamar al método de actualización en ApiClient
        ApiClient.actualizarEquipo(equipo, getContext(), response -> {
            Toast.makeText(getContext(), "Equipo actualizado correctamente", Toast.LENGTH_SHORT).show();
            requireActivity().onBackPressed();
        }, error -> Toast.makeText(getContext(), "Error al actualizar el equipo", Toast.LENGTH_SHORT).show());
    }
}
