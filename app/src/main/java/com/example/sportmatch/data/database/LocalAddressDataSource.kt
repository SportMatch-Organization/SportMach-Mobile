package com.example.sportmatch.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.sportmatch.data.database.entities.user.Endereco

@Dao
interface LocalAddressDataSource {

    @Upsert
    suspend fun adicionarEndereco(endereco: Endereco)

    @Query("SELECT * FROM user_address")
    suspend fun buscarEnderecoPorCep(cep: String): Endereco?

}