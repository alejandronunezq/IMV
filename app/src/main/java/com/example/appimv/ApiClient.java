package com.example.appimv;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ApiClient {

    private static RequestQueue requestQueue;

    // Inicializar el RequestQueue
    public static void init(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    // Método para agregar solicitudes a la cola
    public static <T> void addToRequestQueue(Request<T> request) {
        if (requestQueue != null) {
            requestQueue.add(request);
        } else {
            throw new IllegalStateException("ApiClient not initialized. Call ApiClient.init(context) in your Application class.");
        }
    }

    // Método para buscar equipos
    public static void buscar(String query, Context context, ApiResponseListener responseListener, ApiErrorListener errorListener) {
        String url = "https://mynethome.ddns.net/buscar.php?query=" + query;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> responseListener.onResponse(response),
                error -> errorListener.onError(error)
        );

        addToRequestQueue(request); // Usa el método seguro para agregar la solicitud
    }

    public static void eliminarEquipo(String empleadoNombre, Context context, ApiResponseListener apiResponseListener, ApiErrorListener apiErrorListener) {
    }

    public interface ApiResponseListener {
        void onResponse(JSONObject response);
    }

    public interface ApiErrorListener {
        void onError(Exception e);
    }
}
