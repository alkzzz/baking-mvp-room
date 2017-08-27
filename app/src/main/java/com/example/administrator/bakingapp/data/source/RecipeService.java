package com.example.administrator.bakingapp.data.source;


import com.example.administrator.bakingapp.data.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {

    @GET("/android-baking-app-json")
    Call<List<Recipe>> getRecipeList();

}