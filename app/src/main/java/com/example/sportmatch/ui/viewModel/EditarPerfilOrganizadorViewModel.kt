package com.example.sportmatch.ui.viewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
data class EstadoFormularioOrganizador(
    val nomeOrganizacao: String = "Arena Esportiva Central",
    val email: String = "contato@arena.com",
    val telefone: String = "82 99999-9999",
    val estado: String = "Alagoas",
    val cidade: String = "Arapiraca",
    val logradouro: String = "Rua",
    val numero: String = "123",
    val complemento: String = "",
    val fotoUrl: String = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdmgE_IJ28id-N_nQ4LEasRPh8wRGzdSQltA&s",
    val senha: String = "12345678",
    val cep: String = "57300-000",
    val cnpj: String = "12.345.678/0001-99",
)
class EditarPerfilOrganizadorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EstadoFormularioOrganizador())
    val uiState: StateFlow<EstadoFormularioOrganizador> = _uiState.asStateFlow()
    fun atualizarNome(novoNome: String) {
        _uiState.value = _uiState.value.copy(nomeOrganizacao = novoNome)
    }
    fun atualizarEmail(novoEmail: String) {
        _uiState.value = _uiState.value.copy(email = novoEmail)
    }
    fun atualizarTelefone(novoTelefone: String) {
        _uiState.value = _uiState.value.copy(telefone = novoTelefone)
    }
    fun atualizarEstado(novo: String) { _uiState.value = _uiState.value.copy(estado = novo) }
    fun atualizarCidade(novo: String) { _uiState.value = _uiState.value.copy(cidade = novo) }
    fun atualizarLogradouro(novo: String) { _uiState.value = _uiState.value.copy(logradouro = novo) }
    fun atualizarNumero(novo: String) { _uiState.value = _uiState.value.copy(numero = novo) }
    fun atualizarComplemento(novo: String) { _uiState.value = _uiState.value.copy(complemento = novo) }
    fun atualizarCnpj(novo: String) { _uiState.value = _uiState.value.copy(cnpj = novo) }
    fun atualizarCep(novo: String) { _uiState.value = _uiState.value.copy(cep = novo) }
    fun atualizarSenha(novo: String) { _uiState.value = _uiState.value.copy(senha = novo) }
    fun salvarDados() {
        println("Salvando dados: ${_uiState.value}")
    }
}