package com.example.sportmatch.ui.competicoes

import CampeonatoViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastroCompeticao3(viewModel: CampeonatoViewModel = viewModel(), onNext: () -> Unit, onBefore: () -> Unit) {
    val scrollState = rememberScrollState()
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
                onClick = { /* ação */ },
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