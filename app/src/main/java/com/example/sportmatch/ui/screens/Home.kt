package com.example.sportmatch.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sportmatch.model.BottomNavBarItem
import com.example.sportmatch.model.BottomNavBarItem.Perfil
import com.example.sportmatch.ui.screens.cadastroUsuario.Cadastro1
import com.example.sportmatch.ui.screens.competicoes.ExploreCompeticao
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

    var selectedItem by remember {
        val item = bottomNavBarItems.first()
        mutableStateOf(item)
    }

    val pageState = rememberPagerState {
        bottomNavBarItems.size
    }

    LaunchedEffect(selectedItem) {
        val currentIndex = bottomNavBarItems.indexOf(selectedItem)
        pageState.animateScrollToPage(currentIndex)
    }

    LaunchedEffect(pageState.targetPage) {
        selectedItem = bottomNavBarItems[pageState.targetPage]
    }

    Scaffold (bottomBar = {
        BottomNavBar(
        selectedItem = selectedItem,
    )
    }) {
        innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)){
            HorizontalPager(pageState) {
                page ->
                val item = bottomNavBarItems[page]
                when (item) {
                    BottomNavBarItem.ExploreCompeticao -> ExploreCompeticao({})
                    BottomNavBarItem.Pesquisar -> Text(text = "Pesquisar")
                    BottomNavBarItem.Notificacao -> Text(text = "Notificações")
                    Perfil -> Text(text = "Perfil")
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(selectedItem: BottomNavBarItem) {
    NavigationBar(containerColor = Branco) {
        bottomNavBarItems.forEach { item ->
            NavigationBarItem(
                selected = item.label == selectedItem.label,
                onClick = {

                },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(item.label)
                }
            )
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    Home(
        navController = TODO()
    )
}
