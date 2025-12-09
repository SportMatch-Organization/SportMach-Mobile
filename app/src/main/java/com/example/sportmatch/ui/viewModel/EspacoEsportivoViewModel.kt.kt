package com.example.sportmatch.ui.viewModel

import EspacoEsportivoSupabase
import android.app.Application
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.sportmatch.database.SportMatchDatabase
import com.example.sportmatch.database.entities.EspacoEsportivo
import com.example.sportmatch.service.EspacoEsportivoService
import com.example.sportmatch.supabase.SupabaseClientInstance
import io.github.jan.supabase.storage.storage

@RequiresApi(Build.VERSION_CODES.O)
class EspacoEsportivoViewModel(application: Application) : AndroidViewModel(application) {
    var nome by mutableStateOf("Cebase")
    var descricao by mutableStateOf("bom para todos")
    var tipoSelecionado by mutableStateOf("Quadra poliesportiva")
    var endereco by mutableStateOf("Arapiraca")
    var telefone by mutableStateOf("343436")
    var maximoAtletas by mutableStateOf("23")
    var esportesSuportadosSelecionados by mutableStateOf(listOf<String>("Futebol",
        "Futsal",
        "Handebol"))
    var nivelAcessibilidadeSelecionadas by mutableStateOf(listOf<String>("Nenhum"))
    var recursosSelecionados by mutableStateOf(listOf<String>("Nenhum"))

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
            return nome.isNotBlank() && descricao.isNotBlank() && tipoSelecionado.isNotEmpty() && endereco.isNotBlank() && telefone.isNotBlank() && maximoAtletas.isNotBlank() && esportesSuportadosSelecionados.isNotEmpty() && nivelAcessibilidadeSelecionadas.isNotEmpty() && recursosSelecionados.isNotEmpty() && imagemUri != null
        }

    var imagemUri by mutableStateOf<Uri?>(null)
        private set

    fun setImagem(uri: Uri?) {
        imagemUri = uri
    }
    private val espacoEsportivoDao = SportMatchDatabase.getDatabase(application).espacoEsportivoDao()

    suspend fun uploadImagemEspacosEsportivos(uri: Uri): String? {
        return try {
            val context = getApplication<Application>()
            val supabase = SupabaseClientInstance.client

            val bytes = context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
                ?: return null

            val fileName = "${System.currentTimeMillis()}.jpg"

            val result = supabase.storage.from("espacos-esportivos").upload(
                path = fileName,
                data = bytes,
                upsert = true,
            )

            if (result == null) {
                println("Erro ao fazer upload: retorno nulo")
                return null
            }

            return supabase.storage.from("espacos-esportivos").publicUrl(fileName)

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    suspend fun acionadorDeSalvarEspacoEsportivo() {
        val imagemUrl = imagemUri?.let { uploadImagemEspacosEsportivos(it) } ?: ""

        val espacoSupabase = EspacoEsportivoSupabase(
            nome = nome,
            descricao = descricao,
            tipo_espaco = tipoSelecionado,
            endereco = endereco,
            telefone = telefone,
            maximo_atletas = maximoAtletas.toIntOrNull() ?: 0,
            esportes_suportados = esportesSuportadosSelecionados.joinToString(", "),
            nivel_acessibilidade = nivelAcessibilidadeSelecionadas.joinToString(", "),
            recursos = recursosSelecionados.joinToString(", "),
            imagem_url = imagemUrl
        )

        salvarEspacoEsportivoSupabase(espacoSupabase)
    }

    suspend fun salvarEspacoEsportivo() {
        val urlImagem = imagemUri?.let { uploadImagemEspacosEsportivos(it) } ?: ""

        val novoEspacoEsportivo = EspacoEsportivo(
            nome = nome,
            descricao = descricao,
            tipoEspaco = tipoSelecionado,
            endereco = endereco,
            telefone = telefone,
            maximoAtletas = maximoAtletas,
            esportesSuportados = esportesSuportadosSelecionados.joinToString(", "),
            nivelAcessibilidade = nivelAcessibilidadeSelecionadas.joinToString(", "),
            recursos = recursosSelecionados.joinToString(", "),
            imagemUrl = urlImagem
        )

        espacoEsportivoDao.insert(novoEspacoEsportivo)
    }

    suspend fun salvarEspacoEsportivoSupabase(
        espaco: EspacoEsportivoSupabase
    ): Boolean {
        return EspacoEsportivoService.postEspacoEsportivo(espaco)
    }




}