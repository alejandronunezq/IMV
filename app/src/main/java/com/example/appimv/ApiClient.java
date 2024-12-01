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

    // Método de búsqueda (sin cambios)
    public static void buscar(String query, Context context, ApiResponseListener responseListener, ApiErrorListener errorListener) {
        String url = "https://mynethome.ddns.net/buscar.php?query=" + query;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                responseListener::onResponse,
                error -> errorListener.onError(new Exception(error.getMessage()))
        );

        requestQueue.add(request);
    }

    // Método para actualizar equipos (tu código original)
    public static void actualizarEquipo(Equipo equipo, Context context, ApiResponseListener responseListener, ApiErrorListener errorListener) {
        String url = "https://mynethome.ddns.net/actualizar.php";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", equipo.getId());
            jsonObject.put("tipo_equipo", equipo.getTipo());
            jsonObject.put("marca", equipo.getMarca());
            jsonObject.put("modelo", equipo.getModelo());
            jsonObject.put("numero_serie", equipo.getNumeroSerie());
            jsonObject.put("mac_address", equipo.getMacAddress());
            jsonObject.put("precio", equipo.getPrecio());

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    jsonObject,
                    responseListener::onResponse,
                    error -> errorListener.onError(new Exception(error.getMessage()))
            );

            requestQueue.add(request);
        } catch (Exception e) {
            errorListener.onError(e);
        }
    }

    // Nuevo método para asignar equipos
    public static void asignarEquipo(int empleadoId, Integer laptopId, Integer celularId, Context context, ApiResponseListener responseListener, ApiErrorListener errorListener) {
        String url = "https://mynethome.ddns.net/asignar.php";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("empleado_id", empleadoId);
            jsonObject.put("laptop_id", laptopId); // Puede ser null si no se asigna laptop
            jsonObject.put("celular_id", celularId); // Puede ser null si no se asigna celular

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    jsonObject,
                    responseListener::onResponse,
                    error -> errorListener.onError(new Exception(error.getMessage()))
            );

            requestQueue.add(request);
        } catch (Exception e) {
            errorListener.onError(e);
        }
    }

    // Interfaces para manejar respuestas y errores
    public interface ApiResponseListener {
        void onResponse(JSONObject response);
    }

    public interface ApiErrorListener {
        void onError(Exception e);
    }
}
