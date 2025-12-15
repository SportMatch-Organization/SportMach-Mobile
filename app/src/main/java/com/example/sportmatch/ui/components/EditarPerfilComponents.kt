package com.example.sportmatch.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sportmatch.ui.theme.laranjaPrincipal
import com.example.sportmatch.ui.theme.TextoEscuro
import com.example.sportmatch.ui.theme.CorLabel
import com.example.sportmatch.ui.theme.CorFundoCampo
@Composable
fun CampoFormulario(
    label: String,
    valor: String,
    onValorChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = CorLabel,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
        )
        OutlinedTextField(
            value = valor,
            onValueChange = onValorChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = CorFundoCampo,
                unfocusedContainerColor = CorFundoCampo,
                disabledContainerColor = CorFundoCampo,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = laranjaPrincipal,
                focusedTextColor = TextoEscuro,
                unfocusedTextColor = TextoEscuro
            ),
            singleLine = true
        )
    }
}
@Composable
fun TituloSecao(titulo: String) {
    Text(
        text = titulo,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = TextoEscuro,
        modifier = Modifier.padding(vertical = 12.dp)
    )
}
@Composable
fun CampoSenha(
    label: String,
    valor: String,
    onValorChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF6A7C8F),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
        )
        OutlinedTextField(
            value = valor,
            onValueChange = onValorChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F4F8),
                unfocusedContainerColor = Color(0xFFF0F4F8),
                disabledContainerColor = Color(0xFFF0F4F8),
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = laranjaPrincipal,
                focusedTextColor = TextoEscuro,
                unfocusedTextColor = TextoEscuro
            ),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
    }
}
@Composable
fun HeaderEdicaoFoto(fotoUrl: String, aoClicarEditar: () -> Unit) {
    Box(contentAlignment = Alignment.BottomEnd) {
        AsyncImage(
            model = fotoUrl,
            contentDescription = "Foto de perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .border(3.dp, laranjaPrincipal, CircleShape)
                .padding(4.dp)
                .clip(CircleShape)
        )
        IconButton(
            onClick = aoClicarEditar,
            modifier = Modifier
                .size(32.dp)
                .background(laranjaPrincipal, CircleShape)
                .border(2.dp, Color.White, CircleShape)
        ) {
            Icon(Icons.Default.Edit, contentDescription = "Alterar foto", tint = Color.White, modifier = Modifier.size(16.dp))
        }
    }
}