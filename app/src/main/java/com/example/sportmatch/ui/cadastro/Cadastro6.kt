package com.example.sportmatch.ui.cadastro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
fun Cadastro6(onNavigateToLogin: () -> Unit){

    var futebol by remember { mutableStateOf("Futebol") }


    Column (modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Top){
        Text(
            text = "Quais os seus esportes de interesse?",
            color = Color.Black,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(64.dp)
        )

        Button(onClick = {

        }) {
            Text(futebol)
        }

        Spacer(modifier = Modifier.height(512.dp))
        Button(
            onClick = {
                onNavigateToLogin()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro6Preview(){
    Cadastro6({})
}