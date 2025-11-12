package com.example.sportmatch.ui.viewModel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

@RequiresApi(Build.VERSION_CODES.O)
class EspacoEsportivoViewModel(application: Application) : AndroidViewModel(application) {
    var nome by mutableStateOf("Quadra do Cebase")
    var descricao by mutableStateOf("Quadra com as marcações visíveis")
    var tipoSelecionado by mutableStateOf("Quadra")
    var endereco by mutableStateOf("Rua Josué Messias, Povoado Pau darco, Arapiraca, Alagoas")
    var telefone by mutableStateOf("333334332")
    var maximoAtletas by mutableStateOf("2")
    var esportesSuportadosSelecionados by mutableStateOf(listOf<String>())

    var nivelAcessibilidadeSelecionadas = mutableListOf<String>()
    var recursosSelecionados = mutableListOf<String>()

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
}