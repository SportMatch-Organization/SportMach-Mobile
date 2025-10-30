import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CampeonatoViewModel : ViewModel() {
    // Campos de texto
    var nome by mutableStateOf("")
    var descricao by mutableStateOf("")
    var descricaoAcessibilidade by mutableStateOf("")

    var esporteSelecionado by mutableStateOf("")
    var modalidadeSelecionada by mutableStateOf("")
    var formatoSelecionado by mutableStateOf("")
    var acessibilidadeSelecionada by mutableStateOf("")
    var categoriaSelecionada by mutableStateOf("")

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
}
