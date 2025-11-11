package com.example.sportmatch.ui.components

import CampeonatoViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReviseSeusDados(viewModel: CampeonatoViewModel = viewModel()){
    Column {
        CustomText("Informações da competição", TextType.TITULO)
        Spacer(modifier = Modifier.height(height = 8.dp))
        RowData("Nome", value = viewModel.nome)
        RowData("Descrição", value = viewModel.descricao)
        RowData("Esporte", value = viewModel.esporteSelecionado)
        RowData("Modalidade", value = viewModel.modalidadeSelecionada)
        RowData("Formato", value = viewModel.formatoSelecionado)
        RowData("Categoria", value = viewModel.categoriaSelecionada)
        if(viewModel.acessibilidadeSelecionada.isNotBlank()){
            CustomText(
                "Acessibilidade: ",
                TextType.SUBTITULO,
                color = MaterialTheme.colorScheme.secondary
            )
            CustomText(viewModel.acessibilidadeSelecionada, TextType.SUBTITULO, color = Color(0xFF8C8C8C), fontWeight = FontWeight.Normal)
            CustomText("Descrição da acessibilidade: ", TextType.SUBTITULO, color = MaterialTheme.colorScheme.secondary)
            CustomText(viewModel.descricaoAcessibilidade, TextType.SUBTITULO, color = Color(0xFF8C8C8C), fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.height(16.dp))
        }
        if(viewModel.acessibilidadeSelecionada.isBlank()){
            Spacer(modifier = Modifier.height(16.dp))
        }
        CustomText("Informações para os participantes", TextType.TITULO)
        Spacer(modifier = Modifier.height(height = 8.dp))
        RowData("Tipo de esporte", value = viewModel.tipoSelecionado)
        RowData("Quantidade de ${if (viewModel.tipoSelecionado == "Individual") "pessoas" else "equipes"}"
            , value = viewModel.total)
        if(viewModel.tipoSelecionado == "Coletivo"){
            CustomText("Quantidade de pessoas por equipe:", TextType.SUBTITULO)
            RowData("Quantidade mínima", value = viewModel.minimoEquipe)
            RowData("Quantidade máxima", value = viewModel.maximoEquipe)
        }
            RowData("Faixa etária", value = viewModel.faixaEtariaSelecionada)
        if(viewModel.faixaEtariaSelecionada == "Específica"){
            RowData("Idade mínima", value = viewModel.minimoEquipe)
            RowData("Idade máxima", value = viewModel.maximoEquipe)
        }
        RowData("Local", value = viewModel.local)
        if (!viewModel.competicaoGratuita){
            RowData("valor", value = viewModel.valor)
            CustomText("Formas de pagamento: ", TextType.SUBTITULO, color = MaterialTheme.colorScheme.secondary)
            CustomText(viewModel.formasDeDePagamentoSelecionado.joinToString(", "), TextType.SUBTITULO, color = Color(0xFF8C8C8C), fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.height(16.dp))
        }else{
            Spacer(modifier = Modifier.height(16.dp))
        }
        CustomText("Cronograma", TextType.TITULO)
        Spacer(modifier = Modifier.height(8.dp))
        CustomText("Inscrições:", TextType.SUBTITULO)
        RowData("Início", value = viewModel.dataIniciaInscricao?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))?:"")
        RowData("Fim", value = viewModel.dataIniciaInscricao?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))?:"")
        Spacer(modifier = Modifier.height(8.dp))
        CustomText("Competição:", TextType.SUBTITULO)
        RowData("Início", value = viewModel.dataIniciaCompeticao?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))?:"")
        RowData("Fim", value = viewModel.dataIniciaCompeticao?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))?:"")
    }
}