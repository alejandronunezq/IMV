package com.example.appimv;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiClient.init(this); // Inicializar ApiClient con el contexto de la aplicación
    }
}
