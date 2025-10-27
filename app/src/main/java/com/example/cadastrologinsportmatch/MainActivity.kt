package com.example.cadastrologinsportmatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cadastrologinsportmatch.ui.Cadastro
import com.example.cadastrologinsportmatch.ui.Home
import com.example.cadastrologinsportmatch.ui.Login
import com.example.cadastrologinsportmatch.ui.PerfilUsuario
import com.example.cadastrologinsportmatch.ui.theme.CadastroLoginSportMatchTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        FirebaseApp.initializeApp(this)

        setContent {
            CadastroLoginSportMatchTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            Login(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToCadastro = {
                    navController.navigate("cadastro")
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("cadastro") {
            Cadastro(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onCadastroSuccess = {
                    navController.navigate("perfil_usuario") {
                        popUpTo("login") { inclusive = true }
                    }
                }
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
        composable("home") {
            Home()
        }
    }
}