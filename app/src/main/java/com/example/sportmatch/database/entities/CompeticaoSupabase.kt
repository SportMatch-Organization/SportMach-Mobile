import kotlinx.serialization.Serializable

@Serializable
data class CompeticaoSupabase(
    val id: Int? = null,
    val status: String,
    val vagas_preenchidas: Int,
    val nome: String,
    val descricao: String,
    val esporte: String,
    val modalidade: String,
    val formato: String,
    val categoria: String,
    val tipo_acessibilidade: String,
    val descricao_acessibilidade: String,
    val tipo: String,
    val total: Int,
    val imagem_url: String,
    val minimo_equipe: Int? = null,
    val maximo_equipe: Int? = null,
    val faixa_etaria: String,
    val idade_minima: Int? = null,
    val idade_maxima: Int? = null,
    val inicio_inscricao: String,
    val final_inscricao: String,
    val inicio_competicao: String,
    val final_competicao: String,
    val local: String,
    val gratuito: Boolean,
    val valor: Double? = null,
    val formas_pagamento: String
)
