package com.example.administrator.bakingapp.recipes;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.bakingapp.R;
import com.example.administrator.bakingapp.data.Recipe;
import com.example.administrator.bakingapp.data.source.RecipesDataSource;
import com.example.administrator.bakingapp.data.source.remote.RecipesRemoteDataSource;

import java.util.List;

public class RecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        RecipesRemoteDataSource recipesRemoteDataSource = RecipesRemoteDataSource.getInstance();
        recipesRemoteDataSource.getRecipes(new RecipesDataSource.FetchRecipesCallback() {
            @Override
            public void onRecipesLoaded(List<Recipe> recipes) {
                for (Recipe recipe: recipes) {
                    Log.d("coba", recipe.getName());
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

}
