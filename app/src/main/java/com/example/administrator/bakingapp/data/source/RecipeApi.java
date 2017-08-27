package com.example.administrator.bakingapp.data.source;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeApi {
    public static final String BASE_URL = "http://go.udacity.com";
    private static Retrofit sRetrofit;

    public static Retrofit getRecipeFromUrl() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return sRetrofit;
    }
}
