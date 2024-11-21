package com.example.appimv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMiCuenta extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_mi_cuenta, container, false);

        // Obtener el TextView donde se mostrará el nombre
        TextView nombreTextView = view.findViewById(R.id.nombre_usuario);

        // Obtener el nombre desde el Bundle
        Bundle arguments = getArguments();
        if (arguments != null) {
            String nombre = arguments.getString("NOMBRE");
            // Mostrar el nombre en el TextView
            nombreTextView.setText(nombre);
        }

        return view;
    }
}
