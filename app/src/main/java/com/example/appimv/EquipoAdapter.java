package com.example.appimv;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder> {

    private final List<Equipo> equipoList;
    private final Context context;

    public EquipoAdapter(List<Equipo> equipoList, Context context) {
        this.equipoList = equipoList;
        this.context = context;
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

        // Configurar los datos del equipo en las vistas
        holder.tvIdEquipo.setText("ID: " + equipo.getId());
        holder.tvTipoEquipo.setText("Tipo: " + equipo.getTipo());
        holder.tvMarca.setText("Marca: " + equipo.getMarca());
        holder.tvModelo.setText("Modelo: " + equipo.getModelo());
        holder.tvNumeroSerie.setText("Número de Serie: " + equipo.getNumeroSerie());
        holder.tvMacAddress.setText("MAC: " + equipo.getMacAddress());
        holder.tvPrecio.setText("Precio: " + equipo.getPrecio());
        holder.tvEmpleadoNombre.setText("Empleado: " + equipo.getEmpleadoNombre());
        holder.tvDepartamento.setText("Departamento: " + equipo.getDepartamento());
        holder.tvPuesto.setText("Puesto: " + equipo.getPuesto());
        holder.tvEmpresa.setText("Empresa: " + equipo.getEmpresa());
        holder.tvUbicacion.setText("Ubicación: " + equipo.getUbicacion());
        holder.tvEmpleadoCorreo.setText("Correo: " + equipo.getEmpleadoCorreo());

        // Configurar el clic en el botón Actualizar
        holder.btnActualizar.setOnClickListener(v -> {
            FragmentActualizarEquipo fragment = new FragmentActualizarEquipo();

            // Pasar los datos del equipo al FragmentActualizarEquipo
            Bundle bundle = new Bundle();
            bundle.putSerializable("equipo", equipo);
            fragment.setArguments(bundle);

            // Reemplazar el fragmento actual con FragmentActualizarEquipo
            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return equipoList.size();
    }

    public static class EquipoViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdEquipo, tvTipoEquipo, tvMarca, tvModelo, tvNumeroSerie, tvMacAddress, tvPrecio;
        TextView tvEmpleadoNombre, tvDepartamento, tvPuesto, tvEmpresa, tvUbicacion, tvEmpleadoCorreo;
        Button btnActualizar;

        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdEquipo = itemView.findViewById(R.id.tvIdEquipo);
            tvTipoEquipo = itemView.findViewById(R.id.tvTipoEquipo);
            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvModelo = itemView.findViewById(R.id.tvModelo);
            tvNumeroSerie = itemView.findViewById(R.id.tvNumeroSerie);
            tvMacAddress = itemView.findViewById(R.id.tvMacAddress);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvEmpleadoNombre = itemView.findViewById(R.id.tvEmpleadoNombre);
            tvDepartamento = itemView.findViewById(R.id.tvDepartamento);
            tvPuesto = itemView.findViewById(R.id.tvPuesto);
            tvEmpresa = itemView.findViewById(R.id.tvEmpresa);
            tvUbicacion = itemView.findViewById(R.id.tvUbicacion);
            tvEmpleadoCorreo = itemView.findViewById(R.id.tvEmpleadoCorreo);
            btnActualizar = itemView.findViewById(R.id.btnActualizar);
        }
    }
}
