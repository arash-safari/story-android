package com.safari.arash.dastan.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
    @SuppressLint("StaticFieldLeak")
    private static IOUtils ourInstance;
    private Context context;
    public static IOUtils getInstance(Context context) {
        if(ourInstance==null){
            ourInstance = new IOUtils(context);
        }
        return ourInstance;
    }

    private IOUtils(Context context) {
        this.context = context;
    }
    public String loadJSONFromAsset(String fileName) {
        String json;
        try {
            InputStream is = context.getResources()
                    .getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public String jsonStringParser(JSONObject jsonObject,String st){
        if(jsonObject.has(st)) {
            try {
                return jsonObject.getString(st);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
    public int jsonIntParser(JSONObject jsonObject, String st){
        if(jsonObject.has(st)) {
            try {
                return jsonObject.getInt(st);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
