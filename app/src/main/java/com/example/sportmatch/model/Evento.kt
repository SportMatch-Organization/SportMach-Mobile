package com.example.sportmatch.model

import java.io.Serializable

data class Evento(
    val titulo: String,
    val preco: Double,
    val esporte: String,
    val modalidade: String,
    val categoria: String,
    val subcategoria: String,
    val formatoCompeticao: String,
    val nivelAcessibilidade: String,
    val maxAtletas: Int,
    val minAtletas: Int,
    val dataInicioInscricoes: String,
    val dataFimInscricoes: String,
    val inicioCompeticao: String,
    val fimCompeticao: String,
    val nomeLocal: String? = null,

    // NOVO: Lista de URLs de imagens
    val urlsImagens: List<String> = emptyList()
) : Serializable