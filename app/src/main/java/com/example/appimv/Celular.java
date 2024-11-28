package com.example.appimv;

public class Celular {
    private String marca;
    private String modelo;
    private String numeroSerie;
    private String macAddress;
    private String numero;
    private String correo;
    private String imei;
    private double precio;

    public Celular(String marca, String modelo, String numeroSerie, String macAddress,
                   String numero, String correo, String imei, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.macAddress = macAddress;
        this.numero = numero;
        this.correo = correo;
        this.imei = imei;
        this.precio = precio;
    }

    // Getters y setters
    // (Generar automáticamente con tu IDE)
}
