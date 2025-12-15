package com.example.sportmatch.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sportmatch.data.database.entities.Competicao
import com.example.sportmatch.ui.theme.Branco

@Composable
fun CardCompeticao(competicao: Competicao, onNavigateToDetail: () -> Unit ) {
    Surface (
        color = Branco
    ){
        Row(){
            Column(){
                Text(competicao.nome)
                Row {
                    Text(competicao.esporte)
                    Text(competicao.categoria)
                    Text("R$ " + competicao.valor.toString())
                }
                Text(competicao.inicioCompeticao + " - " + competicao.finalCompeticao)
                Text(competicao.local)
                Text("Ver mais",
                    modifier = Modifier.clickable {
                        onNavigateToDetail()
                    }
                )
            }
            AsyncImage(
                model = competicao.imagemUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Preview
@Composable
fun CardCompeticaoPreview() {
    CardCompeticao(competicao = Competicao(), onNavigateToDetail = {})
}