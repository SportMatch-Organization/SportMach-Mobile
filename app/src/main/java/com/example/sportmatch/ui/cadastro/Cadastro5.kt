package com.example.sportmatch.ui.cadastro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportmatch.model.CadastroViewModel

@Composable
fun Cadastro5(
    viewModel: CadastroViewModel = viewModel(),
    onNavigateToCadastro6: () -> Unit
){

    var estado by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var complemento by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
            verticalArrangement = Arrangement.Top) {
        Text(
            text = "Complete seus dados",
            color = Color.Black,
            fontSize = 24.sp
        )

        Text(
            text = "Falta pouco!",
            color = Color.Black,
            fontSize = 12.sp
        )

        Spacer(
            modifier = Modifier.height(64.dp)
        )

        Text("Estado")

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = estado,
            onValueChange = { estado = it },
            label = {
                Text("Digite seu estado")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Cidade")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = cidade,
            onValueChange = { cidade = it },
            label = {
                Text("Digite sua cidade")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Endereço")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = endereco,
            onValueChange = { endereco = it },
            label = {
                Text("Endereço")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.
            fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ){
            Surface (modifier = Modifier
                .weight(1f)) {
                Column {
                    Text("Número")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = numero,
                        onValueChange = { numero = it },
                        label = {
                            Text("n°")
                        }
                    )
                }
            }

            Surface(modifier = Modifier
                .weight(2f)){
                Column {
                    Text("Complemento")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(value = complemento,
                        onValueChange = {complemento = it},
                        label = {
                            Text("Complemento")
                        }
                    )
                }
            }



        }

        Spacer(modifier = Modifier.height(256.dp))
        Button(
            onClick = {
                endereco = "$estado, $cidade, $numero, $complemento"
                viewModel.setEndereco(endereco)
                onNavigateToCadastro6()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Próximo")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro5Preview(){
    Cadastro5(viewModel { CadastroViewModel()}, {})
}