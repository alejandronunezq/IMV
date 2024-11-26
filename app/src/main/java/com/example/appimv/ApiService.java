package com.example.appimv;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
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

    @FormUrlEncoded
    @POST("actualizar.php")
    Call<GenericResponse> actualizarEquipo(
            @Field("empleado_id") String empleadoId,
            @Field("empleado_nombre") String empleadoNombre,
            @Field("departamento") String departamento,
            @Field("puesto") String puesto,
            @Field("empresa") String empresa,
            @Field("ubicacion") String ubicacion,
            @Field("empleado_correo") String empleadoCorreo,
            @Field("laptop_marca") String laptopMarca,
            @Field("laptop_modelo") String laptopModelo,
            @Field("laptop_numero_serie") String laptopNumeroSerie,
            @Field("laptop_mac") String laptopMac,
            @Field("laptop_procesador") String laptopProcesador,
            @Field("laptop_ram") String laptopRam,
            @Field("laptop_almacenamiento") String laptopAlmacenamiento,
            @Field("celular_marca") String celularMarca,
            @Field("celular_modelo") String celularModelo,
            @Field("celular_numero") String celularNumero,
            @Field("celular_numero_serie") String celularNumeroSerie,
            @Field("celular_imei") String celularImei
    );
}
