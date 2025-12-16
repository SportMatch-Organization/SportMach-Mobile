package com.example.sportmatch.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sportmatch.model.BottomNavBarItem
import com.example.sportmatch.model.BottomNavBarItem.Perfil
import com.example.sportmatch.model.CampeonatoViewModel
import com.example.sportmatch.ui.Perfil_organizador.PerfilOrganizador
import com.example.sportmatch.ui.components.CompeticaoCard
import com.example.sportmatch.ui.components.CustomText
import com.example.sportmatch.ui.components.Loading
import com.example.sportmatch.ui.components.TextType
import com.example.sportmatch.ui.screens.cadastroUsuario.Cadastro1
import com.example.sportmatch.ui.screens.competicoes.ExploreCompeticao
import com.example.sportmatch.ui.screens.pesquisar.Pesquisar
import com.example.sportmatch.ui.theme.Branco
import com.example.sportmatch.ui.viewModel.HomeViewModel
import com.example.sportmatch.ui.viewModel.user.CadastroViewModel

val bottomNavBarItems = listOf(
    BottomNavBarItem.ExploreCompeticao,
    BottomNavBarItem.Pesquisar,
    BottomNavBarItem.Notificacao,
    Perfil,
)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.buscarCompeticoesSupabase()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(24.dp))
            CustomText("Criadas por mim", TextType.TITULO, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))

            if (viewModel.competicoes.isEmpty()) {
                Text(
                    text = "Nenhuma competição encontrada",
                    color = Color.Gray
                )
            } else {
                androidx.compose.foundation.lazy.LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 16.dp)
                ) {
                    items(
                        items = viewModel.competicoes,
                        key = { it.id!! }
                    ) { competicao ->
                        CompeticaoCard(
                            competicao = competicao,
                            onVerMaisClick = { id ->
                                navController.navigate("detalhes/$id")
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
        Loading(visible = viewModel.carregando)
    }
}