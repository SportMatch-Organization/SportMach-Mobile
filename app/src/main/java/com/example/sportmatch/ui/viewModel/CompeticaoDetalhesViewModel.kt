package com.example.sportmatch.ui.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.sportmatch.data.database.entities.Competicao
import com.example.sportmatch.data.database.entities.CompeticaoDetalhesUiModel

data class DetalhesUiState(
    val competicao: CompeticaoDetalhesUiModel? = null,
    val isLoading: Boolean = true
)
class CompeticaoDetalhesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DetalhesUiState())
    val uiState = _uiState.asStateFlow()

    fun carregarCompeticao(competicaoId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val entitySimulada = Competicao(
                id = competicaoId,
                nome = "Vôlei de areia Pajuçara",
                esporte = "Vôlei",
                modalidade = "Vôlei de praia",
                categoria = "Misto",
                formato = "Sub-17",
                tipoAcessibilidade = "Deficiência visual",
                maximoEquipe = 2,
                total = 20,
                vagasPreenchidas = 2,
                inicioCompeticao = "24/11/2025 - 15:00",
                finalCompeticao = "24/11/2025 - 17:00",
                local = "Quadra do sagrada família",
                valor = 20.00,
                inicioInscricao = "10/11/2025",
                finalInscricao = "20/11/2025"
            )
            val uiModel = CompeticaoDetalhesUiModel(entity = entitySimulada, estaInscrito = false)
            _uiState.update { it.copy(competicao = uiModel, isLoading = false) }
        }
    }
    fun realizarInscricao() {
        _uiState.update { currentState ->
            val modelAtual = currentState.competicao ?: return@update currentState

            if (!modelAtual.estaInscrito && modelAtual.vagasDisponiveis > 0) {
                val novaEntity = modelAtual.entity.copy(
                    vagasPreenchidas = modelAtual.entity.vagasPreenchidas + 1
                )
                val novoModel = modelAtual.copy(
                    entity = novaEntity,
                    estaInscrito = true
                )
                currentState.copy(competicao = novoModel)
            } else {
                currentState
            }
        }
    }
}