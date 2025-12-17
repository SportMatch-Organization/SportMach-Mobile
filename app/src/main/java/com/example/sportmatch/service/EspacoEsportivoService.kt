package com.example.sportmatch.service

import EspacoEsportivoSupabase
import android.util.Log
import com.example.sportmatch.supabase.SupabaseClientInstance
import io.github.jan.supabase.postgrest.postgrest

object EspacoEsportivoService {
    private val supabase = SupabaseClientInstance.client

    suspend fun postEspacoEsportivo(
        espaco: EspacoEsportivoSupabase
    ): Boolean {
        return try {
            supabase.postgrest["espacos_esportivos"].insert(espaco) { select() }
            true
        } catch (e: Exception) {
            Log.e("SUPABASE", "Erro ao salvar: ${e.message}")
            false
        }
    }

    suspend fun getEspacosEsportivos(): List<EspacoEsportivoSupabase> {
        return try {
            val result = supabase
                .postgrest["espacos_esportivos"]
                .select()

            result.decodeList<EspacoEsportivoSupabase>()

        } catch (e: Exception) {
            Log.e("SUPABASE", "Erro ao buscar: ${e.message}")
            emptyList()
        }
    }
}
