package com.example.cocktaill.data.repository

import androidx.lifecycle.LiveData
import com.example.cocktaill.data.source.entity.Cocktails
import com.example.cocktaill.data.source.entity.Drink
import com.example.cocktaill.data.source.local.CocktailDao
import com.example.cocktaill.data.source.remote.CocktailNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CocktailsRepositoryImpl(
    private val cocktailDao: CocktailDao,
    private val networkDataSource: CocktailNetworkDataSource

) : CocktailsRepository {
    init {
        networkDataSource.downloadCocktail.observeForever { newCocktails ->
            presistFathedCocktils(newCocktails)
        }
    }

    override suspend fun getCocktails(): LiveData<List<Drink>> {

        return withContext(Dispatchers.IO) {
            initCocktailsData()
            return@withContext cocktailDao.LiveDatagetAllCocktails()
        }
    }

    private fun presistFathedCocktils(cocktails: Cocktails) {
        GlobalScope.launch(Dispatchers.IO) {
            val drinks = cocktails.drinks
            cocktailDao.insert(drinks)
        }
    }

    private suspend fun initCocktailsData() {
        networkDataSource.fetchCocktails()
    }

}