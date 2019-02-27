package com.example.cocktaill

import android.app.Application
import com.example.cocktaill.data.repository.CocktailsRepository
import com.example.cocktaill.data.repository.CocktailsRepositoryImpl
import com.example.cocktaill.data.source.local.CocktailRoomDatabase
import com.example.cocktaill.data.source.remote.*
import com.example.cocktaill.ui.DrinksViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class CocktailsApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@CocktailsApplication))

        bind() from singleton { CocktailRoomDatabase.getInstance(instance()) }
        bind() from singleton { instance<CocktailRoomDatabase>().cocktailDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { CocktailApiService(instance()) }
        bind<CocktailNetworkDataSource>() with singleton { CocktailNetworkDataSourceImpl(instance()) }
        bind<CocktailsRepository>() with singleton { CocktailsRepositoryImpl(instance(), instance()) }
        bind() from provider { DrinksViewModelFactory(instance()) }


    }

}