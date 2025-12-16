package com.example.sportmatch.service

import CompeticaoSupabase
import android.util.Log
import com.example.sportmatch.supabase.SupabaseClientInstance
import io.github.jan.supabase.postgrest.postgrest

object CompeticaoService {

    private val supabase = SupabaseClientInstance.client

    suspend fun postCompeticao(
        competicao: CompeticaoSupabase
    ): Boolean {
        return try {
            supabase
                .postgrest["competicoes"]
                .insert(competicao) {
                    select()
                }

            true

        } catch (e: Exception) {
            Log.e("COMPETICAO", "Erro ao salvar: ${e.message}")
            false
        }
    }

    suspend fun getCompeticoes(): List<CompeticaoSupabase> {
        return try {
            val result = supabase
                .postgrest["competicoes"]
                .select()

            result.decodeList<CompeticaoSupabase>()

        } catch (e: Exception) {
            Log.e("COMPETICAO", "Erro ao buscar: ${e.message}")
            emptyList()
        }
    }
}
