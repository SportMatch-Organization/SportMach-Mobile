package com.example.sportmatch.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sportmatch.model.BottomNavBarItem
import com.example.sportmatch.model.BottomNavBarItem.Perfil
import com.example.sportmatch.ui.Perfil_organizador.PerfilOrganizador
import com.example.sportmatch.ui.screens.cadastroUsuario.Cadastro1
import com.example.sportmatch.ui.screens.competicoes.ExploreCompeticao
import com.example.sportmatch.ui.screens.pesquisar.Pesquisar
import com.example.sportmatch.ui.theme.Branco
import com.example.sportmatch.ui.viewModel.user.CadastroViewModel

val bottomNavBarItems = listOf(
    BottomNavBarItem.ExploreCompeticao,
    BottomNavBarItem.Pesquisar,
    BottomNavBarItem.Notificacao,
    Perfil,
)
@Composable
fun Home(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Tela de Home", style = MaterialTheme.typography.headlineMedium)
    }
}
