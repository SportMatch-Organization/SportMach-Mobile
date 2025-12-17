package com.example.sportmatch.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material3.Icon
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.sportmatch.ui.theme.DisableColor

@Composable
fun CustomImagePicker(
    title: String,
    imagemUri: Uri?,
    selectImage: (Uri?) -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectImage(uri)
    }

    Column {
        Spacer(modifier = Modifier.height(16.dp))
        CustomText(
            title,
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
            if(imagemUri !== null){
                AsyncImage(
                    model = imagemUri,
                    contentDescription = null,
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
