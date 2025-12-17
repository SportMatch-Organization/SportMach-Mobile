package com.example.sportmatch.ui.screens.patrocinadores

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.material3.TextFieldDefaults
import com.example.sportmatch.data.api.APITempo.WeatherService
import com.example.sportmatch.data.database.SportMatchDatabase
import com.example.sportmatch.data.database.entities.Patrocinador


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastro(onVoltar: () -> Unit = {}, onVerCadastrados: () -> Unit = {}) {
    var nomePatrocinador by remember { mutableStateOf("") }
    var cnpjPatrocinador by remember { mutableStateOf("") }
    var contatoPatrocinador by remember { mutableStateOf("") }
    var valorInvestido by remember { mutableStateOf("") }
    var competicao by remember { mutableStateOf("") }

    val context = LocalContext.current

    // API de clima
    var temperatura by remember { mutableStateOf<String?>(null) }
    var descricao by remember { mutableStateOf<String?>(null) }
    var erro by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val response = WeatherService.getWeather("Arapiraca")
            val cond = response.current_condition.firstOrNull()
            if (cond != null) {
                temperatura = cond.temp_C ?: "—"
                descricao = cond.weatherDesc.firstOrNull()?.value ?: "—"
                erro = null
            } else {
                erro = "Dados do clima indisponíveis."
            }
        } catch (e: Exception) {
            erro = "Erro ao buscar dados: ${e.message}"
        }
    }

    // Banco de dados
    val db = remember { SportMatchDatabase.getDatabase(context) }
    val scope = rememberCoroutineScope()
    var salvando by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🏆 Patrocínio", color = Color.White, fontSize = 24.sp) },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFF710F))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            @Composable
            fun campoTexto(
                valor: String,
                onValorChange: (String) -> Unit,
                label: String,
                modifier: Modifier = Modifier
            ) {
                OutlinedTextField(
                    value = valor,
                    onValueChange = onValorChange,
                    label = { Text(label) },
                    modifier = modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0xFFFF710F),
                        unfocusedIndicatorColor = Color.Gray,
                        focusedLabelColor = Color(0xFFFF710F),
                        cursorColor = Color(0xFFFF710F),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray,
                        focusedContainerColor = Color(0xFFBDBDBD),
                        unfocusedContainerColor = Color(0xFFBDBDBD)
                    )
                )
            }

            campoTexto(nomePatrocinador, {
                if (it.all { char -> char.isLetter() || char.isWhitespace() })
                    nomePatrocinador = it
            }, "Nome do patrocinador")
            Spacer(modifier = Modifier.height(24.dp))
            campoTexto(cnpjPatrocinador, {
                if (it.length <= 14 && it.all { c -> c.isDigit() })
                    cnpjPatrocinador = it
            }, "CNPJ do patrocinador")
            Spacer(modifier = Modifier.height(24.dp))
            campoTexto(competicao, {if (it.all { char -> char.isLetter() || char.isWhitespace() }) competicao = it }, "Competição patrocinada")
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                campoTexto(contatoPatrocinador, { if (it.length <= 11 && it.all { c -> c.isDigit()})contatoPatrocinador = it }, "Contato", Modifier.weight(2f))
                campoTexto(valorInvestido, { if (it.all { c -> c.isDigit()})valorInvestido = it }, "Valor", Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Exibição do clima
            if (erro != null) {
                Text(text = erro!!, color = Color.Red, fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp))
            } else if (temperatura != null && descricao != null) {
                Text(text = " $temperatura°C - $descricao", color = Color.Black, fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp))
            } else {
                Text(text = "Carregando clima...", color = Color.Gray, fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (nomePatrocinador.isNotBlank() && cnpjPatrocinador.isNotBlank()) {
                        scope.launch {
                            salvando = true
                            db.patrocinadorDao().inserir(
                                Patrocinador(
                                    nome = nomePatrocinador,
                                    cnpj = cnpjPatrocinador,
                                    contato = contatoPatrocinador,
                                    valor = valorInvestido,
                                    competicao = competicao
                                )
                            )
                            nomePatrocinador = ""
                            cnpjPatrocinador = ""
                            contatoPatrocinador = ""
                            valorInvestido = ""
                            competicao = ""
                            salvando = false
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF710F), contentColor = Color.White),
                modifier = Modifier.fillMaxWidth().height(70.dp).padding(bottom = 20.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                if (salvando) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 3.dp, modifier = Modifier.size(26.dp))
                } else {
                    Text("Cadastrar", fontSize = 18.sp)
                }
            }

            Button(
                onClick = { scope.launch { if (!salvando) onVerCadastrados() } },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = Color.White),
                modifier = Modifier.fillMaxWidth().height(70.dp).padding(bottom = 20.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Ver Cadastrados", fontSize = 18.sp)
            }
        }
    }
}
