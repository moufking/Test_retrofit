package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    val andVersionArray = arrayOf(Drink("Banana","Mouffi", "ioio"), Drink("Banana","Donner 2", "dfsnvdfs"))

    private lateinit var linearLayoutManager: LinearLayoutManager


    // https://www.thecocktaildb.com/api/json/v1/1/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       list.layoutManager = LinearLayoutManager(this)
        //list.adapter = DrinkAdapter(andVersionArray)

        val service =  CocktailService.Factory.create()
        val list_cocktails = service.getCocktails()

        //click.setOnClickListener {

            list_cocktails.enqueue(object : Callback<Drinks> {

                override fun onResponse(call: Call<Drinks>, response: Response<Drinks>) {

                    val allCourse = response.body()

                   // message.update (allCourse!!.drinks.toString())
                    list.adapter = DrinkAdapter(allCourse!!.drinks)
                }
                override fun onFailure(call: Call<Drinks>, t: Throwable) {
                    Log.e("TAN", "Error : $t")
                    //message.update("Erreur")
                }

            })
        //}

    }

    public fun TextView.update(vao: Any) {
        this.text = vao.toString()
    }

    public fun seedItems() {

        var nameArray = resources.getStringArray(R.array.name)
        for (i in 0..(nameArray.size-1))
            andVersionArray[i] = Drink(nameArray[i], nameArray[i],nameArray[i])
    }


}
