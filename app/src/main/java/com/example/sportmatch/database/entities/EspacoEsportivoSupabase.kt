import kotlinx.serialization.Serializable

@Serializable
data class EspacoEsportivoSupabase(
    val nome: String,
    val descricao: String,
    val tipo_espaco: String,
    val endereco: String,
    val telefone: String,
    val maximo_atletas: Int,
    val esportes_suportados: String,
    val nivel_acessibilidade: String,
    val recursos: String,
    val imagem_url: String
)
