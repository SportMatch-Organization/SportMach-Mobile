package com.example.cadastrologinsportmatch


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
import com.example.cadastrologinsportmatch.ui.Login
import com.example.cadastrologinsportmatch.ui.Home
import com.example.cadastrologinsportmatch.ui.cadastro.Cadastro1
import com.example.cadastrologinsportmatch.ui.cadastro.Cadastro2
import com.example.cadastrologinsportmatch.ui.theme.CadastroLoginSportMatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CadastroLoginSportMatchTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Passe o innerPadding para o container da sua tela
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
            Cadastro2()
        }
        composable("home") {
            Home()
        }
    }
}


