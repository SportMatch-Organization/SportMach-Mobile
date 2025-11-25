package com.example.sportmatch.ui

import android.os.Bundle
import android.content.Intent // Necessário para usar Intent, embora não explicitamente chamado no trecho atual
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sportmatch.R
import com.example.sportmatch.model.Evento

class DetalhesEventoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_evento)

        val evento = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("evento_detalhes", Evento::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("evento_detalhes") as? Evento
        }

        if (evento != null) {

            findViewById<TextView>(R.id.text_titulo_evento).text = evento.titulo
            findViewById<TextView>(R.id.text_preco).text = "R$ ${"%.2f".format(evento.preco)}"



            fun preencherDetalhe(viewId: Int, chave: String, valor: Any) {
                val view = findViewById<android.view.View>(viewId)
                view.findViewById<TextView>(R.id.text_chave).text = chave
                view.findViewById<TextView>(R.id.text_valor).text = valor.toString()
            }

            preencherDetalhe(R.id.item_esporte, "Esporte:", evento.esporte)
            preencherDetalhe(R.id.item_modalidade, "Modalidade:", evento.modalidade)
            preencherDetalhe(R.id.item_categoria, "Categoria:", evento.categoria)
            preencherDetalhe(R.id.item_subcategoria, "Subcategoria:", evento.subcategoria)
            preencherDetalhe(R.id.item_formato, "Formato da competição:", evento.formatoCompeticao)
            preencherDetalhe(R.id.item_acessibilidade, "Nível de acessibilidade:", evento.nivelAcessibilidade)
            preencherDetalhe(R.id.item_max_atletas, "Máximo de atletas por equipe:", evento.maxAtletas)
            preencherDetalhe(R.id.item_min_atletas, "Mínimo de atletas por equipe:", evento.minAtletas)


            fun preencherCronograma(viewId: Int, descricao: String, data: String) {
                val view = findViewById<android.view.View>(viewId)
                view.findViewById<TextView>(R.id.text_descricao).text = descricao
                view.findViewById<TextView>(R.id.text_data).text = data
            }

            preencherCronograma(R.id.item_data_inicio_inscricoes, "Início das inscrições", evento.dataInicioInscricoes)
            preencherCronograma(R.id.item_data_fim_inscricoes, "Fim das inscrições", evento.dataFimInscricoes)
            preencherCronograma(R.id.item_inicio_competicao, "Início de competição: ${evento.inicioCompeticao}", "") // Ajustei a descrição para incluir a hora, se for o caso
            preencherCronograma(R.id.item_fim_competicao, "Fim da competição: ${evento.fimCompeticao}", "") // Ajustei a descrição para incluir a hora, se for o caso

        } else {
            Toast.makeText(this, "Erro: Detalhes do evento não carregados.", Toast.LENGTH_LONG).show()
            finish() // Fecha a tela e volta para a anterior
        }
    }
}