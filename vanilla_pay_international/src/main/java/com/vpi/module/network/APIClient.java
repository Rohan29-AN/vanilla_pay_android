package com.vpi.module.network;

import com.vpi.module.config.config;
import com.vpi.module.config.utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static Retrofit retrofit=null;

    public static Retrofit getClient(String env){
        if(retrofit==null){
            String BASE_URL= utilities.getBaseUrl(env);
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
