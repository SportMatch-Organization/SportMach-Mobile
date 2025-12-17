package com.example.sportmatch.ui.Perfil_organizador
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportmatch.ui.components.CabecalhoOrganizador
import com.example.sportmatch.ui.components.CompeticaoCard
import com.example.sportmatch.ui.viewModel.PerfilOrganizadorViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilOrganizador(
    navController: NavController,
    viewModel: PerfilOrganizadorViewModel = viewModel()
) {
    val dadosOrganizador by viewModel.estadoOrganizador.collectAsState()
    val listaCompeticoes by viewModel.estadoCompeticoes.collectAsState()
    Scaffold(
        containerColor = Color(0xFFF0F4F8),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Perfil do Organizador", fontWeight = FontWeight.Bold, color = Color(0xFF1A2C42))
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingInterno ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingInterno)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            // 1. Seção do Cabeçalho
            item {
                CabecalhoOrganizador(
                    organizador = dadosOrganizador,
                    aoClicarEditar = {
                        navController.navigate("editarPerfilOrganizador")
                    }
                )
            }
            item {
                Text(
                    text = "Competições realizadas:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A2C42),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            items(listaCompeticoes) { competicao ->
//                CompeticaoCard(
//                    competicao = competicao,
//                    onVerMaisClick = { id ->
//                        navController.navigate("detalhes/$id")
//                    }
//                )
            }
        }
    }
}