package com.example.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

public interface CocktailService {
    object Factory {
        private val BaseUrl = "https://thecocktaildb.com/api/json/v1/1/"
        fun create(): DrinkService {

            val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            return retrofit.create(DrinkService::class.java)
        }
    }
}