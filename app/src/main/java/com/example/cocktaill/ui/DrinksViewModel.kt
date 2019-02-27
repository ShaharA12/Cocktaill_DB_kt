package com.example.cocktaill.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktaill.data.repository.CocktailsRepository
import com.example.cocktaill.data.source.remote.CocktailApiService
import com.example.cocktaill.data.source.entity.Cocktails
import com.example.cocktaill.data.source.remote.CocktailNetworkDataSourceImpl
import com.example.cocktaill.data.source.remote.ConnectivityInterceptorImpl
import com.example.cocktaill.internal.lazyDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DrinksViewModel(
    private val cocktailsRepository: CocktailsRepository
) : ViewModel() {

    val drinks by lazyDeferred { cocktailsRepository.getCocktails() }

}
