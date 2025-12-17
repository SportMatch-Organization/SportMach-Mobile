package com.example.sportmatch.ui.screens.pesquisar

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportmatch.ui.viewModel.CompeticaoDetalhesViewModel

@Composable
fun CompeticaoDetalhesRoute(
    competicaoId: Int,
    onBackClick: () -> Unit,
    viewModel: CompeticaoDetalhesViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(competicaoId) {
        viewModel.carregarCompeticao(competicaoId)
    }

    uiState.competicao?.let { model ->
        CompeticaoDetalhes(
            model = model,
            onBackClick = onBackClick,
            onInscreverClick = { viewModel.realizarInscricao() }
        )
    }
}