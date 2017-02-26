package com.example.esraa.drrobot;

/**
 * Created by baher on 12/3/16.
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseAPI {

    public static final String BASE_URL = "https://Dr-robot-baherabdelmalek100667523.codeanyapp.com";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}