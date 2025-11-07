package com.example.sportmatch.api

import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApi {

    @GET("{cep}/json/")
    suspend fun buscarEnderecoPorCep(@Path("cep") cep:String )
}