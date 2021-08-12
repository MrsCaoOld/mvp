package com.bw.Caohaigang20210806.util;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {
    public Utils() {
    }
    public static Utils utils = new Utils();

    public static Utils getUtils() {
//        if(utils!=null){
//            synchronized (Utils.class){
//                if(utils!=null){
//                    utils = new Utils();
//                }
//            }
//        }
        return utils;
    }
    public Retrofit abc(String url){
        Retrofit build = new Retrofit.Builder()
                .client(
                        new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()
                )
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return build;
    }
}
