package fr.istic.geoartiste.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import fr.istic.geoartiste.BuildConfig;

public class TraceLog {

    public static void debug(String tag, String message) {
        if(BuildConfig.DEBUG){
            Log.d(tag,message);
        }
    }

    public static void debug(String message) {
        if(BuildConfig.DEBUG){
            Log.d("TraceLog debug",message);
        }
    }
    
    public static void error(String tag, String message) {
        if(BuildConfig.DEBUG){
            Log.e(tag,message);
        }
    }

    public static void error(String message) {
        if(BuildConfig.DEBUG){
            Log.e("TraceLog error",message);
        }
    }
    
    public static void toast(String message, Context context) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
    
}