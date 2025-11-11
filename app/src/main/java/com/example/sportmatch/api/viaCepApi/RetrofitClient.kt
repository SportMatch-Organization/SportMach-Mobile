package com.example.sportmatch.api.viaCepApi

import com.example.sportmatch.api.viaCepApi.ViaCepApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val viaCepApi: ViaCepApi = retrofit.create(ViaCepApi::class.java)
}