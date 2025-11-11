package com.example.sportmatch


import android.os.Build
import CampeonatoViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportmatch.model.CadastroViewModel
import com.example.sportmatch.ui.screens.competicoes.CadastroCompeticao
import com.example.sportmatch.ui.Login
import com.example.sportmatch.ui.Home
import com.example.sportmatch.ui.cadastro.Cadastro3
import com.example.sportmatch.ui.screens.cadastro.Cadastro1
import com.example.sportmatch.ui.screens.cadastro.Cadastro2
import com.example.sportmatch.ui.screens.cadastro.Cadastro4
import com.example.sportmatch.ui.screens.cadastro.Cadastro5
import com.example.sportmatch.ui.screens.cadastro.Cadastro6
import com.example.sportmatch.ui.screens.competicoes.pesquisar.PerfilUsuario
import com.example.sportmatch.ui.screens.competicoes.pesquisar.Pesquisar
import com.example.sportmatch.ui.theme.SportmatchTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    NavHost(
        navController = navController,
        startDestination = "cadastro4",
        modifier = modifier // aplica o padding aqui
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
                onNavigateToCadastro5 = {
                    navController.navigate("cadastro5")
                }
            )
        }

        composable("cadastro5"){
            Cadastro5(
                viewModel = cadastroViewModel,
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

        composable("cadastro-competicao") {
                backStackEntry ->
            val campeonatoViewModel: CampeonatoViewModel = viewModel(backStackEntry)
            CadastroCompeticao(
                viewModel = campeonatoViewModel,
                onNext = { /* ação de continuar */ }
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
            Pesquisar(navController = navController) // Chama a nova tela
        }
    }
}


