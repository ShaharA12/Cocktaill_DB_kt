package com.example.cocktaill.data.repository

import androidx.lifecycle.LiveData
import com.example.cocktaill.data.source.entity.Cocktails
import com.example.cocktaill.data.source.entity.Drink

/**
 * Created by Shahar.A. on 24/02/2019.
 */
interface CocktailsRepository {

    suspend fun getCocktails(): LiveData<List<Drink>>
}