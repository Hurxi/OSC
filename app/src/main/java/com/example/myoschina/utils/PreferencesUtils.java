package com.example.myoschina.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.widget.StackView;

import com.example.myoschina.App;

/**
 * SharedPreferences工具类
 * * Created by 若希 on 2017/5/3.
 */

public class PreferencesUtils {
    public static String PREFERENCE_NAME="oschina";
    public PreferencesUtils(){

    }
    public static boolean putString(String key, String value) {

        SharedPreferences pref = App.getContext().getSharedPreferences("oschina", Context.MODE_PRIVATE);
        //文件名称  文件存储模式
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(key,value);
        return edit.commit();
    }
    public static boolean putInt(String key, int value) {

        SharedPreferences pref = App.getContext().getSharedPreferences("oschina", Context.MODE_PRIVATE);
        //文件名称  文件存储模式
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(key,value);
        return edit.commit();
    }
    public static String getString(String key,@Nullable String defaultValue){
        //可以为空
        SharedPreferences sharedPreferences=App.getContext().getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,defaultValue);

    }
    public static String getString(String key){
        //可以为空
        SharedPreferences sharedPreferences=App.getContext().getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return getString(key,null);

    }
    public static int getInt(String key,@Nullable int defaultValue){
        //可以为空
        SharedPreferences sharedPreferences=App.getContext().getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,defaultValue);
    }
    public static int getInt(String key){
        //可以为空
        SharedPreferences sharedPreferences=App.getContext().getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return getInt(key,0);
    }
}
