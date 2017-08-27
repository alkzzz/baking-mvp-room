package com.example.administrator.bakingapp.data.source.remote;


import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.bakingapp.data.source.RecipesDataSource;

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
    public void getRecipes(@NonNull FetchRecipesCallback callback) {

    }
}
