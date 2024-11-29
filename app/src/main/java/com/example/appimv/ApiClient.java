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

    public static void actualizarEquipo(Equipo equipo, Context context, ApiResponseListener responseListener, ApiErrorListener errorListener) {
        String url = "https://mynethome.ddns.net/actualizar.php";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", equipo.getId());
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

    public interface ApiResponseListener {
        void onResponse(JSONObject response);
    }

    public interface ApiErrorListener {
        void onError(Exception e);
    }
}
