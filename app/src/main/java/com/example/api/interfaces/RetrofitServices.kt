package com.example.api.interfaces

import com.example.api.Wrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("forecast")
    fun getWeather(@Query("lat") lat : String?, @Query("lon") lon : String?, @Query("appid") key : String? ): Call<Wrapper>
}