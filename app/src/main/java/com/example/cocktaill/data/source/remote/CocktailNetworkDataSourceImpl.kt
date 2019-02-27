package com.example.cocktaill.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cocktaill.data.source.entity.Cocktails
import com.resocoder.forecastmvvm.internal.NoConnectivityException

class CocktailNetworkDataSourceImpl(
    private val apiService: CocktailApiService
) : CocktailNetworkDataSource {

    private val _downloadCocktail = MutableLiveData<Cocktails>()

    override val downloadCocktail: LiveData<Cocktails>
        get() = _downloadCocktail

    override suspend fun fetchCocktails() {
        try {
            val fetchCocktails = apiService
                .getCocktail().await()
            _downloadCocktail.postValue(fetchCocktails)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.")
        }

    }
}