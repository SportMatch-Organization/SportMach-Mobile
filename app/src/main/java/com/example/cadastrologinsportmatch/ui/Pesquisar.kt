package com.example.cadastrologinsportmatch.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cadastrologinsportmatch.R
import com.example.cadastrologinsportmatch.database.SportMatchDatabase
import com.example.cadastrologinsportmatch.database.dao.CompeticaoDao
import com.example.cadastrologinsportmatch.database.entities.Competicao
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch


val laranjaPrincipal = Color(0xFFF97316)
val verdeTaxa = Color(0xFF04A777)
val cinzaFundoClaro = Color(0xFFF3F4F6)
val cinzaTextoSecundario = Color.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pesquisar(navController: NavHostController) {


    var listaCompeticoes by remember { mutableStateOf(emptyList<Competicao>()) }
    var selectedTabIndex by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val competicaoDao = remember { SportMatchDatabase.getDatabase(context).competicaoDao() }


    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = false)
    }


    LaunchedEffect(Unit) {
        scope.launch {
            listaCompeticoes = competicaoDao.getTodasCompeticoes()

            if (listaCompeticoes.isEmpty()) {
                adicionarDadosDeTeste(competicaoDao)
                listaCompeticoes = competicaoDao.getTodasCompeticoes()
            }
        }
    }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Campeonato A", fontWeight = FontWeight.Bold, color = Color.White) },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Busca ainda não implementada", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Pesquisar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = laranjaPrincipal // Cor laranja
                ),

                windowInsets = WindowInsets.statusBars.only(WindowInsetsSides.Top)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("home") { popUpTo("pesquisar"){ inclusive = true } } },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    colors = NavigationBarItemDefaults.colors(
                        unselectedIconColor = cinzaTextoSecundario,
                        unselectedTextColor = cinzaTextoSecundario,
                        indicatorColor = laranjaPrincipal.copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    selected = true, // Aba atual
                    onClick = { /* Já está aqui */ },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Pesquisar") },
                    label = { Text("Pesquisar") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = laranjaPrincipal,
                        selectedTextColor = laranjaPrincipal,
                        indicatorColor = laranjaPrincipal.copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { Toast.makeText(context, "Tela não implementada", Toast.LENGTH_SHORT).show() },
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Notificações") },
                    label = { Text("Notificações") },
                    colors = NavigationBarItemDefaults.colors(
                        unselectedIconColor = cinzaTextoSecundario,
                        unselectedTextColor = cinzaTextoSecundario,
                        indicatorColor = laranjaPrincipal.copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { Toast.makeText(context, "Tela não implementada", Toast.LENGTH_SHORT).show() },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") },
                    colors = NavigationBarItemDefaults.colors(
                        unselectedIconColor = cinzaTextoSecundario,
                        unselectedTextColor = cinzaTextoSecundario,
                        indicatorColor = laranjaPrincipal.copy(alpha = 0.1f)
                    )
                )
            }
        },

        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(cinzaFundoClaro)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {


            item {
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = cinzaFundoClaro,
                    contentColor = laranjaPrincipal
                ) {
                    Tab(
                        selected = selectedTabIndex == 0,
                        onClick = { selectedTabIndex = 0 },
                        text = { Text("Competições", fontWeight = if (selectedTabIndex == 0) FontWeight.Bold else FontWeight.Normal) },
                        selectedContentColor = laranjaPrincipal,
                        unselectedContentColor = cinzaTextoSecundario
                    )
                    Tab(
                        selected = selectedTabIndex == 1,
                        onClick = { selectedTabIndex = 1 },
                        text = { Text("Pessoas", fontWeight = if (selectedTabIndex == 1) FontWeight.Bold else FontWeight.Normal) },
                        selectedContentColor = laranjaPrincipal,
                        unselectedContentColor = cinzaTextoSecundario
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }


            if (selectedTabIndex == 0) { // Mostra Competições

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChipWithDropdown(text = "Cidade") {
                            Toast.makeText(context, "Filtro Cidade não implementado", Toast.LENGTH_SHORT).show()
                        }
                        FilterChipWithDropdown(text = "Esporte") {
                            Toast.makeText(context, "Filtro Esporte não implementado", Toast.LENGTH_SHORT).show()
                        }
                        FilterChipWithDropdown(text = "Categoria") {
                            Toast.makeText(context, "Filtro Categoria não implementado", Toast.LENGTH_SHORT).show()
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Text("Abertos", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                }
                val competicoesAbertas = listaCompeticoes.filter { it.status.equals("Abertos", ignoreCase = true) }
                if (competicoesAbertas.isEmpty()) {
                    item { Text("Nenhuma competição aberta.", modifier = Modifier.padding(bottom = 16.dp), color = cinzaTextoSecundario) }
                } else {
                    items(competicoesAbertas, key = { "aberto-${it.id}" }) { competicao ->
                        CompeticaoCard(competicao = competicao)
                    }
                }

                item {
                    Text("Em andamento", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
                }
                val competicoesEmAndamento = listaCompeticoes.filter { it.status.equals("Em andamento", ignoreCase = true) }
                if (competicoesEmAndamento.isEmpty()) {
                    item { Text("Nenhuma competição em andamento.", modifier = Modifier.padding(bottom = 16.dp), color = cinzaTextoSecundario) }
                } else {
                    items(competicoesEmAndamento, key = { "andamento-${it.id}" }) { competicao ->
                        CompeticaoCard(competicao = competicao)
                    }
                }


                item {
                    Text("Encerrados", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
                }
                val competicoesEncerradas = listaCompeticoes.filter { it.status.equals("Encerrados", ignoreCase = true) }
                if (competicoesEncerradas.isEmpty()) {
                    item { Text("Nenhuma competição encerrada.", modifier = Modifier.padding(bottom = 16.dp), color = cinzaTextoSecundario) }
                } else {
                    items(competicoesEncerradas, key = { "encerrado-${it.id}" }) { competicao ->
                        CompeticaoCard(competicao = competicao)
                    }
                }


                item { Spacer(modifier = Modifier.height(16.dp)) }

            } else {
                item {
                    Text(
                        "Funcionalidade 'Pessoas' ainda não implementada.",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = cinzaTextoSecundario
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipWithDropdown(
    text: String,
    onClick: () -> Unit
) {
    FilterChip(
        selected = false,
        onClick = onClick,
        label = { Text(text, fontSize = 12.sp) },
        shape = RoundedCornerShape(50),
        leadingIcon = null,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Abrir opções de $text",
                modifier = Modifier.size(FilterChipDefaults.IconSize)
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            labelColor = Color.DarkGray,
            iconColor = laranjaPrincipal
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color.LightGray.copy(alpha=0.5f),
            borderWidth = 1.dp
        ),
        elevation = FilterChipDefaults.filterChipElevation(
            elevation = 0.dp,
            pressedElevation = 0.dp
        ),
        enabled = true
    )
}



@Composable
fun CompeticaoCard(competicao: Competicao) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* TODO: Navegar para detalhes da competição */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(competicao.titulo, fontWeight = FontWeight.Bold, fontSize = 16.sp, maxLines = 1)
                Spacer(modifier = Modifier.height(6.dp))


                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.SportsVolleyball, contentDescription = "Esporte", modifier = Modifier.size(14.dp), tint = laranjaPrincipal)
                    Spacer(Modifier.width(4.dp))
                    Text(competicao.esporte, fontSize = 12.sp, color = cinzaTextoSecundario)
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Filled.People, contentDescription = "Modo", modifier = Modifier.size(14.dp), tint = laranjaPrincipal)
                    Spacer(Modifier.width(4.dp))
                    Text(competicao.modo, fontSize = 12.sp, color = cinzaTextoSecundario)
                    Spacer(Modifier.weight(1f))
                    Text("R$ %.2f".format(competicao.taxa), fontSize = 13.sp, fontWeight = FontWeight.Bold, color = verdeTaxa)
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Linha 2: Data e Horário
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DateRange, contentDescription = "Data", modifier = Modifier.size(14.dp), tint = cinzaTextoSecundario)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${competicao.data} | ${competicao.horario}", fontSize = 11.sp, color = cinzaTextoSecundario)
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Linha 3: Local
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = "Local", modifier = Modifier.size(14.dp), tint = cinzaTextoSecundario)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(competicao.local, fontSize = 11.sp, color = cinzaTextoSecundario, maxLines = 1)
                }

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Group, contentDescription = "Vagas", modifier = Modifier.size(14.dp), tint = cinzaTextoSecundario)
                        Spacer(Modifier.width(4.dp))
                        Text("${competicao.vagasPreenchidas}/${competicao.vagasTotais}", fontSize = 11.sp, color = cinzaTextoSecundario)
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { /* TODO: Ação Ver mais */ }
                    ) {
                        Text("Ver mais", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = laranjaPrincipal)
                        Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, modifier = Modifier.size(12.dp), tint = laranjaPrincipal)
                    }
                }
            }

            Image(
                painter = painterResource(id = R.drawable.placeholder_volei),
                contentDescription = competicao.titulo,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}


suspend fun adicionarDadosDeTeste(dao: CompeticaoDao) {

    if (dao.getTodasCompeticoes().isEmpty()) {
        dao.insert(Competicao(titulo="Campeonato Aravôlei", status="Abertos", esporte="Vôlei", modo="Misto", taxa=25.90, data="16/11/2025", horario="15:00 - 17:00", local="Quadra do sagrada familia", vagasPreenchidas=2, vagasTotais=33, imagemUrl="placeholder"))
        dao.insert(Competicao(titulo="Campeonato Amador", status="Abertos", esporte="Vôlei", modo="Misto", taxa=20.00, data="16/11/2025", horario="15:00 - 17:00", local="Quadra do sagrada familia", vagasPreenchidas=5, vagasTotais=20, imagemUrl="placeholder"))
        dao.insert(Competicao(titulo="Campeonato Arriba", status="Em andamento", esporte="Vôlei", modo="Misto", taxa=25.90, data="16/11/2025", horario="13:00 - 17:00", local="Quadra do sagrada familia", vagasPreenchidas=10, vagasTotais=15, imagemUrl="placeholder"))
        dao.insert(Competicao(titulo="Campeonato Arau", status="Encerrados", esporte="Vôlei", modo="Misto", taxa=25.90, data="16/11/2025", horario="15:00 - 17:00", local="Quadra do sagrada familia", vagasPreenchidas=30, vagasTotais=30, imagemUrl="placeholder"))
        dao.insert(Competicao(titulo="Campeonato Amador Encerrado", status="Encerrados", esporte="Vôlei", modo="Misto", taxa=20.00, data="16/11/2025", horario="15:00 - 17:00", local="Quadra do sagrada familia", vagasPreenchidas=20, vagasTotais=20, imagemUrl="placeholder"))
    }
}

