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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentEquipos extends Fragment {

    private EditText etSearch;
    private Button btnSearch;
    private RecyclerView recyclerEquipos;
    private EquipoAdapter adapter;
    private final List<Equipo> equipoList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipos, container, false);

        etSearch = view.findViewById(R.id.etSearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        recyclerEquipos = view.findViewById(R.id.recyclerEquipos);

        recyclerEquipos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EquipoAdapter(equipoList, getContext());
        recyclerEquipos.setAdapter(adapter);

        btnSearch.setOnClickListener(v -> buscarEquipos(etSearch.getText().toString().trim()));

        return view;
    }

    private void buscarEquipos(String query) {
        if (query.isEmpty()) {
            Toast.makeText(getContext(), "Ingresa un término de búsqueda", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiClient.buscar(query, getContext(), response -> {
            try {
                equipoList.clear();
                if (response.getBoolean("success")) {
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject json = data.getJSONObject(i);
                        Equipo equipo = new Equipo();

                        equipo.setId(json.optString("equipo_id"));
                        equipo.setTipo(json.optString("tipo_equipo"));
                        equipo.setMarca(json.optString("marca"));
                        equipo.setModelo(json.optString("modelo"));
                        equipo.setNumeroSerie(json.optString("numero_serie"));
                        equipo.setMacAddress(json.optString("mac_address"));
                        equipo.setPrecio(json.optString("precio"));

                        equipo.setEmpleadoId(json.optString("empleado_id"));
                        equipo.setEmpleadoNombre(json.optString("empleado_nombre"));
                        equipo.setDepartamento(json.optString("departamento"));
                        equipo.setPuesto(json.optString("puesto"));
                        equipo.setEmpresa(json.optString("empresa"));
                        equipo.setUbicacion(json.optString("ubicacion"));
                        equipo.setEmpleadoCorreo(json.optString("empleado_correo"));

                        equipoList.add(equipo);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(getContext(), "Error al procesar los datos", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(getContext(), "Error al conectar con el servidor", Toast.LENGTH_SHORT).show());
    }
}
