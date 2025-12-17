package com.example.sportmatch.data.database.entities

data class CompeticaoDetalhesUiModel(
    val entity: Competicao,
    val estaInscrito: Boolean = false
) {
    val vagasDisponiveis: Int
        get() = entity.total - entity.vagasPreenchidas
    val precoFormatado: String
        get() = if (entity.gratuito) "Grátis" else "R$ ${String.format("%.2f", entity.valor ?: 0.0)}"
    val titulo: String = entity.nome
    val maximoAtletas: Int = entity.maximoEquipe ?: 1
}