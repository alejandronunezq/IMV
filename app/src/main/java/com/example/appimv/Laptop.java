package com.example.appimv;

public class Laptop {
    private String marca;
    private String modelo;
    private String numeroSerie;
    private String macAddress;
    private String procesador;
    private String ram;
    private String almacenamiento;
    private double precio;

    public Laptop(String marca, String modelo, String numeroSerie, String macAddress,
                  String procesador, String ram, String almacenamiento, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.macAddress = macAddress;
        this.procesador = procesador;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.precio = precio;
    }

    // Getters y setters
    // (Generar automáticamente con tu IDE)
}
