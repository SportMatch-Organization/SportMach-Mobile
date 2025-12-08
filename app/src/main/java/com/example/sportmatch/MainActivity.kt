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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sportmatch.data.database.entities.Competicao
import com.example.sportmatch.model.CadastroViewModel
import com.example.sportmatch.model.EnderecoUsuarioViewModel
import com.example.sportmatch.model.CampeonatoViewModel
import com.example.sportmatch.ui.screens.competicoes.CadastroCompeticao
import com.example.sportmatch.ui.Home
import com.example.sportmatch.ui.Perfil_organizador.PerfilOrganizador
import com.example.sportmatch.ui.login.Login
import com.example.sportmatch.ui.screens.cadastro.Cadastro1
import com.example.sportmatch.ui.screens.competicoes.pesquisar.Pesquisar
import com.example.sportmatch.ui.screens.espacosEsportivo.CadastroEspacoEsportivo
import com.example.sportmatch.ui.screens.patrocinadores.TelaCadastro
import com.example.sportmatch.ui.screens.perfil.EditarPerfilOrganizadorScreen
import com.example.sportmatch.ui.screens.pesquisar.CompeticaoDetalhes
import com.example.sportmatch.ui.screens.pesquisar.CompeticaoDetalhesRoute
import com.example.sportmatch.ui.theme.SportmatchTheme
import com.example.sportmatch.ui.viewModel.EspacoEsportivoViewModel
import com.example.sportmatch.ui.theme.laranjaPrincipal
import com.example.sportmatch.ui.theme.cinzaTextoSecundario

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
        startDestination = "pesquisar",
        modifier = modifier
    ) {
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
        composable("PerfilOrganizador") {
            PerfilOrganizador(navController = navController)
        }
        composable("editarPerfilOrganizador") {
            EditarPerfilOrganizadorScreen(
                onVoltar = { navController.popBackStack() },
                onSalvar = { navController.popBackStack() }
            )
        }
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

        composable("home") {
            Home(navController = navController)
        }

        composable("cadastro-competicao") { backStackEntry ->
            val campeonatoViewModel: CampeonatoViewModel = viewModel(backStackEntry)
            CadastroCompeticao(
                viewModel = campeonatoViewModel,
                onNext = { navController.navigate("cadastro-competicao2") }
            )
        }

        composable("pesquisar") {
            // Nota: Dentro da tela Pesquisar, lembre-se de chamar:

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
        composable("notificacoes") {
            Text(text = "Tela de Notificações", modifier = Modifier.padding(16.dp))
        }
        composable("perfil") {
            Text(text = "Tela de Perfil", modifier = Modifier.padding(16.dp))
        }
    }
}