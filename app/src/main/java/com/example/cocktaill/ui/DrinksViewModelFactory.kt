package com.example.cocktaill.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cocktaill.data.repository.CocktailsRepository

/**
 * Created by Shahar.A. on 26/02/2019.
 */

class DrinksViewModelFactory(
    private val cocktailsRepository: CocktailsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DrinksViewModel(cocktailsRepository) as T
    }
}