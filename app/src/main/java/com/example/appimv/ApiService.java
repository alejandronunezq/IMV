package com.example.appimv;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // Inicio de sesión
    @POST("login.php")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    // Registro de usuario
    @POST("register.php")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    // Actualización de equipos
    @POST("actualizar.php")
    Call<GenericResponse> actualizarEquipo(@Body Equipo equipo);

    // Registro de empleados
    @POST("insertar_empleado.php")
    Call<GenericResponse> insertarEmpleado(@Body Empleado empleado);

    // Registro de laptops
    @POST("insertar_equipo.php")
    Call<GenericResponse> insertarLaptop(@Body Laptop laptop);

    // Registro de celulares
    @POST("insertar_equipo.php")
    Call<GenericResponse> insertarCelular(@Body Celular celular);

    // Registro de equipos genéricos (permite manejar lógica compartida)
    @POST("insertar_equipo.php")
    Call<GenericResponse> insertarEquipo(@Body EquipoRequest equipoRequest);
}
