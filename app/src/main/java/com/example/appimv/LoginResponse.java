package com.example.appimv;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("nombre") // Este debe coincidir con el campo en la respuesta JSON
    private String nombre;

    // Getters para las propiedades
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getNombre() {
        return nombre;
    }
}
