package com.example.appimv;

public class RegisterRequest {
    private String username;
    private String password;
    private String nombre;
    private String correo;
    private String puesto;
    private String departamento;
    private String empresa;
    private String ubicacion;

    // Constructor
    public RegisterRequest(String username, String password, String nombre, String correo,
                           String puesto, String departamento, String empresa, String ubicacion) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.correo = correo;
        this.puesto = puesto;
        this.departamento = departamento;
        this.empresa = empresa;
        this.ubicacion = ubicacion;
    }

    // Getters y Setters
    // (Agrega todos los getters y setters aquí)
}
