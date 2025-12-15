package com.example.sportmatch.data.repository.user

import com.example.sportmatch.data.api.viaCepApi.RemoteAdressApiDataSource
import com.example.sportmatch.data.database.LocalAddressDataSource
import com.example.sportmatch.data.database.entities.user.Endereco
import retrofit2.Response
import java.lang.Exception

class UserAddressRepository (
    private val localAddressDataSource: LocalAddressDataSource,
    private val remoteAddressApiDataSource: RemoteAdressApiDataSource
){
    suspend fun buscarEnderecoPorCep(cep: String): Endereco? {
        var endereco: Endereco? = Endereco()

        try {
            val response = remoteAddressApiDataSource.buscarEnderecoPorCep(cep)
            if (response.isSuccessful){
                endereco = response.body()
                localAddressDataSource.adicionarEndereco(endereco!!) //aqui se garante que o endereço é não-nulo
            }
        } catch (e: Exception){
            endereco = localAddressDataSource.buscarEnderecoPorCep(cep)
        }

        return endereco
    }
}