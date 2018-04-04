package com.idme;

import android.app.Application;

/**
 * Created by pranav.dixit on 29/03/18.
 */

public class Idme extends Application {

    private static Idme application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Idme getInstance() {
        return application;
    }
}
