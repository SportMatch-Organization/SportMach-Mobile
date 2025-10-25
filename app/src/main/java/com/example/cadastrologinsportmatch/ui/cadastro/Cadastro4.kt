package com.example.cadastrologinsportmatch.ui.cadastro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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

@Composable
fun Cadastro4(onNavigateToCadastro5: () -> Unit){

    var cep by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
            verticalArrangement = Arrangement.Top) {
        Text(
            text = "Qual o seu CEP?",
            color = Color.Black,
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier.height(64.dp)
        )

        Text("CEP")

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = cep,
            onValueChange = { cep = it },
            label = {
                Text("Digite seu Cpf")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(512.dp))

        Button(
            onClick = {
                onNavigateToCadastro5()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Próximo")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro4Preview(){
    Cadastro4({})
}