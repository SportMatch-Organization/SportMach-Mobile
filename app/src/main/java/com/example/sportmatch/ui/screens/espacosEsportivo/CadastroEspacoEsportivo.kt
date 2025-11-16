package com.example.sportmatch.ui.screens.espacosEsportivo

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportmatch.ui.components.CustomButton
import com.example.sportmatch.ui.components.CustomMultiSelectField
import com.example.sportmatch.ui.components.CustomSelectField
import com.example.sportmatch.ui.components.CustomText
import com.example.sportmatch.ui.components.CustomTextField
import com.example.sportmatch.ui.components.TextType
import com.example.sportmatch.ui.theme.StrokeBt
import com.example.sportmatch.ui.viewModel.EspacoEsportivoViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastroEspacoEsportivo(viewModel: EspacoEsportivoViewModel = viewModel(), onBefore: () -> Unit) {
    val scrollState = rememberScrollState()
    var carregando by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        IconButton(onClick = { onBefore() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.Red,
                contentDescription = "Voltar"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            CustomText("Informações do espaço esportivo", TextType.HEADLINE, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(60.dp))
            CustomTextField(
                value = viewModel.nome,
                onValueChange = { viewModel.nome = it },
                label = "Nome do espaço esportivo",
                space =false
            )
            CustomTextField(
                value = viewModel.descricao,
                onValueChange = { viewModel.descricao = it },
                label = "Descrição",
                singleLine = false,
                maxLines = 3
            )
            CustomSelectField(
                label = "Tipo de espaço",
                options = viewModel.tiposEspaco,
                selectedValue = viewModel.tipoSelecionado,
                onValueChange = { viewModel.tipoSelecionado = it },
            )
            CustomTextField(
                value = viewModel.endereco,
                onValueChange = { viewModel.endereco = it },
                label = "Endereço completo",
                singleLine = false,
                maxLines = 3
            )
            CustomTextField(
                value = viewModel.telefone,
                onValueChange = { viewModel.telefone = it },
                label = "Telefone",
            )
            CustomTextField(
                value = viewModel.maximoAtletas,
                onValueChange = { viewModel.maximoAtletas = it },
                label = "Capacidade máxima de atletas",
            )
            CustomMultiSelectField(
                label = "Esportes suportados",
                options = viewModel.esportesSuportados,
                selectedValues = viewModel.esportesSuportadosSelecionados,
                onValueChange = { viewModel.esportesSuportadosSelecionados = it }
            )
            Spacer(modifier = Modifier.height(34.dp))
            CustomButton(
                text = "Cadastrar",
                onClick = {
                    carregando = true
                    scope.launch {
                        try {
//                            viewModel.salvarCompeticao()
                            carregando = false
                            Toast.makeText(context, "Competição cadastrada com sucesso!", Toast.LENGTH_LONG).show()
                        } catch (error: Exception) {
                            carregando = false
                            Toast.makeText(context, "Falha no Cadastro: ${error.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                enabled = !carregando
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                text = "Voltar",
                onClick = { onBefore() },
                modifier = Modifier
                    .fillMaxWidth().height(50.dp)
                    .border(2.dp, StrokeBt, shape = RoundedCornerShape(4.dp)),
                backgroundColor = Color.White,
                textColor = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(40.dp))

        }
    }
}