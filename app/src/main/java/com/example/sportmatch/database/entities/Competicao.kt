package com.example.sportmatch.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "competicoes_table") // O nome da nossa nova tabela no SQL
data class Competicao(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val titulo: String,
    val status: String, // "Abertos", "Em andamento", "Encerrados"
    val esporte: String, // "Vôlei", "Futebol"
    val modo: String, // "Misto"
    val taxa: Double,
    val data: String, // "16/11/2025"
    val horario: String, // "15:00 - 17:00"
    val local: String, // "Quadra do sagrada familia"
    val vagasPreenchidas: Int,
    val vagasTotais: Int,
    val imagemUrl: String // Um link ou placeholder
)