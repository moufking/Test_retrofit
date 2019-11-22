package com.example.api

import retrofit2.Call
import retrofit2.http.GET

interface ITanApi {

    @GET("/filter.php?c=Cocktail")
    fun getTanStops(): Call<List<Drink>>
}