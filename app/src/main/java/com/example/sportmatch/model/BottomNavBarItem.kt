package com.example.sportmatch.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavBarItem (
    val label: String,
    val icon: ImageVector,
    val route: String,
){

    object ExploreCompeticao: BottomNavBarItem(
        label = "explore",
        icon = Icons.Default.Home,
        route = "explore"
    )

    object Pesquisar: BottomNavBarItem(
        label = "Pesquisar",
        icon = Icons.Default.Search,
        route = "pesquisar"
    )

    object Notificacao: BottomNavBarItem(
        label = "Notificações",
        icon = Icons.Default.Notifications,
        route = "notificacoes"
    )

    object Perfil: BottomNavBarItem(
        label = "Perfil",
        icon = Icons.Default.Person,
        route = "PerfilOrganizador"
    )


}