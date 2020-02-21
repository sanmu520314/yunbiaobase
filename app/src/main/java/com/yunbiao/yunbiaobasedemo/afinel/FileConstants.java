package com.yunbiao.yunbiaobasedemo.afinel;

import android.os.Environment;


public class FileConstants {

    private static String RESOURSE_MENU = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String CACHE_BASE_PATH = RESOURSE_MENU+ "/yunbiaobasedemo/";
    public static String IMAGE_CACHE_PATH = CACHE_BASE_PATH + "resource/";// 资源存储目录
    public static String PROPERTY_CACHE_PATH = CACHE_BASE_PATH + "property/";// 参数缓存存储目录
    public static String VIDEO_CACHE_PATH = CACHE_BASE_PATH + "video/";// 参数缓存存储目录
    public static String FILES_CACHE_PATH = CACHE_BASE_PATH + "files/";// 参数缓存存储目录
    public static String APK_CACHE_PATH = CACHE_BASE_PATH + "apk/";// 参数缓存存储目录




}
