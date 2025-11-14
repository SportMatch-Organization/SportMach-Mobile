package com.example.sportmatch.ui.competicoes

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportmatch.database.SportMatchDatabase
import com.example.sportmatch.model.CampeonatoViewModel
import com.example.sportmatch.ui.components.CustomButton
import com.example.sportmatch.ui.components.CustomCheckBox
import com.example.sportmatch.ui.components.CustomCheckBoxGroup
import com.example.sportmatch.ui.components.CustomDateTimePicker
import com.example.sportmatch.ui.components.CustomRadioGroup
import com.example.sportmatch.ui.components.CustomSelectField
import com.example.sportmatch.ui.components.CustomText
import com.example.sportmatch.ui.components.CustomTextField
import com.example.sportmatch.ui.components.ReviseSeusDados
import com.example.sportmatch.ui.components.TextType
import com.example.sportmatch.ui.theme.StrokeBt
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastroCompeticao3(viewModel: CampeonatoViewModel = viewModel(), onBefore: () -> Unit) {
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
            CustomText("Revise os dados", TextType.HEADLINE, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(60.dp))
            ReviseSeusDados(viewModel)
            Spacer(modifier = Modifier.height(34.dp))
            CustomButton(
                text = "Cadastrar",
                onClick = {
                    carregando = true
                    scope.launch {
                        try {
                            viewModel.salvarCompeticao()
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