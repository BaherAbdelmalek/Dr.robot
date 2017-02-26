package com.example.esraa.drrobot;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;



    public interface RestAPI {

        @GET("/welcome")
        Call<Message> getQuestion();

        @Headers({"Content-Type: application/json",
                "Accept: application/json",
                "app_id: 1391036b",
                "app_key: ccc60476e0a6f7b50859cc7c62788994"


        })
        @POST("/chat")
        Call<Message> chatting(@Header("Authorization") String uuid, @Body Message message);

    }
