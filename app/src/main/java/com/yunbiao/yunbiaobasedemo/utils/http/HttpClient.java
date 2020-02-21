package com.yunbiao.yunbiaobasedemo.utils.http;

import android.util.Log;

import com.yunbiao.yunbiaobasedemo.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpClient {
    private static HttpClient instance;
    private OkHttpClient
            .Builder builder;

    private HttpClient() {
        builder = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        if (BuildConfig.DEBUG) {
                            Log.v("rretrofitLog==>>", message);
                        }
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS);

    }

    public static HttpClient getInstance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }

    public OkHttpClient.Builder getBuilder() {
        return builder;
    }
}
