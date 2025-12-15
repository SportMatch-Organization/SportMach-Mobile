package com.example.sportmatch.data.database.entities.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user_address")
data class Endereco (
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var cep: String = "",
    var logradouro: String = "",
    var complemento: String ="",
    var unidade: String = "",
    var bairro: String = "",
    var localidade: String = "",
    var uf: String = "",
    var estado: String = "",
    var regiao: String = "",
    var ibge: String = "",
    var gia: String = "",
    var ddd: String = "",
    var siafi: String = ""
)