package com.example.sportmatch.ui.competicoes

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
import com.example.sportmatch.model.CampeonatoViewModel
import com.example.sportmatch.ui.components.CustomButton
import com.example.sportmatch.ui.components.CustomCheckBox
import com.example.sportmatch.ui.components.CustomCheckBoxGroup
import com.example.sportmatch.ui.components.CustomDateTimePicker
import com.example.sportmatch.ui.components.CustomRadioGroup
import com.example.sportmatch.ui.components.CustomSelectField
import com.example.sportmatch.ui.components.CustomText
import com.example.sportmatch.ui.components.CustomTextField
import com.example.sportmatch.ui.components.TextType
import com.example.sportmatch.ui.theme.StrokeBt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastroCompeticao2(viewModel: CampeonatoViewModel = viewModel(), onNext: () -> Unit, onBefore: () -> Unit) {
    var TiposEsporte = listOf<String>("Individual", "Coletivo")
    var FaixasEtarias = listOf<String>("Qualquer idade", "Específica")
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
            CustomText("Informações para os participantes", TextType.HEADLINE, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(60.dp))
            CustomRadioGroup(
                label = "Tipo de esporte:",
                options = TiposEsporte,
                selectedValue = viewModel.tipoSelecionado,
                onValueChange = { viewModel.tipoSelecionado = it }
            )
            if (viewModel.tipoSelecionado != "") {
                CustomTextField(
                    value = viewModel.total,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            viewModel.total = newValue
                        }
                    },
                    label = if (viewModel.tipoSelecionado == "Individual")
                        "Total de pessoas"
                    else
                        "Total de equipes",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                if (viewModel.tipoSelecionado == "Coletivo") {
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomText("Limite de pessoas por equipe:", TextType.SUBTITULO)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CustomTextField(
                            value = viewModel.minimoEquipe,
                            onValueChange = { newValue ->
                                if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                                    viewModel.minimoEquipe = newValue
                                }
                            },
                            label = "Mínimo",
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        CustomTextField(
                            value = viewModel.maximoEquipe,
                            onValueChange = { newValue ->
                                if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                                    viewModel.maximoEquipe = newValue
                                }
                            },
                            label = "Máximo",
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            CustomRadioGroup(
                label = "Faixa etária:",
                options = FaixasEtarias,
                selectedValue = viewModel.faixaEtariaSelecionada,
                onValueChange = { viewModel.faixaEtariaSelecionada = it }
            )
            if (viewModel.faixaEtariaSelecionada == "Específica") {
                Spacer(modifier = Modifier.height(16.dp))
                CustomText("Idade dos participantes:", TextType.SUBTITULO)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CustomTextField(
                        value = viewModel.idadeMinima,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                                viewModel.idadeMinima = newValue
                            }
                        },
                        label = "Minima",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CustomTextField(
                        value = viewModel.idadeMaxima,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                                viewModel.idadeMaxima = newValue
                            }
                        },
                        label = "Máxima",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            CustomText("Datas", TextType.SUBTITULO)
            Spacer(modifier = Modifier.height(16.dp))
            CustomText("Inscrições:", TextType.SUBTITULO, fontSize = 14.sp)
            CustomDateTimePicker(
                label = "Data inicial",
                value = viewModel.dataIniciaInscricao,
                onValueChange = { novaDataHora ->
                    viewModel.dataIniciaInscricao = novaDataHora
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomDateTimePicker(
                label = "Data final",
                value = viewModel.dataFimInscricao,
                onValueChange = { novaDataHora ->
                    viewModel.dataFimInscricao = novaDataHora
                },
                isError = viewModel.dataIniciaInscricao?.let { inicio ->
                    viewModel.dataFimInscricao?.let { fim ->
                        inicio.isAfter(fim)
                    }
                } ?: false,
                mensagemDeErro = "A data final não pode ser antes da inicial."
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomText("Competição:", TextType.SUBTITULO, fontSize = 14.sp)
            CustomDateTimePicker(
                label = "Data Inícial",
                value = viewModel.dataIniciaCompeticao,
                onValueChange = { novaDataHora ->
                    viewModel.dataIniciaCompeticao = novaDataHora
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomDateTimePicker(
                label = "Data final",
                value = viewModel.dataFimCompeticao,
                onValueChange = { novaDataHora ->
                    viewModel.dataFimCompeticao = novaDataHora
                },
                isError = viewModel.dataIniciaCompeticao?.let { inicio ->
                    viewModel.dataFimCompeticao?.let { fim ->
                        inicio.isAfter(fim)
                    }
                } ?: false,
                mensagemDeErro = "A data final não pode ser antes da inicial."
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomSelectField(
                label = "Local da competição",
                options = viewModel.locais,
                selectedValue = viewModel.local,
                onValueChange = { viewModel.local = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomCheckBox(
                checked = viewModel.competicaoGratuita,
                onCheckedChange = {
                    viewModel.competicaoGratuita = it;
                    viewModel.valor = ""
                    viewModel.formasDeDePagamentoSelecionado = listOf()
                },
                label = "Competição gratuita",
            )

            if (!viewModel.competicaoGratuita) {
                CustomTextField(
                    value = viewModel.valor,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            viewModel.valor = newValue
                        }
                    },
                    label = "Valor da inscrição",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomCheckBoxGroup(
                    title = "Formas de pagagamento:",
                    options = viewModel.formasDePagamento,
                    selectedOptions = viewModel.formasDeDePagamentoSelecionado,
                    onSelectionChange = { viewModel.formasDeDePagamentoSelecionado = it },
                )

            }
            Spacer(modifier = Modifier.height(34.dp))
            CustomButton(
                text = "Próximo",
                onClick = { onNext() },
                enabled = viewModel.camposObrigatorios2
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