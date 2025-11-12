package com.example.sportmatch.ui.screens.competicoes

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportmatch.model.CampeonatoViewModel
import com.example.sportmatch.ui.components.CustomButton
import com.example.sportmatch.ui.components.CustomSelectField
import com.example.sportmatch.ui.components.CustomTextField
import com.example.sportmatch.ui.components.CustomRadioGroup
import com.example.sportmatch.ui.components.CustomText
import com.example.sportmatch.ui.components.TextType

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroCompeticao(viewModel: CampeonatoViewModel = viewModel(), onNext: () -> Unit){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        IconButton(onClick = { onNext() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.Red,
                contentDescription = "Voltar"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White).padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            CustomText("Informações da competição", TextType.HEADLINE, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(60.dp))
            CustomTextField(
                value = viewModel.nome,
                onValueChange = { viewModel.nome = it },
                label = "Nome da competição",
                space = false
            )
            CustomTextField(
                value = viewModel.descricao,
                onValueChange = { viewModel.descricao = it },
                label = "Descrição"
            )

            CustomSelectField(
                label = "Esporte",
                options = viewModel.opcoesEsportes,
                selectedValue = viewModel.esporteSelecionado,
                onValueChange = {
                    viewModel.esporteSelecionado = it
                    viewModel.limparCampos()
                }
            )

            if (viewModel.esporteAtual !== null) CustomSelectField(
                label = "Modalidade",
                options = viewModel.modalidades,
                selectedValue = viewModel.modalidadeSelecionada,
                onValueChange = { viewModel.modalidadeSelecionada = it },
            )

            if (viewModel.esporteAtual !== null) CustomSelectField(
                label = "Formato",
                options = viewModel.formatos,
                selectedValue = viewModel.formatoSelecionado,
                onValueChange = { viewModel.formatoSelecionado = it },
            )

            CustomRadioGroup(
                label = "Selecione o categoria:",
                options = viewModel.categorias,
                selectedValue = viewModel.categoriaSelecionada,
                onValueChange = { viewModel.categoriaSelecionada = it }
            )

            if (viewModel.esporteAtual !== null) CustomSelectField(
                label = "Tipo de acessibilidade",
                options = viewModel.acessibilidades,
                selectedValue = viewModel.acessibilidadeSelecionada,
                onValueChange = { viewModel.acessibilidadeSelecionada = it },
            )

            if (viewModel.acessibilidadeSelecionada !== "") CustomTextField(
                value = viewModel.descricaoAcessibilidade,
                onValueChange = { viewModel.descricaoAcessibilidade = it },
                label = "Descreva como é a acessibilidade"
            )
            Spacer(modifier = Modifier.height(34.dp))
            CustomButton(
                enabled = viewModel.camposObrigatorios1,
                text = "Próximo",
                onClick = { onNext() },
            )
            Spacer(modifier = Modifier.height(40.dp))

        }
    }
}
