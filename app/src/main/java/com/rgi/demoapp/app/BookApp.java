package com.rgi.demoapp.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

import com.rgi.demoapp.utils.PreferenceConnector;


public class BookApp extends Application {
    private static BookApp mInstance;

    private static Context mContext;
    public static String currency_code = "";
    public static PreferenceConnector cache;

    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;
    private final String PREFS_NAME = "BookApp";
    public static String SessionKey = "j5aD9uweHEAncbhd";// Must have 16 character session key
    public static double lat = 0.0f, longi = 0.0f;
/*    Fabric fabric;*/

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        BookApp.mContext = getApplicationContext();

        mInstance = this;
        //MultiDex.install(this);
        sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        // Make sure we use vector drawables
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        cache = new PreferenceConnector();


    }

    public static synchronized BookApp getInstance() {
        return mInstance;
    }


}
