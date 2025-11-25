package com.example.sportmatch.ui.screens.competicoes.pesquisar
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportmatch.database.dao.CompeticaoDao
import com.example.sportmatch.database.entities.Competicao
import com.example.sportmatch.database.SportMatchDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
data class PesquisarUiState(
    val textoBusca: String = "",
    val selectedTabIndex: Int = 0,
    val isSearchEmpty: Boolean = true,
    val competicoesAbertas: List<Competicao> = emptyList(),
    val competicoesEmAndamento: List<Competicao> = emptyList(),
    val competicoesEncerradas: List<Competicao> = emptyList(),
    val isLoading: Boolean = true,
    val isSearching: Boolean = false
)
class PesquisarViewModel(application: Application) : AndroidViewModel(application) {
    private val competicaoDao: CompeticaoDao =
        SportMatchDatabase.getDatabase(application).competicaoDao()
    private val _uiState = MutableStateFlow(PesquisarUiState())
    val uiState: StateFlow<PesquisarUiState> = _uiState.asStateFlow()
    private var _todasCompeticoes: List<Competicao> = emptyList()
    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            _todasCompeticoes = competicaoDao.getTodasCompeticoes()
            if (_todasCompeticoes.isEmpty()) {
                adicionarDadosDeTeste()
                _todasCompeticoes = competicaoDao.getTodasCompeticoes()
            }
            _uiState.update { it.copy(isLoading = false) }
            _refiltrarListas()
        }
    }
    fun onSearchTextChanged(novoTexto: String) {
        _uiState.update { it.copy(textoBusca = novoTexto) }
        _refiltrarListas()
    }
    fun onTabChanged(novoIndex: Int) {
        _uiState.update { it.copy(selectedTabIndex = novoIndex) }
    }
    fun onStartSearch() {
        _uiState.update { it.copy(isSearching = true) }
    }
    fun onStopSearch() {
        _uiState.update { it.copy(isSearching = false, textoBusca = "") }
        _refiltrarListas()
    }
    private fun _refiltrarListas() {
        val textoBuscaAtual = _uiState.value.textoBusca
        if (textoBuscaAtual.isBlank()) {
            _uiState.update {
                it.copy(
                    isSearchEmpty = true,
                    competicoesAbertas = emptyList(),
                    competicoesEmAndamento = emptyList(),
                    competicoesEncerradas = emptyList()
                )
            }
        } else {
            val filtradas = _todasCompeticoes.filter {
                it.nome.contains(textoBuscaAtual, ignoreCase = true)
            }
            _uiState.update {
                it.copy(
                    isSearchEmpty = false,
                    competicoesAbertas = filtradas.filter { c -> c.status.equals("Abertos", ignoreCase = true) },
                    competicoesEmAndamento = filtradas.filter { c -> c.status.equals("Em andamento", ignoreCase = true) },
                    competicoesEncerradas = filtradas.filter { c -> c.status.equals("Encerrados", ignoreCase = true) }
                )
            }
        }
    }
    private suspend fun adicionarDadosDeTeste() {
        Log.d("PesquisarViewModel", "Adicionando dados de teste ao banco...")
        competicaoDao.insert(Competicao(nome="Campeonato Aravôlei", status="Abertos", esporte="Vôlei", tipo="Misto", valor=25.90, inicioCompeticao="16/11/2025", local="Quadra do sagrada familia", vagasPreenchidas=2, total=33, imagemUrl="placeholder"))
        competicaoDao.insert(Competicao(nome="Campeonato Amador", status="Abertos", esporte="Vôlei", tipo="Misto", valor=20.00, inicioCompeticao="16/11/2025", local="Quadra do sagrada familia", vagasPreenchidas=5, total=20, imagemUrl="placeholder"))
        competicaoDao.insert(Competicao(nome="Basquete de Rua", status="Abertos", esporte="Basquete", tipo="Masculino", valor=10.00, inicioCompeticao="20/11/2025", local="Parque Central", vagasPreenchidas=3, total=10, imagemUrl="placeholder"))
        competicaoDao.insert(Competicao(nome="Campeonato Arriba", status="Em andamento", esporte="Vôlei", tipo="Misto", valor=25.90, inicioCompeticao="16/11/2025", local="Quadra do sagrada familia", vagasPreenchidas=10, total=15, imagemUrl="placeholder"))
        competicaoDao.insert(Competicao(nome="Campeonato Arau", status="Encerrados", esporte="Vôlei", tipo="Misto", valor=25.90, inicioCompeticao="16/11/2025", local="Quadra do sagrada familia", vagasPreenchidas=30, total=30, imagemUrl="placeholder"))
    }
}