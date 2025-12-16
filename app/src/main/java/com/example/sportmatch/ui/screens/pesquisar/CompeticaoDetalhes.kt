package com.example.sportmatch.ui.screens.pesquisar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportmatch.data.database.entities.CompeticaoDetalhesUiModel
import com.example.sportmatch.ui.components.CronogramaItem
import com.example.sportmatch.ui.components.DetailRow
import com.example.sportmatch.ui.components.InfoCard
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sportmatch.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompeticaoDetalhes(
    model: CompeticaoDetalhesUiModel,
    onBackClick: () -> Unit,
    onInscreverClick: () -> Unit
) {
    val primaryColor = Color(0xFFF26522)
    val competicao = model.entity
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = model.titulo,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryColor
                )
            )
        },
        bottomBar = {
            Button(
                onClick = onInscreverClick,
                enabled = !model.estaInscrito && model.vagasDisponiveis > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor,
                    disabledContainerColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = if (model.estaInscrito) "Inscrição Realizada" else "Inscrever-se",
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Gray)
            ) {
                val url = competicao.imagemUrl ?: ""
                val imagemModel = if (url.startsWith("http")) {
                    url
                } else {
                    R.drawable.placeholder_volei
                }
                AsyncImage(
                    model = imagemModel,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White.copy(alpha = 0.9f),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "\uD83D\uDC65 ${competicao.vagasPreenchidas}/${competicao.total}",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            InfoCard(titulo = "Detalhes", preco = model.precoFormatado) {
                DetailRow("Esporte:", competicao.esporte)
                DetailRow("Modalidade:", competicao.modalidade)
                DetailRow("Categoria:", competicao.categoria)
                DetailRow("Subcategoria:", competicao.formato)
                DetailRow("Formato da competição:", competicao.formato)
                DetailRow("Nível de acessibilidade:", competicao.tipoAcessibilidade)
                DetailRow("Máximo de atletas por equipe:", "${model.maximoAtletas}")
                DetailRow("Total de vagas:", "${competicao.total}")
                DetailRow("Vagas Restantes:", "${model.vagasDisponiveis}")
            }
            InfoCard(titulo = "Local") {
                Text(text = competicao.local)
            }
            InfoCard(titulo = "Cronograma") {
                CronogramaItem("Início das Inscrições", competicao.inicioInscricao)
                CronogramaItem("Término das Inscrições", competicao.finalInscricao)
                CronogramaItem("Início da competição", competicao.inicioCompeticao)
                CronogramaItem("Término da competição", competicao.finalCompeticao)
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}