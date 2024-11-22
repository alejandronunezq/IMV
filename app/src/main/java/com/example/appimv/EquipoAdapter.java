package com.example.appimv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        // Enlazar datos generales del empleado
        holder.tvEmpleadoNombre.setText(equipo.getEmpleadoNombre());
        holder.tvDepartamento.setText("Departamento: " + equipo.getDepartamento());
        holder.tvPuesto.setText("Puesto: " + equipo.getPuesto());
        holder.tvEmpresa.setText("Empresa: " + equipo.getEmpresa());
        holder.tvUbicacion.setText("Ubicación: " + equipo.getUbicacion());
        holder.tvCorreo.setText("Correo: " + equipo.getEmpleadoCorreo());

        // Enlazar detalles de la laptop
        holder.tvLaptop.setText("Laptop: " + formatLaptopDetails(equipo));
        holder.tvDetallesLaptop.setText("Núm. Serie: " + equipo.getLaptopNumeroSerie() +
                "\nMAC: " + equipo.getLaptopMac() +
                "\nProcesador: " + equipo.getLaptopProcesador() +
                "\nRAM: " + equipo.getLaptopRam() +
                "\nAlmacenamiento: " + equipo.getLaptopAlmacenamiento());

        // Enlazar detalles del celular
        holder.tvCelular.setText("Celular: " + formatCelularDetails(equipo));
        holder.tvDetallesCelular.setText("Número: " + equipo.getCelularNumero() +
                "\nNúm. Serie: " + equipo.getCelularNumeroSerie() +
                "\nIMEI: " + equipo.getCelularImei());

        // Configurar botones
        holder.btnVisualizar.setOnClickListener(v -> listener.onVisualizar(equipo));
        holder.btnActualizar.setOnClickListener(v -> listener.onActualizar(equipo));
        holder.btnEliminar.setOnClickListener(v -> listener.onEliminar(equipo));
    }

    @Override
    public int getItemCount() {
        return equipoList.size();
    }

    /**
     * Formatea los detalles de la laptop.
     */
    private String formatLaptopDetails(Equipo equipo) {
        return (equipo.getLaptopMarca() != null ? equipo.getLaptopMarca() : "Sin Marca") +
                " - " +
                (equipo.getLaptopModelo() != null ? equipo.getLaptopModelo() : "Sin Modelo");
    }

    /**
     * Formatea los detalles del celular.
     */
    private String formatCelularDetails(Equipo equipo) {
        return (equipo.getCelularMarca() != null ? equipo.getCelularMarca() : "Sin Marca") +
                " - " +
                (equipo.getCelularModelo() != null ? equipo.getCelularModelo() : "Sin Modelo");
    }

    public static class EquipoViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmpleadoNombre, tvDepartamento, tvPuesto, tvEmpresa, tvUbicacion, tvCorreo;
        TextView tvLaptop, tvDetallesLaptop, tvCelular, tvDetallesCelular;
        Button btnVisualizar, btnActualizar, btnEliminar;

        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);

            // Vincular las vistas
            tvEmpleadoNombre = itemView.findViewById(R.id.tvEmpleadoNombre);
            tvDepartamento = itemView.findViewById(R.id.tvDepartamento);
            tvPuesto = itemView.findViewById(R.id.tvPuesto);
            tvEmpresa = itemView.findViewById(R.id.tvEmpresa);
            tvUbicacion = itemView.findViewById(R.id.tvUbicacion);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
            tvLaptop = itemView.findViewById(R.id.tvLaptop);
            tvDetallesLaptop = itemView.findViewById(R.id.tvLaptopDetalles);
            tvCelular = itemView.findViewById(R.id.tvCelular);
            tvDetallesCelular = itemView.findViewById(R.id.tvCelularDetalles);
            btnVisualizar = itemView.findViewById(R.id.btnVisualizar);
            btnActualizar = itemView.findViewById(R.id.btnActualizar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    public interface OnEquipoClickListener {
        void onVisualizar(Equipo equipo);

        void onActualizar(Equipo equipo);

        void onEliminar(Equipo equipo);
    }
}
