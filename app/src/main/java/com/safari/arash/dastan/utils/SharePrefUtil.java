package com.safari.arash.dastan.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefUtil {
    private Context context;
    private static SharePrefUtil ourInstance;

    public static SharePrefUtil getInstance(Context context) {
        if(ourInstance==null){
            ourInstance = new SharePrefUtil(context);
        }
        return ourInstance;
    }

    private SharePrefUtil(Context context) {
        this.context = context;
    }
    private SharedPreferences.Editor getEditor(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.dastan.app",Context.MODE_PRIVATE);
        return sharedPreferences.edit();
    }
    public void setPosition(String dastanId,int position){
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(dastanId+"position",position).apply();
    }

    public int getPosition(String dastanId){
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.dastan.app",Context.MODE_PRIVATE);
        return sharedPreferences.getInt(dastanId+"position",0);
    }
    public void setTimeSWait(String dastanId,long timSWait){
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(dastanId+"wait",timSWait).apply();
    }
    public long getTimeSWait(String dastanId){
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.dastan.app",Context.MODE_PRIVATE);
        return sharedPreferences.getLong(dastanId+"wait",0);
    }
    public void setLastWaitIndex(String dastanId,int index){
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(dastanId+"index",index).apply();
    }
    public long getLastWaitIndex(String dastanId){
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.dastan.app",Context.MODE_PRIVATE);
        return sharedPreferences.getInt(dastanId+"index",0);
    }
}
