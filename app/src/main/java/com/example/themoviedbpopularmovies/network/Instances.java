package com.example.themoviedbpopularmovies.network;

import com.example.themoviedbpopularmovies.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Instances {
    private static Retrofit retrofit;
    private static Client client;

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Client getClient(){
        if(client == null){
            client = getRetrofitInstance().create(Client.class);
        }
        return client;
    }

}
