package com.example.sportmatch.ui.screens.competicoes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.sportmatch.data.database.SportMatchDatabase
import com.example.sportmatch.data.database.entities.Competicao
import com.example.sportmatch.ui.components.CardCompeticao
import com.example.sportmatch.ui.theme.Branco

@Composable
fun ExploreCompeticao(onNavigateToDetail: () -> Unit = {}) {
    val context = LocalContext.current
    var competicoes by remember { mutableStateOf(emptyList<Competicao>())}

    LaunchedEffect(Unit){
        competicoes = SportMatchDatabase.getDatabase(context = context).competicaoDao().getTodasCompeticoes()
    }

    Scaffold (
        containerColor = Branco
    ){
        innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)){
            items(competicoes) {
                competicao ->
                CardCompeticao(
                    competicao, onNavigateToDetail
                )
            }
        }
    }
}