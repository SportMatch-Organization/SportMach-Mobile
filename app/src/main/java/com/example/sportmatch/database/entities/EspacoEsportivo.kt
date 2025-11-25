package com.example.sportmatch.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "espaco-esportivo")
data class EspacoEsportivo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String = "",
    val descricao: String = "",
    val tipoEspaco: String = "",
    val endereco: String = "",
    val telefone: String = "",
    @ColumnInfo(name = "maximo_atletas") val maximoAtletas: String = "",
    @ColumnInfo(name = "esportes_suportados") val esportesSuportados: String = "",
    @ColumnInfo(name = "nivel_acessibilidade") val nivelAcessibilidade: String = "",
    val recursos: String = "",
    @ColumnInfo(name = "imagem_url") val imagemUrl: String = "",
)