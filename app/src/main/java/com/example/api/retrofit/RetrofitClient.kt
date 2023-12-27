package com.example.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// object сочетает создание класса и его единичного экземпляра
object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

}