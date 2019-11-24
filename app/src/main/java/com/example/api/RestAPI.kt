package com.example.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Call

public interface CocktailService {

    @GET("/courses")
    fun getTanStops(): Call<List<Drink>>


    object Factory {
        private val BaseUrl = "https://www.thecocktaildb.com/api/json/v1/"
        fun create(): CocktailService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BaseUrl)
                .build()

            return retrofit.create(CocktailService::class.java)
        }
    }
}