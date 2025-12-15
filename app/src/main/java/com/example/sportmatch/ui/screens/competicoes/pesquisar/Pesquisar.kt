
package com.example.sportmatch.ui.screens.competicoes.pesquisar

import ApiSport
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import androidx.compose.material3.OutlinedTextFieldDefaults
import com.google.android.gms.common.api.Api
import com.example.sportmatch.R
import com.example.sportmatch.data.database.SportMatchDatabase
import com.example.sportmatch.data.database.dao.CompeticaoDao
import com.example.sportmatch.data.database.entities.Competicao

val laranjaPrincipal = Color(0xFFF97316)
val verdeTaxa = Color(0xFF04A777)
val cinzaFundoClaro = Color(0xFFF3F4F6)
val cinzaTextoSecundario = Color.Gray
val cinzaChipFundo = Color(0xFFEEEEEE)


val listaEsportesLocal = listOf("Esporte", "Todos", "Vôlei", "Basquete", "Futebol")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pesquisar(navController: NavHostController) {

    var listaCompeticoes by remember { mutableStateOf(emptyList<Competicao>()) }
    var listaEsportesDaApi by remember { mutableStateOf(emptyList<ApiSport>()) }
    var isLoadingApi by remember { mutableStateOf(false) }
    var selectedTabIndex by remember { mutableStateOf(0) }
    var filtroEsporteExpanded by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("PESQUISAR_PREFS", 0)
    var esporteSelecionado by remember {
        mutableStateOf(sharedPrefs.getString("esporteSelecionado", "Esporte") ?: "Esporte")
    }

    val scope = rememberCoroutineScope()
    val competicaoDao = remember { SportMatchDatabase.getDatabase(context).competicaoDao() }

    LaunchedEffect(Unit) {
        Log.d("DEBUG_COMPETICOES", "oi")
        isLoadingApi = true
        try {
            listaEsportesDaApi = listaEsportesLocal.mapIndexed { index, name -> ApiSport(index, name) }
        } catch (e: Exception) {
            listaEsportesDaApi = listaEsportesLocal.mapIndexed { index, name -> ApiSport(index, name) }
            Toast.makeText(context, "API falhou, a usar lista local de esportes.", Toast.LENGTH_SHORT).show()
        }
        isLoadingApi = false
    }


    LaunchedEffect(selectedTabIndex) {
        if (selectedTabIndex == 0) {
            listaCompeticoes = competicaoDao.getTodasCompeticoes()
            if (listaCompeticoes.isEmpty()) {
                adicionarDadosDeTeste(competicaoDao)
                listaCompeticoes = competicaoDao.getTodasCompeticoes()
            }
        }
    }


    val competicoesFiltradas = remember(esporteSelecionado, listaCompeticoes) {
        if (esporteSelecionado == "Todos" || esporteSelecionado == "Esporte") listaCompeticoes
        else listaCompeticoes.filter {
            it.esporte.equals(esporteSelecionado, ignoreCase = true)
        }
    }
    val competicoesAbertas = remember(competicoesFiltradas) {
        competicoesFiltradas.filter { it.status.equals("Abertos", ignoreCase = true) }
    }
    val competicoesEmAndamento = remember(competicoesFiltradas) {
        competicoesFiltradas.filter { it.status.equals("Em andamento", ignoreCase = true) }
    }
    val competicoesEncerradas = remember(competicoesFiltradas) {
        competicoesFiltradas.filter { it.status.equals("Encerrados", ignoreCase = true) }
    }


    val systemUiController = rememberSystemUiController()
    SideEffect { systemUiController.setStatusBarColor(Color.Transparent, darkIcons = false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Campeonato A", fontWeight = FontWeight.Bold, color = Color.White) },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = "Pesquisar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = laranjaPrincipal
                ),
                windowInsets = WindowInsets.statusBars.only(WindowInsetsSides.Top)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("home") { popUpTo("pesquisar") { inclusive = true } } },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    colors = NavigationBarItemDefaults.colors(
                        unselectedIconColor = cinzaTextoSecundario,
                        unselectedTextColor = cinzaTextoSecundario,
                        indicatorColor = laranjaPrincipal.copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
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
                PrimaryTabRow(
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


            if (selectedTabIndex == 0) {


                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FilterChipComBotao (text = "Cidade") {
                            Toast.makeText(context, "Filtro Cidade não implementado", Toast.LENGTH_SHORT).show()
                        }

                        FiltroEsporteDropdown(
                            listaEsportes = listaEsportesDaApi,
                            esporteSelecionado = esporteSelecionado,
                            onEsporteSelecionado = { esporte ->
                                esporteSelecionado = esporte
                                sharedPrefs.edit().putString("esporteSelecionado", esporte).apply()
                                filtroEsporteExpanded = false
                            },
                            expanded = filtroEsporteExpanded,
                            onExpandedChange = { filtroEsporteExpanded = it }
                        )

                        FilterChipComBotao(text = "Categoria") {
                            Toast.makeText(context, "Filtro Categoria não implementado", Toast.LENGTH_SHORT).show()
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item { Text("Abertos", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp)) }
                if (competicoesAbertas.isEmpty()) {
                    item { Text("Nenhuma competição aberta.", modifier = Modifier.padding(bottom = 16.dp), color = cinzaTextoSecundario) }
                } else {
                    items(competicoesAbertas, key = { "aberto-${it.id}" }) { competicao ->
                        CompeticaoCard(competicao = competicao)
                    }
                }

                item { Text("Em andamento", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)) }
                if (competicoesEmAndamento.isEmpty()) {
                    item { Text("Nenhuma competição em andamento.", modifier = Modifier.padding(bottom = 16.dp), color = cinzaTextoSecundario) }
                } else {
                    items(competicoesEmAndamento, key = { "andamento-${it.id}" }) { competicao ->
                        CompeticaoCard(competicao = competicao)
                    }
                }

                item { Text("Encerrados", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)) }
                if (competicoesEncerradas.isEmpty()) {
                    item { Text("Nenhuma competição encerrada.", modifier = Modifier.padding(bottom = 16.dp), color = cinzaTextoSecundario) }
                } else {
                    items(competicoesEncerradas, key = { "encerrado-${it.id}" }) { competicao ->
                        CompeticaoCard(competicao = competicao)
                    }
                }

            } else {

                item {
                    Text(
                        "Funcionalidade 'Pessoas' (da API /users) ainda não implementada.",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = cinzaTextoSecundario
                    )
                }

            }
        }
    }
}


@Composable
fun FilterChipComBotao(text: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = Color.DarkGray
        ),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(text, fontSize = 12.sp)
        Spacer(Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Abrir opções de $text",
            modifier = Modifier.size(18.dp),
            tint = laranjaPrincipal
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltroEsporteDropdown(
    listaEsportes: List<ApiSport>,
    esporteSelecionado: String,
    onEsporteSelecionado: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange
    ) {
        OutlinedTextField(
            value = esporteSelecionado,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .widthIn(min = 120.dp),
            textStyle = LocalTextStyle.current.copy(fontSize = 12.sp, color = Color.DarkGray),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = RoundedCornerShape(50),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f),
                focusedTrailingIconColor = laranjaPrincipal,
                unfocusedTrailingIconColor = laranjaPrincipal,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            modifier = Modifier.background(Color.White)
        ) {
            listaEsportes.forEach { esporte ->
                DropdownMenuItem(
                    text = { Text(esporte.name, fontWeight = FontWeight.Bold) },
                    onClick = {
                        onEsporteSelecionado(esporte.name)
                        onExpandedChange(false)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {


            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = competicao.nome,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.DarkGray,
                            modifier = Modifier.weight(1f, fill = false)
                        )
                        Spacer(Modifier.width(8.dp))
                        ChipVisual(
                            text = "${competicao.vagasPreenchidas}/${competicao.total}",
                            icon = Icons.Filled.Group,
                            iconTint = Color.DarkGray
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ChipVisual(
                            text = competicao.esporte,
                            icon = Icons.Filled.SportsVolleyball,
                            iconTint = laranjaPrincipal
                        )
                        Spacer(Modifier.width(8.dp))
                        ChipVisual(
                            text = competicao.tipo,
                            icon = Icons.Filled.People,
                            iconTint = laranjaPrincipal
                        )
                        Spacer(Modifier.weight(1f))


                        ChipVisual(
                            text = "R$ %.2f".format(competicao.valor),
                            textColor = verdeTaxa,
                            fontWeight = FontWeight.Bold,
                            backgroundColor = verdeTaxa.copy(alpha = 0.1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.DateRange, contentDescription = "Data", modifier = Modifier.size(16.dp), tint = cinzaTextoSecundario)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(competicao.inicioCompeticao, fontSize = 12.sp, color = cinzaTextoSecundario)
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(Icons.Default.Schedule, contentDescription = "Horário", modifier = Modifier.size(16.dp), tint = cinzaTextoSecundario)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(competicao.inicioCompeticao, fontSize = 12.sp, color = cinzaTextoSecundario)
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = "Local", modifier = Modifier.size(16.dp), tint = laranjaPrincipal)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(competicao.local, fontSize = 12.sp, color = cinzaTextoSecundario, maxLines = 1)
                    }
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { /* TODO: Ação Ver mais */ }
                        .padding(top = 10.dp)
                ) {
                    Text("Ver mais", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = laranjaPrincipal)
                    Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, modifier = Modifier.size(14.dp), tint = laranjaPrincipal)
                }
            }


            Image(
                painter = painterResource(id = R.drawable.placeholder_volei),
                contentDescription = competicao.nome,
                modifier = Modifier
                    .width(110.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)),
            )
        }
    }
}



@Composable
fun ChipVisual(
    text: String,
    icon: ImageVector? = null,
    iconTint: Color = Color.DarkGray,
    textColor: Color = Color.DarkGray,
    fontWeight: FontWeight = FontWeight.Medium,
    backgroundColor: Color = cinzaChipFundo
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (icon != null) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(14.dp), tint = iconTint)
            Spacer(Modifier.width(4.dp))
        }
        Text(text, fontSize = 12.sp, color = textColor, fontWeight = fontWeight)
    }
}

suspend fun adicionarDadosDeTeste(dao: CompeticaoDao) {
    dao.insert(Competicao(nome="Campeonato Aravôlei", status="Abertos", esporte="Vôlei", tipo="Misto", valor=25.90, inicioCompeticao="16/11/2025", local="Quadra do sagrada familia", vagasPreenchidas=2, total=33, imagemUrl="placeholder"))
    dao.insert(Competicao(nome="Campeonato Amador", status="Abertos", esporte="Vôlei", tipo="Misto", valor=20.00, inicioCompeticao="16/11/2025", local="Quadra do sagrada familia", vagasPreenchidas=5, total=20, imagemUrl="placeholder"))
    dao.insert(Competicao(nome="Basquete de Rua", status="Abertos", esporte="Basquete", tipo="Masculino", valor=10.00, inicioCompeticao="20/11/2025", local="Parque Central", vagasPreenchidas=3, total=10, imagemUrl="placeholder"))
    dao.insert(Competicao(nome="Campeonato Arriba", status="Em andamento", esporte="Vôlei", tipo="Misto", valor=25.90, inicioCompeticao="16/11/2025", local="Quadra do sagrada familia", vagasPreenchidas=10, total=15, imagemUrl="placeholder"))
    dao.insert(Competicao(nome="Campeonato Arau", status="Encerrados", esporte="Vôlei", tipo="Misto", valor=25.90, inicioCompeticao="16/11/2025", local="Quadra do sagrada familia", vagasPreenchidas=30, total=30, imagemUrl="placeholder"))
}