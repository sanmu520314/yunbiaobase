package com.yunbiao.yunbiaobasedemo.utils.http;

import com.yunbiao.yunbiaobasedemo.afinel.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance;
    private Retrofit.Builder mRetrofitBuilder;
    private OkHttpClient.Builder mOkhttpBuilder;

    private RetrofitClient() {
        mOkhttpBuilder = HttpClient.getInstance().getBuilder();
        mRetrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkhttpBuilder.build());
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    public static String BaseURL =  Constants.BASEURL;

    public Retrofit getRetrofit() {
        return mRetrofitBuilder
                .baseUrl(BaseURL)
                .client(mOkhttpBuilder.build())
                .build();
    }
}
