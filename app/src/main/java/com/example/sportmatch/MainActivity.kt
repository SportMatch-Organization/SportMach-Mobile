package com.example.sportmatch


import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportmatch.model.CadastroViewModel
import com.example.sportmatch.model.EnderecoUsuarioViewModel
import com.example.sportmatch.model.CampeonatoViewModel
import com.example.sportmatch.model.Evento
import com.example.sportmatch.ui.screens.competicoes.CadastroCompeticao
import com.example.sportmatch.ui.Login
import com.example.sportmatch.ui.Home
import com.example.sportmatch.ui.cadastro.Cadastro3
import com.example.sportmatch.ui.competicoes.CadastroCompeticao2
import com.example.sportmatch.ui.competicoes.CadastroCompeticao3
import com.example.sportmatch.ui.DetalhesEventoActivity
import com.example.sportmatch.ui.screens.cadastro.Cadastro1
import com.example.sportmatch.ui.screens.cadastro.Cadastro2
import com.example.sportmatch.ui.screens.cadastro.Cadastro4
import com.example.sportmatch.ui.screens.cadastro.Cadastro5
import com.example.sportmatch.ui.screens.cadastro.Cadastro6
import com.example.sportmatch.ui.screens.competicoes.pesquisar.PerfilUsuario
import com.example.sportmatch.ui.screens.competicoes.pesquisar.Pesquisar
import com.example.sportmatch.ui.screens.espacosEsportivo.CadastroEspacoEsportivo
import com.example.sportmatch.ui.screens.patrocinadores.TelaCadastro
import com.example.sportmatch.ui.theme.SportmatchTheme
import com.example.sportmatch.ui.viewModel.EspacoEsportivoViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ====================================================================
        // === CÓDIGO DE TESTE: INICIAR A TELA DETALHES EVENTO (XML)          ===
        // ====================================================================

        val urlsDeTeste = listOf(
            // Usa o placeholder existente como a primeira imagem
            "drawable://placeholder_volei",
            // Usa caminhos genéricos para simular outras imagens que serão carregadas como cor
            "url://imagem_2",
            "url://imagem_3"
        )

        val eventoTeste = Evento(
            titulo = "Vôlei de areia Pajuçara",
            preco = 20.00,
            esporte = "Vôlei",
            modalidade = "Vôlei de praia",
            categoria = "Misto",
            subcategoria = "sub-17",
            formatoCompeticao = "Sub-17",
            nivelAcessibilidade = "Deficiência visual",
            maxAtletas = 4,
            minAtletas = 2,
            dataInicioInscricoes = "10/11/2025",
            dataFimInscricoes = "20/11/2025",
            inicioCompeticao = "24/11/2025 - 15:00",
            fimCompeticao = "24/11/2025 - 17:00",
            nomeLocal = "Praia da Pajuçara",
            urlsImagens = urlsDeTeste // Passa a lista de URLs
        )

        val intent = Intent(this, DetalhesEventoActivity::class.java)
        intent.putExtra("evento_detalhes", eventoTeste)
        startActivity(intent)
        finish()

        // ====================================================================
        // === FIM DO CÓDIGO DE TESTE (Retorna o fluxo Compose depois)        ===
        // ====================================================================

        enableEdgeToEdge()

        setContent {
            SportmatchTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val cadastroViewModel: CadastroViewModel = viewModel()
    val enderecoUsuarioViewModel: EnderecoUsuarioViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {
            Login(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToCadastro = {
                    navController.navigate("cadastro1")
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("cadastro1") {
            Cadastro1 (
                viewModel = cadastroViewModel,
                onNavigateToCadastro2 = {
                    navController.navigate("cadastro2")
                }
            )
        }

        composable("cadastro2"){
            Cadastro2(
                viewModel = cadastroViewModel,
                onNavigateToCadastro3 = {
                    navController.navigate("cadastro3")
                }
            )
        }

        composable("cadastro3"){
            Cadastro3 (
                viewModel = cadastroViewModel,
                onNavigateToCadastro4 = {
                    navController.navigate("cadastro4")
                }
            )
        }

        composable("cadastro4"){
            Cadastro4(
                viewModel = cadastroViewModel,
                enderecoUsuarioViewModel = enderecoUsuarioViewModel,
                onNavigateToCadastro5 = {
                    navController.navigate("cadastro5")
                }
            )
        }

        composable("cadastro5"){
            Cadastro5(
                viewModel = cadastroViewModel,
                enderecoUsuarioViewModel = enderecoUsuarioViewModel,
                onNavigateToCadastro6 = {
                    navController.navigate("cadastro6")
                }
            )
        }

        composable("cadastro6"){
            Cadastro6(
                viewModel = cadastroViewModel,
                onNavigateToLogin = {
                    navController.navigate("login")
                }
            )
        }

        composable("home") {
            Home()
        }

        composable("cadastro-competicao") { backStackEntry ->
            val campeonatoViewModel: CampeonatoViewModel = viewModel(backStackEntry)
            CadastroCompeticao(
                viewModel = campeonatoViewModel,
                onNext = { navController.navigate("cadastro-competicao2") }
            )
        }

        composable("cadastro-competicao2") { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("cadastro-competicao")
            }
            val campeonatoViewModel: CampeonatoViewModel = viewModel(parentEntry)
            CadastroCompeticao2(
                viewModel = campeonatoViewModel,
                onNext = { navController.navigate("cadastro-competicao3") },
                onBefore = { navController.popBackStack() }
            )
        }

        composable("cadastro-competicao3") { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("cadastro-competicao")
            }
            val campeonatoViewModel: CampeonatoViewModel = viewModel(parentEntry)
            CadastroCompeticao3(
                viewModel = campeonatoViewModel,
                onBefore = { navController.popBackStack() }
            )
        }


        composable("perfil_usuario") {
            PerfilUsuario(
                onNavigateBack = {
                    navController.navigate("login") {
                        popUpTo("perfil_usuario") { inclusive = true }
                    }
                },
                onOnboardingComplete = {
                    navController.navigate("home") {
                        popUpTo("perfil_usuario") { inclusive = true }
                    }
                }
            )
        }
        composable("pesquisar") {
            Pesquisar(navController = navController)
        }
        composable("cadastro-espaco-esportivo") {
            val espacoEsportivoViewModel: EspacoEsportivoViewModel = viewModel()
            CadastroEspacoEsportivo(
                viewModel = espacoEsportivoViewModel,
                onBefore = { navController.popBackStack() }
            )
        }
        composable("cadastro-patrocinador") {
            TelaCadastro(
                onVoltar = {},
                onVerCadastrados = {}
            )
        }
    }
}