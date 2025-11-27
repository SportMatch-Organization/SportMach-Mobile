package com.example.sportmatch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportmatch.R

/**
 * Adapter para exibir um carrossel de imagens (URLs ou caminhos de recurso) usando ViewPager2.
 */
class CarrosselAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<CarrosselAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_carrossel_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_imagem_carrossel, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageUrls[position]

        // --- LÓGICA DE CARREGAMENTO DA IMAGEM ---
        // Se a URL contém nosso placeholder, carrega o drawable.
        if (imageUrl.contains("placeholder_volei")) {
            holder.imageView.setImageResource(R.drawable.placeholder_volei)
        } else {
            // Caso contrário, usa uma cor sólida para simular uma imagem diferente.
            // VOCÊ DEVE SUBSTITUIR ISSO PELA LÓGICA DE CARREGAMENTO DE IMAGEM REAL (Coil/Glide)
            val colors = listOf(0xFFCCCCCC.toInt(), 0xFFBBBBBB.toInt(), 0xFFAAAAAA.toInt())
            holder.imageView.setBackgroundColor(colors[position % colors.size])
        }
    }

    override fun getItemCount() = imageUrls.size
}