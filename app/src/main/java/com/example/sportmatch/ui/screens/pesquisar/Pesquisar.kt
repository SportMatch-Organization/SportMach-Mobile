package com.example.sportmatch.ui.screens.pesquisar
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportmatch.ui.components.CompeticaoCard
import com.example.sportmatch.ui.components.FilterChipComBotao
import com.example.sportmatch.ui.screens.competicoes.pesquisar.PesquisarViewModel
import com.example.sportmatch.ui.theme.laranjaPrincipal
import com.example.sportmatch.ui.theme.cinzaFundoClaro
import com.example.sportmatch.ui.theme.cinzaTextoSecundario
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pesquisar(
    navController: NavHostController,
    viewModel: PesquisarViewModel = viewModel()
) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as? Activity)?.window
        window?.let {
            it.statusBarColor = Color.White.toArgb()
            WindowCompat.getInsetsController(it, view).isAppearanceLightStatusBars = true
        }
    }
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(uiState.isSearching) {
        if (uiState.isSearching) {
            focusRequester.requestFocus()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    if (uiState.isSearching) {
                        OutlinedTextField(
                            value = uiState.textoBusca,
                            onValueChange = { viewModel.onSearchTextChanged(it) },
                            placeholder = { Text("Pesquisar campeonato...") },
                            shape = RoundedCornerShape(50),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedContainerColor = cinzaFundoClaro,
                                unfocusedContainerColor = cinzaFundoClaro,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                focusedPlaceholderColor = cinzaTextoSecundario,
                                unfocusedPlaceholderColor = cinzaTextoSecundario
                            ),
                            trailingIcon = {
                                if (uiState.textoBusca.isNotEmpty()) {
                                    IconButton(onClick = { viewModel.onSearchTextChanged("") }) {
                                        Icon(Icons.Default.Close, contentDescription = "Limpar")
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            singleLine = true
                        )
                    } else {
                        Text("Pesquisar")
                    }
                },
                navigationIcon = {
                    if (uiState.isSearching) {
                        IconButton(onClick = { viewModel.onStopSearch() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                        }
                    }
                },
                actions = {
                    if (!uiState.isSearching) {
                        IconButton(onClick = { viewModel.onStartSearch() }) {
                            Icon(Icons.Default.Search, contentDescription = "Iniciar pesquisa")
                        }
                    }
                }
            )
        },
        containerColor = cinzaFundoClaro
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                PrimaryTabRow(
                    selectedTabIndex = uiState.selectedTabIndex,
                    containerColor = cinzaFundoClaro,
                    contentColor = laranjaPrincipal,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Tab(
                        selected = uiState.selectedTabIndex == 0,
                        onClick = { viewModel.onTabChanged(0) },
                        text = { Text("Competições", fontWeight = if (uiState.selectedTabIndex == 0) FontWeight.Bold else FontWeight.Normal) }
                    )
                    Tab(
                        selected = uiState.selectedTabIndex == 1,
                        onClick = { viewModel.onTabChanged(1) },
                        text = { Text("Pessoas", fontWeight = if (uiState.selectedTabIndex == 1) FontWeight.Bold else FontWeight.Normal) }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (uiState.selectedTabIndex == 0) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FilterChipComBotao(text = "Cidade") {
                            Toast.makeText(
                                context,
                                "Filtro Cidade não implementado",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        FilterChipComBotao(text = "Esporte") {
                            Toast.makeText(
                                context,
                                "Filtro Esporte não implementado",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        FilterChipComBotao(text = "Categoria") {
                            Toast.makeText(
                                context,
                                "Filtro Categoria não implementado",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                if (!uiState.isSearchEmpty) {
                    item {
                        Column(Modifier.padding(horizontal = 16.dp)) {
                            Text("Abertos", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                        }
                    }
                    if (uiState.competicoesAbertas.isEmpty()) {
                        item {
                            Column(Modifier.padding(horizontal = 16.dp)) {
                                Text("Nenhuma competição aberta encontrada.", modifier = Modifier.padding(bottom = 16.dp), color = cinzaTextoSecundario)
                            }
                        }
                    } else {
                        items(uiState.competicoesAbertas, key = { "aberto-${it.id}" }) { competicao ->
                            Column(Modifier.padding(horizontal = 16.dp)) {
                                /*CompeticaoCard(
                                    competicao = competicao,
                                    onVerMaisClick = { id ->
                                        navController.navigate("detalhes/$id")
                                    }
                                )*/
                            }
                        }
                    }
                    item {
                        Column(Modifier.padding(horizontal = 16.dp)) {
                            Text("Em andamento", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
                        }
                    }
                    if (uiState.competicoesEmAndamento.isEmpty()) {
                        item {
                            Column(Modifier.padding(horizontal = 16.dp)) {
                                Text("Nenhuma competição em andamento encontrada.", modifier = Modifier.padding(bottom = 16.dp), color = cinzaTextoSecundario)
                            }
                        }
                    } else {
                        items(uiState.competicoesEmAndamento, key = { "andamento-${it.id}" }) { competicao ->
                            Column(Modifier.padding(horizontal = 16.dp)) {
                                /*CompeticaoCard(
                                    competicao = competicao,
                                    onVerMaisClick = { id ->
                                        navController.navigate("detalhes/$id")
                                    }
                                )*/
                            }
                        }
                    }
                    item {
                        Column(Modifier.padding(horizontal = 16.dp)) {
                            Text("Encerrados", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
                        }
                    }
                    if (uiState.competicoesEncerradas.isEmpty()) {
                        item {
                            Column(Modifier.padding(horizontal = 16.dp)) {
                                Text("Nenhuma competição encerrada encontrada.", modifier = Modifier.padding(bottom = 16.dp), color = cinzaTextoSecundario)
                            }
                        }
                    } else {
                        items(uiState.competicoesEncerradas, key = { "encerrado-${it.id}" }) { competicao ->
                            Column(Modifier.padding(horizontal = 16.dp)) {
                                /*CompeticaoCard(
                                    competicao = competicao,
                                    onVerMaisClick = { id ->
                                        navController.navigate("detalhes/$id")
                                    }
                                )*/
                            }
                        }
                    }
                }
            }
            else {
                item {
                    Text(
                        "Funcionalidade 'Pessoas' não implementada.",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = cinzaTextoSecundario
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}