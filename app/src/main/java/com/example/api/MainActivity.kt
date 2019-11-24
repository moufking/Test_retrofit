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

    private val BaseUrl = "https://www.thecocktaildb.com/api/json/v1/1/"
    val andVersionArray = arrayOf(Drink("Banana","Mouffi", "ioio"), Drink("Banana","Donner 2", "dfsnvdfs"))

    //val andVersionArray = mutableListOf<Drink>()

    private lateinit var linearLayoutManager: LinearLayoutManager


    // https://www.thecocktaildb.com/api/json/v1/1/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       list.layoutManager = LinearLayoutManager(this)
        list.adapter = DrinkAdapter(andVersionArray)

        //use retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ITanApi::class.java)
        val tanStops = service.getTanStops()

        click.setOnClickListener {

            tanStops.enqueue(object : Callback<List<Drinks>> {

                override fun onResponse(call: Call<List<Drinks>>, response: Response<List<Drinks>>) {

                    val allCourse = response.body()
                    Log.d("tatat", response.body().toString())
                    if (allCourse != null) {

                        for (c in allCourse) {
                            Log.d("toto", "qlo")
                        }
                        message.update(response.body().toString())
                    }
                    message.update(response.body().toString())
                }
                override fun onFailure(call: Call<List<Drinks>>, t: Throwable) {
                    //Log.e("TAN", "Error : $t")
                    message.update("Erreur")
                }

            })
        }

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
