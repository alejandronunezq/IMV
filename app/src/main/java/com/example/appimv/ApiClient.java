package com.example.appimv;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ApiClient {

    private static RequestQueue requestQueue;

    public static void init(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    public static <T> void addToRequestQueue(Request<T> request) {
        if (requestQueue != null) {
            requestQueue.add(request);
        } else {
            throw new IllegalStateException("ApiClient not initialized. Call ApiClient.init(context) in your Application class.");
        }
    }

    public static void buscar(String query, Context context, ApiResponseListener responseListener, ApiErrorListener errorListener) {
        String url = "https://mynethome.ddns.net/buscar.php?query=" + query;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> responseListener.onResponse(response),
                error -> errorListener.onError(new Exception(error.getMessage()))
        );

        addToRequestQueue(request);
    }

    public static void actualizarEquipo(Equipo equipo, Context context, ApiResponseListener responseListener, ApiErrorListener errorListener) {
        String url = "https://mynethome.ddns.net/actualizar.php";

        JSONObject params = new JSONObject();
        try {
            params.put("empleado_id", equipo.getEmpleadoId());
            params.put("empleado_nombre", equipo.getEmpleadoNombre());
            params.put("departamento", equipo.getDepartamento());
            params.put("puesto", equipo.getPuesto());
            params.put("empresa", equipo.getEmpresa());
            params.put("ubicacion", equipo.getUbicacion());
            params.put("empleado_correo", equipo.getEmpleadoCorreo());

            params.put("laptop_marca", equipo.getLaptopMarca());
            params.put("laptop_modelo", equipo.getLaptopModelo());
            params.put("laptop_numero_serie", equipo.getLaptopNumeroSerie());
            params.put("laptop_mac", equipo.getLaptopMac());
            params.put("laptop_procesador", equipo.getLaptopProcesador());
            params.put("laptop_ram", equipo.getLaptopRam());
            params.put("laptop_almacenamiento", equipo.getLaptopAlmacenamiento());

            params.put("celular_marca", equipo.getCelularMarca());
            params.put("celular_modelo", equipo.getCelularModelo());
            params.put("celular_numero", equipo.getCelularNumero());
            params.put("celular_numero_serie", equipo.getCelularNumeroSerie());
            params.put("celular_imei", equipo.getCelularImei());
        } catch (Exception e) {
            errorListener.onError(e);
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                params,
                response -> responseListener.onResponse(response),
                error -> errorListener.onError(new Exception(error.getMessage()))
        );

        addToRequestQueue(request);
    }

    public static void eliminarEquipo(String empleadoNombre, Context context, ApiResponseListener responseListener, ApiErrorListener errorListener) {
        String url = "https://mynethome.ddns.net/eliminar.php";

        JSONObject params = new JSONObject();
        try {
            params.put("empleado_nombre", empleadoNombre);
        } catch (Exception e) {
            errorListener.onError(e);
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                params,
                response -> responseListener.onResponse(response),
                error -> errorListener.onError(new Exception(error.getMessage()))
        );

        addToRequestQueue(request);
    }

    public interface ApiResponseListener {
        void onResponse(JSONObject response);
    }

    public interface ApiErrorListener {
        void onError(Exception e);
    }
}
