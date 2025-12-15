package com.example.sportmatch.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patrocinadores")
data class Patrocinador(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val cnpj: String,
    val contato: String,
    val valor: String,
    val competicao: String
)
