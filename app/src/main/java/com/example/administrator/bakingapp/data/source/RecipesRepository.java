package com.example.administrator.bakingapp.data.source;


import android.support.annotation.NonNull;

import com.example.administrator.bakingapp.data.Recipe;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class RecipesRepository implements RecipesDataSource {

    private static RecipesRepository INSTANCE = null;

    private final RecipesDataSource mRemoteRecipesDataSource;

    private final RecipesDataSource mLocalRecipesDataSource;

    List<Recipe> mCachedRecipes;

    boolean mCachedIsNotValid;


    public RecipesRepository(RecipesDataSource remoteRecipesDataSource, RecipesDataSource localRecipesDataSource) {
        mRemoteRecipesDataSource = remoteRecipesDataSource;
        mLocalRecipesDataSource = localRecipesDataSource;
    }

    public static RecipesRepository getInstance(RecipesDataSource recipeRemoteDataSource, RecipesDataSource recipesLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new RecipesRepository(recipeRemoteDataSource, recipesLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getRecipes(@NonNull final FetchRecipesCallback callback) {
        if(mCachedRecipes != null && !mCachedIsNotValid) {
            callback.onRecipesLoaded(mCachedRecipes);
        }

        if(mCachedIsNotValid) {
            getRecipesFromRemoteDataSource(callback);
        } else {
            mLocalRecipesDataSource.getRecipes(new FetchRecipesCallback() {
                @Override
                public void onRecipesLoaded(List<Recipe> recipes) {
                    refreshCache(recipes);
                    callback.onRecipesLoaded(new ArrayList<Recipe>());
                }

                @Override
                public void onDataNotAvailable() {
                    getRecipesFromRemoteDataSource(callback);
                }
            });
        }
    }

    private void getRecipesFromRemoteDataSource(final FetchRecipesCallback callback) {
        mRemoteRecipesDataSource.getRecipes(new FetchRecipesCallback() {
            @Override
            public void onRecipesLoaded(List<Recipe> recipes) {
                refreshCache(recipes);
                refreshLocalDataSource(recipes);
                callback.onRecipesLoaded(new ArrayList<Recipe>());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void refreshLocalDataSource(List<Recipe> recipes) {
        mLocalRecipesDataSource.deleteAllRecipes();
        for (Recipe recipe: recipes) {
            mLocalRecipesDataSource.storeRecipe(recipe);
        }
    }

    private void refreshCache(List<Recipe> recipes) {
        if(mCachedRecipes == null) {
            mCachedRecipes = new ArrayList<>();
        }
        mCachedRecipes.clear();
        mCachedRecipes.addAll(recipes);
        mCachedIsNotValid = false;
    }
}
