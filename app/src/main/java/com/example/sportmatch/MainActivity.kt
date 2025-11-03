package com.example.sportmatch
import CampeonatoViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.sportmatch.ui.screens.competicoes.CadastroCompeticao
import com.example.sportmatch.ui.Login
import com.example.sportmatch.ui.Home
import com.example.sportmatch.ui.screens.cadastro.Cadastro1
import com.example.sportmatch.ui.screens.cadastro.Cadastro2
import com.example.sportmatch.ui.screens.cadastro.Cadastro3
import com.example.sportmatch.ui.screens.cadastro.Cadastro4
import com.example.sportmatch.ui.screens.cadastro.Cadastro5
import com.example.sportmatch.ui.screens.cadastro.Cadastro6
import com.example.sportmatch.ui.theme.SportmatchTheme

class MainActivity : ComponentActivity() {
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

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
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
                onCadastro = { userDto ->
                    navController.navigate("cadastro1")
                },
                onNavigateToCadastro2 = {
                    navController.navigate("cadastro2")
                }
            )
        }

        composable("cadastro2"){
            Cadastro2(
                onNavigateToCadastro3 = {
                    navController.navigate("cadastro3")
                }
            )
        }

        composable("cadastro3"){
            Cadastro3(
                onNavigateToCadastro4 = {
                    navController.navigate("cadastro4")
                }
            )
        }

        composable("cadastro4"){
            Cadastro4(
                onNavigateToCadastro5 = {
                    navController.navigate("cadastro5")
                }
            )
        }

        composable("cadastro5"){
            Cadastro5(
                onNavigateToCadastro6 = {
                    navController.navigate("cadastro6")
                }
            )
        }

        composable("cadastro6"){
            Cadastro6(
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
    }
}


