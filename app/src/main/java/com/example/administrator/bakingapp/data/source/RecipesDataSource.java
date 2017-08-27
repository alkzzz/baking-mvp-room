package com.example.administrator.bakingapp.data.source;


import android.support.annotation.NonNull;

import com.example.administrator.bakingapp.data.Recipe;

import java.util.List;

public interface RecipesDataSource {

    interface FetchRecipesCallback {

        void onRecipesLoaded(List<Recipe> recipes);

        void onDataNotAvailable();
    }

    void getRecipes(@NonNull FetchRecipesCallback callback);
}
