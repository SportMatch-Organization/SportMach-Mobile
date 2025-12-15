package com.example.sportmatch.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.storage

object SupabaseClientInstance {

    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = "https://tutbroveesexjdysfozn.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InR1dGJyb3ZlZXNleGpkeXNmb3puIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM0ODEwMjcsImV4cCI6MjA3OTA1NzAyN30.DM_d-V1szYfkZ8UHmP6zmDrP1Sen2WRgXVVhxs01QHQ"
        ) {
            install(io.github.jan.supabase.postgrest.Postgrest)
            install(io.github.jan.supabase.storage.Storage)
        }
    }
}
