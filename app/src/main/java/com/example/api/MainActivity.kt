package com.example.api

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.common.Common
import com.example.api.interfaces.RetrofitServices
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class MainActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyRecyclerAdapter
    lateinit var recycler : RecyclerView
    val LON = "30.2964015"
    val LAT = "54.2021736"
    val KEY = "4518e07f5c7de659bd2a9f39cee1c8d5"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.r_view)
        mService = Common.retrofitService
        recycler.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        getWeather()
    }

    private fun getWeather() {
        mService.getWeather(LAT, LON, KEY).enqueue(object : Callback<Wrapper> {


            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                System.out.println("failed")
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                adapter = MyRecyclerAdapter()
                recycler.adapter = adapter
                val wrapper = response.body() as Wrapper
                adapter.submitList((wrapper.list))
            }
        })
    }
}

data class Wrapper (val list: MutableList<Weather>)
data class Weather (val dt_txt: String, val main: Temp)
data class Temp (val temp: Float)
