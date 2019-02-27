package com.example.cocktaill.data.source.remote

import com.example.cocktaill.data.source.entity.Cocktails
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Shahar.A. on 19/02/2019.
 */

//
interface CocktailApiService {

    @GET("filter.php?a=Alcoholic")
    fun getCocktail(): Deferred<Cocktails>

    companion object {
        operator fun invoke(
            //dager
            connectivityInterceptor: ConnectivityInterceptor
        ): CocktailApiService {
//            val requestInterceptor = Interceptor { chain ->
//                val request = chain.request().newBuilder().url().build()
//                return@Interceptor chain.proceed(request)
//            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder().client(okHttpClient).baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CocktailApiService::class.java)
        }
    }


}