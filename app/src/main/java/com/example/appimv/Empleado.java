package com.example.appimv;

public class Empleado {
    private String nombre;
    private String correo;
    private String departamento;
    private String puesto;
    private String empresa;
    private String ubicacion;
    private Integer celularId;
    private Integer laptopId;

    // Constructor
    public Empleado(String nombre, String correo, String departamento, String puesto,
                    String empresa, String ubicacion, Integer celularId, Integer laptopId) {
        this.nombre = nombre;
        this.correo = correo;
        this.departamento = departamento;
        this.puesto = puesto;
        this.empresa = empresa;
        this.ubicacion = ubicacion;
        this.celularId = celularId;
        this.laptopId = laptopId;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCelularId() {
        return celularId;
    }

    public void setCelularId(Integer celularId) {
        this.celularId = celularId;
    }

    public Integer getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(Integer laptopId) {
        this.laptopId = laptopId;
    }
}
