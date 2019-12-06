package com.example.api

import retrofit2.Call
import retrofit2.http.GET

interface DrinkService {

    @GET("filter.php?c=Cocktail")
    fun getCocktails(): Call<Drinks>
}