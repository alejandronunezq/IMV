package com.example.appimv;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    // Endpoint para el inicio de sesión
    @FormUrlEncoded
    @POST("login.php") // Ruta relativa al endpoint en el servidor
    Call<LoginResponse> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    // Endpoint para el registro de usuario
    @FormUrlEncoded
    @POST("register.php") // Ruta relativa al endpoint en el servidor
    Call<RegisterResponse> registerUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nombre") String nombre,
            @Field("correo") String correo,
            @Field("puesto") String puesto,
            @Field("departamento") String departamento,
            @Field("empresa") String empresa,
            @Field("ubicacion") String ubicacion
    );
}
