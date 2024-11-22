package com.example.appimv;

public class Equipo {

    private String empleadoNombre;
    private String departamento;
    private String puesto;
    private String empresa;
    private String ubicacion;
    private String empleadoCorreo;

    private String laptopMarca;
    private String laptopModelo;
    private String laptopNumeroSerie;
    private String laptopMac;
    private String laptopProcesador;
    private String laptopRam;
    private String laptopAlmacenamiento;

    private String celularMarca;
    private String celularModelo;
    private String celularNumero;
    private String celularNumeroSerie;
    private String celularImei;

    // Constructor vacío
    public Equipo() {
    }

    // Constructor completo
    public Equipo(String empleadoNombre, String departamento, String puesto, String empresa, String ubicacion,
                  String empleadoCorreo, String laptopMarca, String laptopModelo, String laptopNumeroSerie,
                  String laptopMac, String laptopProcesador, String laptopRam, String laptopAlmacenamiento,
                  String celularMarca, String celularModelo, String celularNumero, String celularNumeroSerie,
                  String celularImei) {
        this.empleadoNombre = empleadoNombre;
        this.departamento = departamento;
        this.puesto = puesto;
        this.empresa = empresa;
        this.ubicacion = ubicacion;
        this.empleadoCorreo = empleadoCorreo;

        this.laptopMarca = laptopMarca;
        this.laptopModelo = laptopModelo;
        this.laptopNumeroSerie = laptopNumeroSerie;
        this.laptopMac = laptopMac;
        this.laptopProcesador = laptopProcesador;
        this.laptopRam = laptopRam;
        this.laptopAlmacenamiento = laptopAlmacenamiento;

        this.celularMarca = celularMarca;
        this.celularModelo = celularModelo;
        this.celularNumero = celularNumero;
        this.celularNumeroSerie = celularNumeroSerie;
        this.celularImei = celularImei;
    }

    // Getters y setters para atributos del empleado
    public String getEmpleadoNombre() {
        return empleadoNombre != null ? empleadoNombre : "N/A";
    }

    public void setEmpleadoNombre(String empleadoNombre) {
        this.empleadoNombre = empleadoNombre.trim();
    }

    public String getDepartamento() {
        return departamento != null ? departamento : "Sin Departamento";
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento.trim();
    }

    public String getPuesto() {
        return puesto != null ? puesto : "Sin Puesto";
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto.trim();
    }

    public String getEmpresa() {
        return empresa != null ? empresa : "Sin Empresa";
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa.trim();
    }

    public String getUbicacion() {
        return ubicacion != null ? ubicacion : "Sin Ubicación";
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion.trim();
    }

    public String getEmpleadoCorreo() {
        return empleadoCorreo != null ? empleadoCorreo : "Sin Correo";
    }

    public void setEmpleadoCorreo(String empleadoCorreo) {
        this.empleadoCorreo = empleadoCorreo.trim();
    }

    // Getters y setters para atributos de la laptop
    public String getLaptopMarca() {
        return laptopMarca != null ? laptopMarca : "Sin Marca";
    }

    public void setLaptopMarca(String laptopMarca) {
        this.laptopMarca = laptopMarca.trim();
    }

    public String getLaptopModelo() {
        return laptopModelo != null ? laptopModelo : "Sin Modelo";
    }

    public void setLaptopModelo(String laptopModelo) {
        this.laptopModelo = laptopModelo.trim();
    }

    public String getLaptopNumeroSerie() {
        return laptopNumeroSerie != null ? laptopNumeroSerie : "Sin Número de Serie";
    }

    public void setLaptopNumeroSerie(String laptopNumeroSerie) {
        this.laptopNumeroSerie = laptopNumeroSerie.trim();
    }

    public String getLaptopMac() {
        return laptopMac != null ? laptopMac : "Sin MAC Address";
    }

    public void setLaptopMac(String laptopMac) {
        this.laptopMac = laptopMac.trim();
    }

    public String getLaptopProcesador() {
        return laptopProcesador != null ? laptopProcesador : "Sin Procesador";
    }

    public void setLaptopProcesador(String laptopProcesador) {
        this.laptopProcesador = laptopProcesador.trim();
    }

    public String getLaptopRam() {
        return laptopRam != null ? laptopRam : "Sin RAM";
    }

    public void setLaptopRam(String laptopRam) {
        this.laptopRam = laptopRam.trim();
    }

    public String getLaptopAlmacenamiento() {
        return laptopAlmacenamiento != null ? laptopAlmacenamiento : "Sin Almacenamiento";
    }

    public void setLaptopAlmacenamiento(String laptopAlmacenamiento) {
        this.laptopAlmacenamiento = laptopAlmacenamiento.trim();
    }

    // Getters y setters para atributos del celular
    public String getCelularMarca() {
        return celularMarca != null ? celularMarca : "Sin Marca";
    }

    public void setCelularMarca(String celularMarca) {
        this.celularMarca = celularMarca.trim();
    }

    public String getCelularModelo() {
        return celularModelo != null ? celularModelo : "Sin Modelo";
    }

    public void setCelularModelo(String celularModelo) {
        this.celularModelo = celularModelo.trim();
    }

    public String getCelularNumero() {
        return celularNumero != null ? celularNumero : "Sin Número";
    }

    public void setCelularNumero(String celularNumero) {
        this.celularNumero = celularNumero.trim();
    }

    public String getCelularNumeroSerie() {
        return celularNumeroSerie != null ? celularNumeroSerie : "Sin Número de Serie";
    }

    public void setCelularNumeroSerie(String celularNumeroSerie) {
        this.celularNumeroSerie = celularNumeroSerie.trim();
    }

    public String getCelularImei() {
        return celularImei != null ? celularImei : "Sin IMEI";
    }

    public void setCelularImei(String celularImei) {
        this.celularImei = celularImei != null ? celularImei.trim() : null;
    }

    // Método para mostrar información completa (opcional)
    @Override
    public String toString() {
        return "Empleado: " + empleadoNombre + " | Laptop: " + laptopMarca + " " + laptopModelo + " | Celular: " + celularMarca + " " + celularModelo;
    }
}
