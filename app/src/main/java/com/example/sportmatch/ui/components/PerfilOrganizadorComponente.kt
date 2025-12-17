package com.example.sportmatch.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sportmatch.ui.viewModel.ModeloOrganizador
import com.example.sportmatch.ui.theme.laranjaPrincipal
import com.example.sportmatch.ui.theme.TextoEscuro

@Composable
fun CabecalhoOrganizador(
    organizador: ModeloOrganizador,
    aoClicarEditar: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        AsyncImage(
            model = organizador.fotoUrl,
            contentDescription = "Logo da ${organizador.nome}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .border(3.dp, laranjaPrincipal, CircleShape)
                .padding(4.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = organizador.nome,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = TextoEscuro
        )
        Text(
            text = organizador.endereco,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFFFFF3E0), RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Icon(Icons.Default.Star, contentDescription = null, tint = laranjaPrincipal, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${organizador.nota} (${organizador.totalAvaliacoes} avaliações)",
                fontWeight = FontWeight.Bold,
                color = laranjaPrincipal,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = aoClicarEditar,
            colors = ButtonDefaults.buttonColors(containerColor = laranjaPrincipal),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Editar dados do organizador", color = Color.White)
        }
    }
}