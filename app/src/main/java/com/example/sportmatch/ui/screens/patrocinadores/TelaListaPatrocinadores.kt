package com.example.sportmatch.ui.screens.patrocinadores

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Delete
import com.example.sportmatch.database.SportMatchDatabase
import com.example.sportmatch.database.entities.Patrocinador


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaListaPatrocinadores(onVoltar: () -> Unit = {}) {

    val context = LocalContext.current
    val db = remember { SportMatchDatabase.getDatabase(context) }
    val scope = rememberCoroutineScope()
    var patrocinadores by remember { mutableStateOf(listOf<Patrocinador>()) }

    LaunchedEffect(key1 = System.currentTimeMillis()){
        scope.launch {
            patrocinadores = db.patrocinadorDao().listarTodos()
        }

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista de Patrocinadores",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF710F)
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (patrocinadores.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Nenhum patrocinador cadastrado ainda 😢")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(patrocinadores) { p ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0E0)),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text("Nome: ${p.nome}", fontSize = 18.sp)
                                Text("CNPJ: ${p.cnpj}", fontSize = 16.sp)
                                Text("Contato: ${p.contato}", fontSize = 16.sp)
                                Text("Valor: R$${p.valor.toDouble()}", fontSize = 16.sp)
                                Text("Competiçãp: ${p.competicao}", fontSize = 16.sp)

                                IconButton(
                                    onClick = {
                                        scope.launch {
                                            db.patrocinadorDao().deletarPorCNPJ(p.cnpj)
                                            patrocinadores = db.patrocinadorDao().listarTodos()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Excluir",
                                        tint = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
