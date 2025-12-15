package com.example.sportmatch.ui.screens.competicoes

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.sportmatch.model.CampeonatoViewModel
import com.example.sportmatch.ui.components.CustomButton
import com.example.sportmatch.ui.components.CustomImagePicker
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
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        viewModel.setImagem(uri)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.Red,
                contentDescription = "Voltar"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(color = Color.White)
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
                label = "Selecione a categoria:",
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
            CustomImagePicker(
                "Escolha abaixo a imagem da competição: ",
                viewModel.imagemUri,  { uri ->
                    viewModel.setImagem(uri)
                })
            Spacer(modifier = Modifier.height(16.dp))
            CustomText(
                "Escolha abaixo a imagem da competição: ",
                TextType.SUBTITULO,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(backgroundColor = MaterialTheme.colorScheme.secondary,
                text = "Selecionar imagem",
                onClick = { launcher.launch("image/*") },
                icon = {
                    Icon(
                        imageVector = Icons.Default.ImageSearch,
                        tint = Color.White,
                        contentDescription = "Voltar"
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
            if(viewModel.imagemUri !== null){
                AsyncImage(
                    model = viewModel.imagemUri,
                    contentDescription = null,
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Crop
                )
            }
            }
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
