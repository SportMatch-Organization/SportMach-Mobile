package com.example.sportmatch.data.api.viaCepApi

import com.example.sportmatch.data.database.entities.user.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteAdressApiDataSource {

    @GET("{cep}/json/")
    suspend fun buscarEnderecoPorCep(@Path("cep") cep:String ): Response<Endereco>
}