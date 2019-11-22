package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private val BaseUrl = "https://www.thecocktaildb.com/api/json/v1/1/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ITanApi::class.java)
        val tanStops = service.getTanStops()

        click.setOnClickListener {


            tanStops.enqueue(object : Callback<List<Drink>> {

                override fun onResponse(call: Call<List<Drink>>, response: Response<List<Drink>>) {

                    val allCourse = response.body()
                    Log.d("tatat", response.body().toString())
                    if (allCourse != null) {

                        for (c in allCourse) {
                            Log.d("toto", "qlo")

                        }
                        message.update("pas vde");
                    }
                    message.update(response.body().toString())


                }


                override fun onFailure(call: Call<List<Drink>>, t: Throwable) {
                    //Log.e("TAN", "Error : $t")

                    message.update("Erreur")


                }

            })

        }


    }

    public fun TextView.update(vao: Any) {
        this.text = vao.toString()
    }
}
