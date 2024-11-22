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
    private List<Equipo> equipoList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipos, container, false);

        // Vincular elementos del layout
        etSearch = view.findViewById(R.id.etSearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        recyclerEquipos = view.findViewById(R.id.recyclerEquipos);

        // Configurar RecyclerView
        recyclerEquipos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EquipoAdapter(equipoList, new EquipoAdapter.OnEquipoClickListener() {
            @Override
            public void onVisualizar(Equipo equipo) {
                visualizarEquipo(equipo);
            }

            @Override
            public void onActualizar(Equipo equipo) {
                actualizarEquipo(equipo);
            }

            @Override
            public void onEliminar(Equipo equipo) {
                eliminarEquipo(equipo);
            }
        });
        recyclerEquipos.setAdapter(adapter);

        // Configurar acción del botón buscar
        btnSearch.setOnClickListener(v -> buscarEquipos(etSearch.getText().toString().trim()));

        return view;
    }

    private void buscarEquipos(String query) {
        if (query.isEmpty()) {
            Toast.makeText(getContext(), "Ingresa un término de búsqueda", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiClient.buscar(query, getContext(), new ApiClient.ApiResponseListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    equipoList.clear(); // Limpiar lista antes de actualizar
                    if (response.getBoolean("success")) {
                        JSONArray data = response.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject json = data.getJSONObject(i);

                            // Crear objeto Equipo a partir de la respuesta JSON
                            Equipo equipo = new Equipo();
                            equipo.setEmpleadoNombre(json.optString("empleado_nombre", "Sin Nombre"));
                            equipo.setDepartamento(json.optString("departamento", "Sin Departamento"));
                            equipo.setPuesto(json.optString("puesto", "Sin Puesto"));
                            equipo.setEmpresa(json.optString("empresa", "Sin Empresa"));
                            equipo.setUbicacion(json.optString("ubicacion", "Sin Ubicación"));
                            equipo.setEmpleadoCorreo(json.optString("empleado_correo", "Sin Correo"));

                            equipo.setLaptopMarca(json.optString("laptop_marca", "Sin Marca"));
                            equipo.setLaptopModelo(json.optString("laptop_modelo", "Sin Modelo"));
                            equipo.setLaptopNumeroSerie(json.optString("laptop_numero_serie", "Sin Número de Serie"));
                            equipo.setLaptopMac(json.optString("laptop_mac", "Sin MAC"));
                            equipo.setLaptopProcesador(json.optString("laptop_procesador", "Sin Procesador"));
                            equipo.setLaptopRam(json.optString("laptop_ram", "Sin RAM"));
                            equipo.setLaptopAlmacenamiento(json.optString("laptop_almacenamiento", "Sin Almacenamiento"));

                            equipo.setCelularMarca(json.optString("celular_marca", "Sin Marca"));
                            equipo.setCelularModelo(json.optString("celular_modelo", "Sin Modelo"));
                            equipo.setCelularNumero(json.optString("celular_numero", "Sin Número"));
                            equipo.setCelularNumeroSerie(json.optString("celular_numero_serie", "Sin Número de Serie"));
                            equipo.setCelularImei(json.optString("celular_imei", "Sin IMEI"));

                            equipoList.add(equipo);
                        }
                        adapter.notifyDataSetChanged();

                        if (equipoList.isEmpty()) {
                            Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Error al procesar los datos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new ApiClient.ApiErrorListener() {
            @Override
            public void onError(Exception e) {
                Toast.makeText(getContext(), "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void visualizarEquipo(Equipo equipo) {
        // Muestra detalles del equipo en un Toast o implementa un Fragment/Diálogo para más detalles
        String detalles = "Empleado: " + equipo.getEmpleadoNombre() + "\n"
                + "Departamento: " + equipo.getDepartamento() + "\n"
                + "Laptop: " + equipo.getLaptopMarca() + " - " + equipo.getLaptopModelo() + "\n"
                + "Celular: " + equipo.getCelularMarca() + " - " + equipo.getCelularModelo();
        Toast.makeText(getContext(), detalles, Toast.LENGTH_LONG).show();
    }

    private void actualizarEquipo(Equipo equipo) {
        // Implementar lógica para actualización (puede ser abrir un formulario o diálogo)
        Toast.makeText(getContext(), "Actualizando: " + equipo.getEmpleadoNombre(), Toast.LENGTH_SHORT).show();
    }

    private void eliminarEquipo(Equipo equipo) {
        ApiClient.eliminarEquipo(equipo.getEmpleadoNombre(), getContext(), new ApiClient.ApiResponseListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        Toast.makeText(getContext(), "Equipo eliminado: " + equipo.getEmpleadoNombre(), Toast.LENGTH_SHORT).show();
                        equipoList.remove(equipo);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "No se pudo eliminar el equipo", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new ApiClient.ApiErrorListener() {
            @Override
            public void onError(Exception e) {
                Toast.makeText(getContext(), "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
