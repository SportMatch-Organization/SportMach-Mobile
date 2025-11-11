import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class CampeonatoViewModel : ViewModel() {
    var nome by mutableStateOf("competição de volei")
    var descricao by mutableStateOf("Para amigos")

    var esporteSelecionado by mutableStateOf("Futebol")
    var modalidadeSelecionada by mutableStateOf("Campo")
    var formatoSelecionado by mutableStateOf("Pontos corridos")
    var categoriaSelecionada by mutableStateOf("Misto")
    var acessibilidadeSelecionada by mutableStateOf("Futebol de 5 (deficiência visual)")
    var descricaoAcessibilidade by mutableStateOf("boa")
    var tipoSelecionado by mutableStateOf("Individual")
    var total by mutableStateOf("10")
    var minimoEquipe by mutableStateOf("4")
    var maximoEquipe by mutableStateOf("6")
    var faixaEtariaSelecionada by mutableStateOf("Qualquer idade")
    var idadeMinima by mutableStateOf("")
    var idadeMaxima by mutableStateOf("")
    var dataIniciaInscricao by  mutableStateOf<LocalDateTime?>(LocalDateTime.of(2025, 10, 27, 9, 0))
    var dataFimInscricao by  mutableStateOf<LocalDateTime?>(LocalDateTime.of(2025, 12, 27, 9, 0))
    var dataIniciaCompeticao by  mutableStateOf<LocalDateTime?>(LocalDateTime.of(2025, 10, 27, 9, 0))
    var dataFimCompeticao by  mutableStateOf<LocalDateTime?>(LocalDateTime.of(2025, 11, 27, 9, 0))
    var local by mutableStateOf("Quadra do Cebase")

    var competicaoGratuita by  mutableStateOf(true)
    var valor by  mutableStateOf("")
    var formasDeDePagamentoSelecionado by  mutableStateOf(listOf<String>())

    var opcoesEsportes = EsportesData.map { it.nome }
    val categorias = listOf("Masculino", "Feminino", "Misto")

    val esporteAtual
        get() = EsportesData.find { it.nome == esporteSelecionado }

    val modalidades
        get() = esporteAtual?.modalidades ?: emptyList()

    val formatos
        get() = esporteAtual?.formatos ?: emptyList()

    val acessibilidades
        get() = esporteAtual?.tiposAcessibilidade ?: emptyList()
    val locais = listOf("Quadra do sagrada família", "Quadra do Cebase", "Quadra do Arena CF")
    val formasDePagamento = listOf("Pix", "Dinheiro", "Cartão de crédito", "Cartão de débito")

    fun limparCampos(){
            modalidadeSelecionada = ""
            formatoSelecionado = ""
            categoriaSelecionada = ""
            acessibilidadeSelecionada = ""
        }

    val camposObrigatorios1:Boolean get() =
        nome.isNotBlank() &&
                descricao.isNotBlank() &&
                esporteSelecionado.isNotBlank() &&
                modalidadeSelecionada.isNotBlank() &&
                formatoSelecionado.isNotBlank() &&
                categoriaSelecionada.isNotBlank() &&
                (
                        acessibilidadeSelecionada.isBlank() ||
                                (acessibilidadeSelecionada.isNotBlank() && descricaoAcessibilidade.isNotBlank())
                        )


    val camposObrigatorios2: Boolean
        @RequiresApi(Build.VERSION_CODES.O)
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

}
