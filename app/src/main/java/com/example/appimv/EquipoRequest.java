package com.example.appimv;

public class EquipoRequest {
    private String tipoEquipo;
    private Object datosEquipo;

    public EquipoRequest(String tipoEquipo, Object datosEquipo) {
        this.tipoEquipo = tipoEquipo;
        this.datosEquipo = datosEquipo;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public Object getDatosEquipo() {
        return datosEquipo;
    }

    public void setDatosEquipo(Object datosEquipo) {
        this.datosEquipo = datosEquipo;
    }
}
