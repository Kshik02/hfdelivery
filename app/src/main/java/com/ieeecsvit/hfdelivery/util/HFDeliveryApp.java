package com.ieeecsvit.hfdelivery.util;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class HFDeliveryApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);         //Firebase caching
    }
}
