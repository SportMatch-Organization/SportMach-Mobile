package com.example.sportmatch


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportmatch.ui.Login
import com.example.sportmatch.ui.Home
import com.example.sportmatch.ui.cadastro.Cadastro1
import com.example.sportmatch.ui.cadastro.Cadastro2
import com.example.sportmatch.ui.cadastro.Cadastro3
import com.example.sportmatch.ui.cadastro.Cadastro4
import com.example.sportmatch.ui.cadastro.Cadastro5
import com.example.sportmatch.ui.cadastro.Cadastro6
import com.example.sportmatch.ui.theme.CadastroLoginSportMatchTheme
import com.google.firebase.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CadastroLoginSportMatchTheme {
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
        startDestination = "login",
        modifier = modifier // aplica o padding aqui
    ) {
        composable("login") {
            Login(
                onLogin = { loginDto ->
                    navController.navigate("home")
                },
                onNavigateToCadastro = {
                    navController.navigate("cadastro1")
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
    }
}


