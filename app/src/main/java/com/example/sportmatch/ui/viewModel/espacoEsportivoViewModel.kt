package com.example.sportmatch.ui.viewModel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportmatch.database.SportMatchDatabase
import com.example.sportmatch.database.entities.Competicao
import com.example.sportmatch.database.entities.EspacoEsportivo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class EspacoEsportivoViewModel(application: Application) : AndroidViewModel(application) {
    var nome by mutableStateOf("")
    var descricao by mutableStateOf("")
    var tipoSelecionado by mutableStateOf("")
    var endereco by mutableStateOf("")
    var telefone by mutableStateOf("")
    var maximoAtletas by mutableStateOf("")
    var esportesSuportadosSelecionados by mutableStateOf(listOf<String>())
    var nivelAcessibilidadeSelecionadas by mutableStateOf(listOf<String>())
    var recursosSelecionados by mutableStateOf(listOf<String>())

    val tiposEspaco = listOf(
        "Academia de musculação",
        "Box de crossfit",
        "Campo de futebol",
        "Campo de futebol society",
        "Piscina olímpica",
        "Pista de atletismo",
        "Pista de skate",
        "Quadra de basquete",
        "Quadra de futsal",
        "Quadra de padel",
        "Quadra de tênis",
        "Quadra de vôlei",
        "Quadra de vôlei de areia",
        "Quadra poliesportiva",
        "Tatame / dojô")

    val esportesSuportados = listOf(
        "Atletismo",
        "Basquete",
        "Beach tennis",
        "Boxe",
        "Capoeira",
        "Ciclismo",
        "Corrida",
        "Futebol",
        "Futsal",
        "Handebol",
        "Jiu-jitsu",
        "Judô",
        "Karatê",
        "MMA",
        "Muay Thai",
        "Natação",
        "Skate",
        "Surfe",
        "Taekwondo",
        "Tênis",
        "Tênis de mesa",
        "Triatlo",
        "Vôlei",
        "Vôlei de praia")

    val niveisAcessibildade = listOf(
        "Nenhum",
        "Acesso permitido a cão-guia",
        "Área de circulação ampla e livre de obstáculos",
        "Área de descanso acessível",
        "Área para cadeiras de rodas",
        "Assentos reservados para PCD",
        "Atendimento acessível (equipe treinada)",
        "Avisos sonoros e visuais",
        "Banheiro adaptado",
        "Bebedouro em altura acessível",
        "Calçada acessível",
        "Cadeira de rodas disponível sob demanda",
        "Corrimãos nas escadas e rampas",
        "Elevador ou plataforma elevatória",
        "Espaço para cadeiras de rodas",
        "Estacionamento com vagas para PCD",
        "Iluminação adequada",
        "Mapa tátil",
        "Pia e espelhos em altura acessível",
        "Piso nivelado nas arquibancadas",
        "Piso tátil direcional e de alerta",
        "Placas com alto contraste e pictogramas",
        "Portas largas (mínimo 80 cm)",
        "Sinalização em braile e alto contraste",
        "Sistema de legenda ou Libras em eventos",
        "Tomadas e interruptores em altura adaptada",
        "Vestiário adaptado")

    val recursos = listOf(
        "Nenhum",
        "Aluguel de bolas e equipamentos",
        "Área de aquecimento ou alongamento",
        "Área de churrasco ou confraternização",
        "Área de descanso com bancos ou sombra",
        "Área para espectadores coberta",
        "Armários (lockers)",
        "Banheiros",
        "Banheiros com chuveiro",
        "Bar ou lanchonete",
        "Bebedouro",
        "Cadeiras para espectadores",
        "Cantina ou quiosque",
        "Chuveiros",
        "Cobertura (quadra coberta ou parcialmente coberta)",
        "Estacionamento",
        "Estacionamento para bicicletas",
        "Iluminação noturna",
        "Internet Wi-Fi",
        "Marcação oficial (linhas e medidas regulamentares)",
        "Mesas e cadeiras de apoio",
        "Placar eletrônico",
        "Quadra ou campo com piso profissional",
        "Rede de proteção (em quadras e campos)",
        "Sala de primeiros socorros",
        "Sala de treino ou academia de apoio",
        "Segurança no local",
        "Sistema de som para eventos",
        "Som ambiente",
        "Tomadas e energia elétrica disponíveis",
        "Vestiários masculinos e femininos"
    )

    val camposObrigatorios: Boolean
        get(){
            return nome.isNotBlank() && descricao.isNotBlank() && tiposEspaco.isNotEmpty() && endereco.isNotBlank() && telefone.isNotBlank() && maximoAtletas.isNotBlank() && esportesSuportadosSelecionados.isNotEmpty() && nivelAcessibilidadeSelecionadas.isNotEmpty() && recursosSelecionados.isNotEmpty()
        }

    private val espacoEsportivoDao = SportMatchDatabase.getDatabase(application).espacoEsportivoDao()

    fun salvarEspacoEsportivo() {
        viewModelScope.launch(Dispatchers.IO) {
            val novoEspacoEsportivo = EspacoEsportivo(
                        nome = nome,
                        descricao = descricao,
                        tipoEspaco = tipoSelecionado,
                        endereco = endereco,
                        telefone = telefone,
                maximoAtletas = maximoAtletas,
                        esportesSuportados = esportesSuportados.joinToString(", "),
                        nivelAcessibilidade = nivelAcessibilidadeSelecionadas.joinToString(", "),
                        recursos = recursos.joinToString(", ")

            )
            espacoEsportivoDao.insert(novoEspacoEsportivo)
        }
    }
}