package com.example.m213_retrofit_post;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteAPI {

    public MovieApi getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.48.3.250/RestAPi_Movie/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        return retrofit.create(MovieApi.class);


    }

}
