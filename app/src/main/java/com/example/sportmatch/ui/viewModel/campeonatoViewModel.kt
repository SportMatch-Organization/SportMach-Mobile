package com.example.sportmatch.model
import CompeticaoSupabase
import EspacoEsportivoSupabase
import EsportesData
import android.app.Application
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.sportmatch.data.database.SportMatchDatabase
import com.example.sportmatch.service.CompeticaoService
import com.example.sportmatch.service.EspacoEsportivoService
import io.github.jan.supabase.storage.storage
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class CampeonatoViewModel(application: Application) : AndroidViewModel(application) {

    var nome by mutableStateOf("competição de volei")
    var descricao by mutableStateOf("Para amigos")
    var esporteSelecionado by mutableStateOf("Futebol")
    var modalidadeSelecionada by mutableStateOf("Campo")
    var formatoSelecionado by mutableStateOf("Pontos corridos")
    var categoriaSelecionada by mutableStateOf("Misto")
    var acessibilidadeSelecionada by mutableStateOf("Futebol de 5 (deficiência visual)")
    var descricaoAcessibilidade by mutableStateOf("boa")
    var imagemUri by mutableStateOf<Uri?>(null)
        private set

    fun setImagem(uri: Uri?) {
        imagemUri = uri
    }
    var tipoSelecionado by mutableStateOf("Individual")
    var total by mutableStateOf("10")
    var minimoEquipe by mutableStateOf("4")
    var maximoEquipe by mutableStateOf("6")
    var faixaEtariaSelecionada by mutableStateOf("Qualquer idade")
    var idadeMinima by mutableStateOf("")
    var idadeMaxima by mutableStateOf("")
    var dataIniciaInscricao by mutableStateOf<LocalDateTime?>(LocalDateTime.of(2025, 10, 27, 9, 0))
    var dataFimInscricao by mutableStateOf<LocalDateTime?>(LocalDateTime.of(2025, 12, 27, 9, 0))
    var dataIniciaCompeticao by mutableStateOf<LocalDateTime?>(LocalDateTime.of(2025, 10, 27, 9, 0))
    var dataFimCompeticao by mutableStateOf<LocalDateTime?>(LocalDateTime.of(2025, 11, 27, 9, 0))
    var local by mutableStateOf("")

    var competicaoGratuita by mutableStateOf(true)
    var valor by mutableStateOf("")
    var formasDeDePagamentoSelecionado by mutableStateOf(listOf<String>())

    var opcoesEsportes = EsportesData.map { it.nome }
    val categorias = listOf("Masculino", "Feminino", "Misto")
    var locais by mutableStateOf(listOf<String>())
    val formasDePagamento = listOf("Pix", "Dinheiro", "Cartão de crédito", "Cartão de débito")

    val esporteAtual get() = EsportesData.find { it.nome == esporteSelecionado }
    val modalidades get() = esporteAtual?.modalidades ?: emptyList()
    val formatos get() = esporteAtual?.formatos ?: emptyList()
    val acessibilidades get() = esporteAtual?.tiposAcessibilidade ?: emptyList()

    var carregando by mutableStateOf(false)
    fun limparCampos() {
        modalidadeSelecionada = ""
        formatoSelecionado = ""
        categoriaSelecionada = ""
        acessibilidadeSelecionada = ""
    }

    val camposObrigatorios1: Boolean
        get() =
            nome.isNotBlank() &&
                    descricao.isNotBlank() &&
                    esporteSelecionado.isNotBlank() &&
                    modalidadeSelecionada.isNotBlank() &&
                    formatoSelecionado.isNotBlank() &&
                    categoriaSelecionada.isNotBlank() &&
                    (
                            acessibilidadeSelecionada.isBlank() ||
                                    (acessibilidadeSelecionada.isNotBlank() && descricaoAcessibilidade.isNotBlank())
                            ) &&
                    imagemUri !== null

    val camposObrigatorios2: Boolean
        get() {
            val inicioInscricao = dataIniciaInscricao
            val fimInscricao = dataFimInscricao
            val inicioCompeticao = dataIniciaCompeticao
            val fimCompeticao = dataFimCompeticao

            return tipoSelecionado.isNotBlank() &&
                    total.isNotBlank() &&
                    (
                            tipoSelecionado == "Individual" ||
                                    (minimoEquipe.isNotBlank() && maximoEquipe.isNotBlank())
                            ) &&
                    faixaEtariaSelecionada.isNotBlank() &&
                    (
                            faixaEtariaSelecionada == "Qualquer idade" ||
                                    (idadeMinima.isNotBlank() && idadeMaxima.isNotBlank())
                            ) &&
                    inicioInscricao != null &&
                    fimInscricao != null &&
                    fimInscricao.isAfter(inicioInscricao) &&
                    inicioCompeticao != null &&
                    fimCompeticao != null &&
                    fimCompeticao.isAfter(inicioCompeticao) &&
                    local.isNotEmpty() &&
                    (
                            competicaoGratuita ||
                                    (valor.isNotBlank() && formasDeDePagamentoSelecionado.isNotEmpty())
                            )
        }

    private val competicaoDao = SportMatchDatabase.getDatabase(application).competicaoDao()

    suspend fun uploadImagemCompeticao(uri: Uri): String? {
        return try {
            val context = getApplication<Application>()
            val inputStream = context.contentResolver.openInputStream(uri)
            if (inputStream == null) {
                return null
            }

            val bytes = inputStream.readBytes()
            val fileName = "${System.currentTimeMillis()}.jpg"
            val supabase = com.example.sportmatch.supabase.SupabaseClientInstance.client

            val result = supabase.storage.from("competicoes").upload(
                path = fileName,
                data = bytes
            )

            val url = supabase.storage.from("competicoes").publicUrl(fileName)

            return url

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    suspend fun salvarCompeticao(): Boolean {
        return try {
            val urlImagem = imagemUri?.let { uploadImagemCompeticao(it) } ?: ""

            val novaCompeticao = CompeticaoSupabase(
                id = null,
                status = "aberta",
                vagas_preenchidas = 0,
                nome = nome,
                descricao = descricao,
                esporte = esporteSelecionado,
                modalidade = modalidadeSelecionada,
                formato = formatoSelecionado,
                categoria = categoriaSelecionada,
                tipo_acessibilidade = acessibilidadeSelecionada,
                descricao_acessibilidade = descricaoAcessibilidade,
                tipo = tipoSelecionado,
                total = total.toIntOrNull() ?: 0,
                imagem_url = urlImagem,
                minimo_equipe = minimoEquipe?.toIntOrNull(),
                maximo_equipe = maximoEquipe?.toIntOrNull(),
                faixa_etaria = faixaEtariaSelecionada,
                idade_minima = idadeMinima?.toIntOrNull(),
                idade_maxima = idadeMaxima?.toIntOrNull(),
                inicio_inscricao = dataIniciaInscricao?.toString() ?: "",
                final_inscricao = dataFimInscricao?.toString() ?: "",
                inicio_competicao = dataIniciaCompeticao?.toString() ?: "",
                final_competicao = dataFimCompeticao?.toString() ?: "",
                local = local,
                gratuito = competicaoGratuita,
                valor = valor.toDoubleOrNull(),
                formas_pagamento = formasDeDePagamentoSelecionado.joinToString(",")
            )

            CompeticaoService.postCompeticao(novaCompeticao)

        } catch (e: Exception) {
            Log.e("COMPETICAO", "Erro ao cadastrar: ${e.message}")
            false
        }
    }




    var espacosEsportivos by mutableStateOf<List<EspacoEsportivoSupabase>>(emptyList())
        private set

    suspend fun buscarEspacosEsportivosSupabase() {
        carregando = true

        try {
            val lista = EspacoEsportivoService.getEspacosEsportivos()

            espacosEsportivos = lista
            locais = lista.map { it.nome }

        } catch (e: Exception) {
            Log.e("SUPABASE", "Erro ao buscar: ${e.message}")
        } finally {
            carregando = false
        }
    }

}
