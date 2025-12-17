package com.example.sportmatch.ui.viewModel.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class EsportesInteresseViewModel: ViewModel() {

    val esportesInteresse = listOf<String>(
        "Ciclismo",
        "Corrida",
        "Musculação",
        "Futebol",
        "Basquete",
        "Vôlei",
        "Atletismo",
        "Boxe",
        "Futsal",
        "Jiu-Jitsu",
        "Handebol",
        "Judô",
        "Karatê",
        "Skate",
        "Surfe",
        "Futvôlei",
        "Tênis de mesa",
        "Tênis",
        "BMX",
        "Hipismo",
        "Cross-fit"
    )

    val esportesInteresseSelecionados = mutableListOf<String>()
}