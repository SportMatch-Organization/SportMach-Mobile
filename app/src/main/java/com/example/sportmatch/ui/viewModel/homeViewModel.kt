package com.example.sportmatch.ui.viewModel

import CompeticaoSupabase
import EspacoEsportivoSupabase
import EsportesData
import android.app.Application
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.sportmatch.data.database.SportMatchDatabase
import com.example.sportmatch.service.CompeticaoService
import com.example.sportmatch.service.EspacoEsportivoService
import io.github.jan.supabase.storage.storage
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel(application: Application) : AndroidViewModel(application) {

    var carregando by mutableStateOf(false)

    var competicoes by mutableStateOf<List<CompeticaoSupabase>>(emptyList())
        private set
    suspend fun buscarCompeticoesSupabase() {
        carregando = true

        try {
            val lista = CompeticaoService.getCompeticoes()

            competicoes = lista.reversed()
            Log.d("HomeViewModel", "Competições carregadas: $competicoes")
        } catch (e: Exception) {
            Log.e("SUPABASE", "Erro ao buscar: ${e.message}")
        } finally {
            carregando = false
        }
    }

}
