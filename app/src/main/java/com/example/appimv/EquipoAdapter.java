package com.example.appimv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder> {

    private final List<Equipo> equipoList;
    private final OnEquipoClickListener listener;

    public EquipoAdapter(List<Equipo> equipoList, OnEquipoClickListener listener) {
        this.equipoList = equipoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipo, parent, false);
        return new EquipoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoViewHolder holder, int position) {
        Equipo equipo = equipoList.get(position);

        // Mostrar datos generales del empleado
        holder.tvEmpleadoNombre.setText(equipo.getEmpleadoNombre());
        holder.tvDepartamento.setText("Departamento: " + equipo.getDepartamento());
        holder.tvPuesto.setText("Puesto: " + equipo.getPuesto());
        holder.tvEmpresa.setText("Empresa: " + equipo.getEmpresa());
        holder.tvUbicacion.setText("Ubicación: " + equipo.getUbicacion());
        holder.etCorreo.setText(equipo.getEmpleadoCorreo());

        // Mostrar datos de la laptop
        holder.etLaptopMarca.setText(equipo.getLaptopMarca());
        holder.etLaptopModelo.setText(equipo.getLaptopModelo());
        holder.etLaptopNumeroSerie.setText(equipo.getLaptopNumeroSerie());
        holder.etLaptopMac.setText(equipo.getLaptopMac());
        holder.etLaptopProcesador.setText(equipo.getLaptopProcesador());
        holder.etLaptopRam.setText(equipo.getLaptopRam());
        holder.etLaptopAlmacenamiento.setText(equipo.getLaptopAlmacenamiento());

        // Mostrar datos del celular
        holder.etCelularMarca.setText(equipo.getCelularMarca());
        holder.etCelularModelo.setText(equipo.getCelularModelo());
        holder.etCelularNumero.setText(equipo.getCelularNumero());
        holder.etCelularNumeroSerie.setText(equipo.getCelularNumeroSerie());
        holder.etCelularImei.setText(equipo.getCelularImei());

        // Deshabilitar campos inicialmente
        setFieldsEditable(holder, false);

        // Configurar botón de actualizar
        holder.btnActualizar.setOnClickListener(v -> {
            setFieldsEditable(holder, true);
            holder.btnActualizar.setVisibility(View.GONE);
            holder.btnGuardar.setVisibility(View.VISIBLE);
        });

        // Configurar botón de guardar
        holder.btnGuardar.setOnClickListener(v -> {
            setFieldsEditable(holder, false);
            holder.btnActualizar.setVisibility(View.VISIBLE);
            holder.btnGuardar.setVisibility(View.GONE);

            // Actualizar datos en el objeto equipo
            equipo.setEmpleadoCorreo(holder.etCorreo.getText().toString());
            equipo.setLaptopMarca(holder.etLaptopMarca.getText().toString());
            equipo.setLaptopModelo(holder.etLaptopModelo.getText().toString());
            equipo.setLaptopNumeroSerie(holder.etLaptopNumeroSerie.getText().toString());
            equipo.setLaptopMac(holder.etLaptopMac.getText().toString());
            equipo.setLaptopProcesador(holder.etLaptopProcesador.getText().toString());
            equipo.setLaptopRam(holder.etLaptopRam.getText().toString());
            equipo.setLaptopAlmacenamiento(holder.etLaptopAlmacenamiento.getText().toString());
            equipo.setCelularMarca(holder.etCelularMarca.getText().toString());
            equipo.setCelularModelo(holder.etCelularModelo.getText().toString());
            equipo.setCelularNumero(holder.etCelularNumero.getText().toString());
            equipo.setCelularNumeroSerie(holder.etCelularNumeroSerie.getText().toString());
            equipo.setCelularImei(holder.etCelularImei.getText().toString());

            // Llamar a la API para actualizar los datos
            listener.onActualizar(equipo);
        });

        // Configurar botón de eliminar
        holder.btnEliminar.setOnClickListener(v -> listener.onEliminar(equipo));
    }

    @Override
    public int getItemCount() {
        return equipoList.size();
    }

    private void setFieldsEditable(EquipoViewHolder holder, boolean editable) {
        holder.etCorreo.setEnabled(editable);
        holder.etLaptopMarca.setEnabled(editable);
        holder.etLaptopModelo.setEnabled(editable);
        holder.etLaptopNumeroSerie.setEnabled(editable);
        holder.etLaptopMac.setEnabled(editable);
        holder.etLaptopProcesador.setEnabled(editable);
        holder.etLaptopRam.setEnabled(editable);
        holder.etLaptopAlmacenamiento.setEnabled(editable);
        holder.etCelularMarca.setEnabled(editable);
        holder.etCelularModelo.setEnabled(editable);
        holder.etCelularNumero.setEnabled(editable);
        holder.etCelularNumeroSerie.setEnabled(editable);
        holder.etCelularImei.setEnabled(editable);
    }

    public static class EquipoViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmpleadoNombre, tvDepartamento, tvPuesto, tvEmpresa, tvUbicacion;
        EditText etCorreo, etLaptopMarca, etLaptopModelo, etLaptopNumeroSerie, etLaptopMac,
                etLaptopProcesador, etLaptopRam, etLaptopAlmacenamiento, etCelularMarca,
                etCelularModelo, etCelularNumero, etCelularNumeroSerie, etCelularImei;
        Button btnVisualizar, btnActualizar, btnGuardar, btnEliminar;

        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);

            // Vincular vistas
            tvEmpleadoNombre = itemView.findViewById(R.id.tvEmpleadoNombre);
            tvDepartamento = itemView.findViewById(R.id.tvDepartamento);
            tvPuesto = itemView.findViewById(R.id.tvPuesto);
            tvEmpresa = itemView.findViewById(R.id.tvEmpresa);
            tvUbicacion = itemView.findViewById(R.id.tvUbicacion);
            etCorreo = itemView.findViewById(R.id.etCorreo);
            etLaptopMarca = itemView.findViewById(R.id.etLaptopMarca);
            etLaptopModelo = itemView.findViewById(R.id.etLaptopModelo);
            etLaptopNumeroSerie = itemView.findViewById(R.id.etLaptopNumeroSerie);
            etLaptopMac = itemView.findViewById(R.id.etLaptopMac);
            etLaptopProcesador = itemView.findViewById(R.id.etLaptopProcesador);
            etLaptopRam = itemView.findViewById(R.id.etLaptopRam);
            etLaptopAlmacenamiento = itemView.findViewById(R.id.etLaptopAlmacenamiento);
            etCelularMarca = itemView.findViewById(R.id.etCelularMarca);
            etCelularModelo = itemView.findViewById(R.id.etCelularModelo);
            etCelularNumero = itemView.findViewById(R.id.etCelularNumero);
            etCelularNumeroSerie = itemView.findViewById(R.id.etCelularNumeroSerie);
            etCelularImei = itemView.findViewById(R.id.etCelularImei);

            btnActualizar = itemView.findViewById(R.id.btnActualizar);
            btnGuardar = itemView.findViewById(R.id.btnGuardar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    public interface OnEquipoClickListener {
        void onVisualizar(Equipo equipo);

        void onActualizar(Equipo equipo);

        void onEliminar(Equipo equipo);
    }
}
