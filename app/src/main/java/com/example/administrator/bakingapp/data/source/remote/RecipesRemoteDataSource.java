package com.example.administrator.bakingapp.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.bakingapp.data.Recipe;
import com.example.administrator.bakingapp.data.source.RecipeApi;
import com.example.administrator.bakingapp.data.source.RecipeService;
import com.example.administrator.bakingapp.data.source.RecipesDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesRemoteDataSource implements RecipesDataSource {
    private static RecipesRemoteDataSource INSTANCE;

    public static RecipesRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RecipesRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private RecipesRemoteDataSource() {}

    @Override
    public void getRecipes(@NonNull final FetchRecipesCallback callback) {
        RecipeService recipeService = RecipeApi.getRecipeFromUrl().create(RecipeService.class);
        Call<List<Recipe>> call = recipeService.getRecipeList();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                callback.onRecipesLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d(RecipesRemoteDataSource.class.getSimpleName(), t.getMessage());
            }
        });
    }
}
