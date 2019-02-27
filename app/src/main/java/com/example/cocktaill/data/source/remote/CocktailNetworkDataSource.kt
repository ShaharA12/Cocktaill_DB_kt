package com.example.cocktaill.data.source.remote

import androidx.lifecycle.LiveData
import com.example.cocktaill.data.source.entity.Cocktails

/**
 * Created by Shahar.A. on 24/02/2019.
 */
interface CocktailNetworkDataSource {
    val downloadCocktail:LiveData<Cocktails>

    suspend fun fetchCocktails()
}