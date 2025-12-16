package com.example.sportmatch.ui.components
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sportmatch.R
import com.example.sportmatch.data.database.entities.Competicao
import com.example.sportmatch.ui.theme.verdeTaxa
import com.example.sportmatch.ui.theme.cinzaChipFundo
import com.example.sportmatch.ui.theme.laranjaPrincipal
import com.example.sportmatch.ui.theme.cinzaTextoSecundario
@Composable
fun FilterChipComBotao(text: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = Color.DarkGray
        ),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(text, fontSize = 12.sp)
        Spacer(Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Abrir opções de $text",
            modifier = Modifier.size(18.dp),
            tint = laranjaPrincipal
        )
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CompeticaoCard(
    competicao: Competicao,
    onVerMaisClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onVerMaisClick(competicao.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = competicao.nome,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.DarkGray,
                            modifier = Modifier.weight(1f, fill = false)
                        )
                        Spacer(Modifier.width(8.dp))
                        ChipVisual(
                            text = "${competicao.vagasPreenchidas}/${competicao.total}",
                            icon = Icons.Filled.Group,
                            iconTint = Color.DarkGray
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ChipVisual(
                            text = competicao.esporte,
                            icon = Icons.Filled.SportsVolleyball,
                            iconTint = laranjaPrincipal
                        )
                        ChipVisual(
                            text = competicao.tipo,
                            icon = Icons.Filled.People,
                            iconTint = laranjaPrincipal
                        )
                        ChipVisual(
                            text = "R$ %.2f".format(competicao.valor ?: 0.0),
                            textColor = verdeTaxa,
                            fontWeight = FontWeight.Bold,
                            backgroundColor = verdeTaxa.copy(alpha = 0.1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.DateRange, contentDescription = "Data", modifier = Modifier.size(16.dp), tint = cinzaTextoSecundario)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(competicao.inicioCompeticao, fontSize = 12.sp, color = cinzaTextoSecundario)
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(Icons.Default.Schedule, contentDescription = "Horário", modifier = Modifier.size(16.dp), tint = cinzaTextoSecundario)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(competicao.inicioCompeticao.split(" - ").lastOrNull() ?: "", fontSize = 12.sp, color = cinzaTextoSecundario)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = "Local", modifier = Modifier.size(16.dp), tint = laranjaPrincipal)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(competicao.local, fontSize = 12.sp, color = cinzaTextoSecundario, maxLines = 1)
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onVerMaisClick(competicao.id) }
                        .padding(top = 10.dp)
                ) {
                    Text("Ver mais", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = laranjaPrincipal)
                    Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, modifier = Modifier.size(14.dp), tint = laranjaPrincipal)
                }
            }
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .padding(top = 12.dp, bottom = 12.dp, end = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                val url = competicao.imagemUrl ?: ""
                val imagemParaMostrar = if (url.startsWith("http")) {
                    url
                } else {
                    R.drawable.placeholder_volei
                }
                AsyncImage(
                    model = imagemParaMostrar,
                    contentDescription = competicao.nome,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                )
            }
        }
    }
}
@Composable
fun ChipVisual(
    text: String,
    icon: ImageVector? = null,
    iconTint: Color = Color.DarkGray,
    textColor: Color = Color.DarkGray,
    fontWeight: FontWeight = FontWeight.Medium,
    backgroundColor: Color = cinzaChipFundo
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(14.dp), tint = iconTint)
            Spacer(Modifier.width(4.dp))
        }
        Text(text, fontSize = 12.sp, color = textColor, fontWeight = fontWeight)
    }
}