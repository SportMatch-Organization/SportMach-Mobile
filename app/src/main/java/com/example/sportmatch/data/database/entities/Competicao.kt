package com.example.sportmatch.data.database.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "competicao")
data class Competicao(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val status: String = "",
    val vagasPreenchidas: Int = 0,
    val nome: String = "",
    val descricao: String = "",
    val esporte: String = "",
    val modalidade: String = "",
    val formato: String = "",
    val categoria: String = "",
    @ColumnInfo(name = "tipo_acessibilidade") val tipoAcessibilidade: String = "",
    @ColumnInfo(name = "descricao_acessibilidade") val descricaoAcessibilidade: String = "",
    val tipo: String = "",
    val total: Int = 0,
    val imagemUrl: String = "",
    @ColumnInfo(name = "minimo_equipe") val minimoEquipe: Int? = null,
    @ColumnInfo(name = "maximo_equipe") val maximoEquipe: Int? = null,
    @ColumnInfo(name = "faixa_etaria") val faixaEtaria: String = "",
    @ColumnInfo(name = "idade_minima") val idadeMinima: Int? = null,
    @ColumnInfo(name = "idade_maxima") val idadeMaxima: Int? = null,
    @ColumnInfo(name = "inicio_inscricao") val inicioInscricao: String = "",
    @ColumnInfo(name = "final_inscricao") val finalInscricao: String = "",
    @ColumnInfo(name = "inicio_competicao") val inicioCompeticao: String = "",
    @ColumnInfo(name = "final_competicao") val finalCompeticao: String = "",
    val local: String = "",
    val gratuito: Boolean = false,
    val valor: Double? = null,
    @ColumnInfo(name = "formas_pagamento") val formasPagamento: String = ""
)