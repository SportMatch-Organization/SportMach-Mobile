package com.example.sportmatch.ui.viewModel
import androidx.lifecycle.ViewModel
import com.example.sportmatch.database.entities.Competicao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
data class ModeloOrganizador(
    val nome: String,
    val fotoUrl: String,
    val endereco: String,
    val nota: Double,
    val totalAvaliacoes: Int
)
class PerfilOrganizadorViewModel : ViewModel() {
    private val _estadoOrganizador = MutableStateFlow(
        ModeloOrganizador(
            nome = "Arena Esportiva Central",
            fotoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdmgE_IJ28id-N_nQ4LEasRPh8wRGzdSQltA&s",
            endereco = "Av.Agamenon Magalhães, 1024, Centro",
            nota = 4.8,
            totalAvaliacoes = 124
        )
    )
    val estadoOrganizador: StateFlow<ModeloOrganizador> = _estadoOrganizador.asStateFlow()
    private val _estadoCompeticoes = MutableStateFlow<List<Competicao>>(emptyList())
    val estadoCompeticoes: StateFlow<List<Competicao>> = _estadoCompeticoes.asStateFlow()

    init {
        carregarCompeticoes()
    }
    private fun carregarCompeticoes() {
        _estadoCompeticoes.value = listOf(
            Competicao(
                id = 1, nome = "Copa Aravôlei 2025", status = "Aberto", esporte = "Vôlei", tipo = "Misto", valor = 20.00, inicioCompeticao = "16/11/2025 - 15:00", local = "Quadra Sagrada Família", vagasPreenchidas = 2, total = 20, imagemUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwHN672FtLCRBMkBuHwUC9joInybedMIcplQ&s"
            ),
            Competicao(
                id = 2, nome = "Torneio Basquete 3x3", status = "Aberto", esporte = "Basquete", tipo = "Masculino", valor = 35.00, inicioCompeticao = "20/11/2025 - 09:00", local = "Ginásio Municipal", vagasPreenchidas = 10, total = 16, imagemUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwHN672FtLCRBMkBuHwUC9joInybedMIcplQ&s"
            ),
            Competicao(
                id = 3, nome = "Corrida Rústica", status = "Encerrado", esporte = "Corrida", tipo = "Geral", valor = 15.00, inicioCompeticao = "05/12/2025 - 06:00", local = "Parque da Cidade", vagasPreenchidas = 30, total = 30, imagemUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwHN672FtLCRBMkBuHwUC9joInybedMIcplQ&s"
            )
        )
    }
}