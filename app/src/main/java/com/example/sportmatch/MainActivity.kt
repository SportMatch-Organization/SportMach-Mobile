package com.example.sportmatch

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sportmatch.model.CampeonatoViewModel
import com.example.sportmatch.ui.Perfil_organizador.PerfilOrganizador
import com.example.sportmatch.ui.screens.Perfil.PerfilUsuario
import com.example.sportmatch.ui.screens.cadastro.Cadastro4
import com.example.sportmatch.ui.screens.cadastro.Cadastro5
import com.example.sportmatch.ui.screens.cadastro.Cadastro6
import com.example.sportmatch.ui.screens.cadastroUsuario.Cadastro1
import com.example.sportmatch.ui.screens.cadastroUsuario.Cadastro2
import com.example.sportmatch.ui.cadastro.Cadastro3
import com.example.sportmatch.ui.screens.competicoes.CadastroCompeticao
import com.example.sportmatch.ui.competicoes.CadastroCompeticao2
import com.example.sportmatch.ui.competicoes.CadastroCompeticao3
import com.example.sportmatch.ui.login.Login
import com.example.sportmatch.ui.screens.Home
import com.example.sportmatch.ui.screens.competicoes.ExploreCompeticao
import com.example.sportmatch.ui.screens.competicoes.pesquisar.Pesquisar
import com.example.sportmatch.ui.screens.espacosEsportivo.CadastroEspacoEsportivo
import com.example.sportmatch.ui.screens.patrocinadores.TelaCadastro
import com.example.sportmatch.ui.screens.perfil.EditarPerfilOrganizadorScreen
import com.example.sportmatch.ui.screens.pesquisar.CompeticaoDetalhesRoute
import com.example.sportmatch.ui.theme.SportmatchTheme
import com.example.sportmatch.ui.theme.cinzaTextoSecundario
import com.example.sportmatch.ui.theme.laranjaPrincipal
import com.example.sportmatch.ui.viewModel.EspacoEsportivoViewModel
import com.example.sportmatch.ui.viewModel.user.CadastroViewModel
import com.example.sportmatch.ui.viewModel.user.EnderecoUsuarioViewModel
import com.example.sportmatch.ui.viewModel.user.EsportesInteresseViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportmatchTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val bottomBarRoutes = setOf(
                    "home",
                    "pesquisar",
                    "notificacoes",
                    "PerfilOrganizador"
                )
                val shouldShowBottomBar = currentRoute in bottomBarRoutes

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (shouldShowBottomBar) {
                            AppBottomNavigation(navController = navController, currentRoute = currentRoute)
                        }
                    }
                ) { innerPadding ->
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
fun AppBottomNavigation(navController: NavHostController, currentRoute: String?) {
    NavigationBar(containerColor = Color.White) {
        val items = listOf(
            BottomNavItem("home", Icons.Default.Home, "Home"),
            BottomNavItem("pesquisar", Icons.Default.Search, "Pesquisar"),
            BottomNavItem("notificacoes", Icons.Default.Notifications, "Notificações"),
            BottomNavItem("PerfilOrganizador", Icons.Default.Person, "Perfil")
        )
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = laranjaPrincipal,
                    selectedTextColor = laranjaPrincipal,
                    unselectedIconColor = cinzaTextoSecundario,
                    unselectedTextColor = cinzaTextoSecundario,
                    indicatorColor = laranjaPrincipal.copy(alpha = 0.1f)
                )
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val cadastroViewModel: CadastroViewModel = viewModel()
    val enderecoUsuarioViewModel: EnderecoUsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login", // Define o Login como ponto de partida
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

        composable("PerfilOrganizador") {
            PerfilOrganizador(navController = navController)
        }

        composable("editarPerfilOrganizador") {
            EditarPerfilOrganizadorScreen(
                onVoltar = { navController.popBackStack() },
                onSalvar = { navController.popBackStack() }
            )
        }

        composable("cadastro1") {
            Cadastro1(
                viewModel = cadastroViewModel,
                onNavigateToCadastro2 = { navController.navigate("cadastro2") }
            )
        }
        composable("cadastro2") {
            Cadastro2(
                viewModel = cadastroViewModel,
                onNavigateToCadastro3 = { navController.navigate("cadastro3") }
            )
        }
        composable("cadastro3") {
            Cadastro3(
                viewModel = cadastroViewModel,
                onNavigateToCadastro4 = { navController.navigate("cadastro4") }
            )
        }
        composable("cadastro4") {
            Cadastro4(
                viewModel = cadastroViewModel,
                enderecoUsuarioViewModel = enderecoUsuarioViewModel,
                onNavigateToCadastro5 = { navController.navigate("cadastro5") }
            )
        }
        composable("cadastro5") {
            Cadastro5(
                viewModel = cadastroViewModel,
                enderecoUsuarioViewModel = enderecoUsuarioViewModel,
                onNavigateToCadastro6 = { navController.navigate("cadastro6") }
            )
        }
        composable("cadastro6") {
            val esportesInteresseViewModel: EsportesInteresseViewModel = viewModel()
            Cadastro6(
                viewModel = cadastroViewModel,
                esportesInteresseViewModel = esportesInteresseViewModel,
                onNavigateToLogin = { navController.navigate("login") },
            )
        }

        composable("home") {
            // Unificado: Passando navController para permitir navegação interna na Home
            Home(navController = navController)
        }

        composable("pesquisar") {
            Pesquisar(navController = navController)
        }

        composable("notificacoes") {
            Text(text = "Tela de Notificações", modifier = Modifier.padding(16.dp))
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
        composable(
            route = "detalhes/{competicaoId}",
            arguments = listOf(
                navArgument("competicaoId") { type = NavType.IntType }
            ),
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("competicaoId") ?: 0
            CompeticaoDetalhesRoute(
                competicaoId = id,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("explore-competicao") {
            ExploreCompeticao(
                onNavigateToDetail = {
                    navController.navigate("detalhes/1")
                }
            )
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
                onVoltar = { navController.popBackStack() },
                onVerCadastrados = {}
            )
        }
    }
}