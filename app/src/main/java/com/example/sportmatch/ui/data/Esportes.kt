data class Esporte(
    val nome: String,
    val modalidades: List<String>,
    val formatos: List<String>,
    val tiposAcessibilidade: List<String>
)

val EsportesData = listOf(
    Esporte(
        nome = "Futebol",
        modalidades = listOf("Campo", "Society", "Futsal", "Praia"),
        formatos = listOf("Pontos corridos", "Mata-mata", "Fase de grupos + mata-mata"),
        tiposAcessibilidade = listOf("Futebol de 5 (deficiência visual)", "Futebol de 7 (paralisia cerebral)")
    ),
    Esporte(
        nome = "Vôlei",
        modalidades = listOf("Quadra", "Praia"),
        formatos = listOf("Melhor de sets", "Mata-mata", "Grupos classificatórios"),
        tiposAcessibilidade = listOf("Vôlei sentado")
    ),
    Esporte(
        nome = "Basquete",
        modalidades = listOf("Quadra", "3x3", "Streetball"),
        formatos = listOf("Fase de grupos", "Eliminatória simples", "Mata-mata"),
        tiposAcessibilidade = listOf("Basquete em cadeira de rodas")
    ),
    Esporte(
        nome = "Handebol",
        modalidades = listOf("Quadra", "Praia"),
        formatos = listOf("Fase de grupos + mata-mata", "Pontos corridos"),
        tiposAcessibilidade = listOf("Handebol em cadeira de rodas")
    ),
    Esporte(
        nome = "Tênis",
        modalidades = listOf("Quadra rápida", "Saibro", "Grama"),
        formatos = listOf("Eliminatória simples", "Melhor de sets"),
        tiposAcessibilidade = listOf("Tênis em cadeira de rodas")
    ),
    Esporte(
        nome = "Natação",
        modalidades = listOf("Piscina curta", "Piscina longa", "Águas abertas"),
        formatos = listOf("Provas individuais", "Revezamento", "Eliminatórias + finais"),
        tiposAcessibilidade = listOf("Natação paralímpica")
    ),
    Esporte(
        nome = "Atletismo",
        modalidades = listOf("Corridas", "Saltos", "Lançamentos", "Marcha atlética"),
        formatos = listOf("Classificatórias + finais", "Pontuação por equipe"),
        tiposAcessibilidade = listOf("Atletismo paralímpico", "Corridas com guia")
    ),
    Esporte(
        nome = "Judô",
        modalidades = listOf("Masculino", "Feminino"),
        formatos = listOf("Eliminatória simples", "Repescagem", "Disputa de medalhas"),
        tiposAcessibilidade = listOf("Judô paralímpico (deficiência visual)")
    ),
    Esporte(
        nome = "Jiu-Jitsu",
        modalidades = listOf("Tradicional", "No-Gi"),
        formatos = listOf("Eliminatória simples", "Pontos por vantagem"),
        tiposAcessibilidade = listOf("Jiu-Jitsu adaptado")
    ),
    Esporte(
        nome = "Capoeira",
        modalidades = listOf("Angola", "Regional", "Contemporânea"),
        formatos = listOf("Apresentação", "Competição por pontos"),
        tiposAcessibilidade = listOf("Capoeira inclusiva")
    ),
    Esporte(
        nome = "Ciclismo",
        modalidades = listOf("Estrada", "Pista", "Mountain Bike", "BMX"),
        formatos = listOf("Prova contra o tempo", "Circuito por voltas", "Etapas acumulativas"),
        tiposAcessibilidade = listOf("Ciclismo de mão", "Tandem (dupla com guia)")
    ),
    Esporte(
        nome = "Skate",
        modalidades = listOf("Street", "Park"),
        formatos = listOf("Baterias eliminatórias", "Final por pontuação"),
        tiposAcessibilidade = listOf("Skate adaptado")
    ),
    Esporte(
        nome = "Surf",
        modalidades = listOf("Shortboard", "Longboard", "Bodyboard"),
        formatos = listOf("Baterias eliminatórias", "Pontuação por manobras"),
        tiposAcessibilidade = listOf("Surf adaptado")
    ),
    Esporte(
        nome = "Corrida de Rua",
        modalidades = listOf("5 km", "10 km", "Meia maratona", "Maratona"),
        formatos = listOf("Cronometragem individual", "Largada por pelotões"),
        tiposAcessibilidade = listOf("Corrida com guia", "Categoria cadeirante")
    ),
    Esporte(
        nome = "Futebol Americano",
        modalidades = listOf("Full Pads", "Flag Football"),
        formatos = listOf("Pontos corridos", "Mata-mata"),
        tiposAcessibilidade = listOf("Flag adaptado")
    ),
    Esporte(
        nome = "Rugby",
        modalidades = listOf("Rugby XV", "Rugby 7"),
        formatos = listOf("Fase de grupos + mata-mata", "Pontos corridos"),
        tiposAcessibilidade = listOf("Rugby em cadeira de rodas")
    )
)
