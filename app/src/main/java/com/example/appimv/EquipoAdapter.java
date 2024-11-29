package com.example.appimv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder> {

    private final List<Equipo> equipoList;

    public EquipoAdapter(List<Equipo> equipoList) {
        this.equipoList = equipoList;
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

        holder.tvTipoEquipo.setText(equipo.getTipo());
        holder.tvMarca.setText(equipo.getMarca());
        holder.tvModelo.setText(equipo.getModelo());
        holder.tvNumeroSerie.setText(equipo.getNumeroSerie());
        holder.tvMacAddress.setText(equipo.getMacAddress());
        holder.tvPrecio.setText(equipo.getPrecio());

        holder.tvEmpleadoNombre.setText(equipo.getEmpleadoNombre());
        holder.tvDepartamento.setText(equipo.getDepartamento());
        holder.tvPuesto.setText(equipo.getPuesto());
        holder.tvEmpresa.setText(equipo.getEmpresa());
        holder.tvUbicacion.setText(equipo.getUbicacion());
        holder.tvEmpleadoCorreo.setText(equipo.getEmpleadoCorreo());
    }

    @Override
    public int getItemCount() {
        return equipoList.size();
    }

    public static class EquipoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTipoEquipo, tvMarca, tvModelo, tvNumeroSerie, tvMacAddress, tvPrecio;
        TextView tvEmpleadoNombre, tvDepartamento, tvPuesto, tvEmpresa, tvUbicacion, tvEmpleadoCorreo;

        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);

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
        }
    }
}
