package com.example.sportmatch.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.sportmatch.R
import com.example.sportmatch.model.Evento

class DetalhesEventoActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var imageCounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Associa esta Activity ao layout XML
        setContentView(R.layout.activity_detalhes_evento)

        // Inicializa as Views do carrossel
        viewPager = findViewById(R.id.view_pager_carrossel)
        imageCounter = findViewById(R.id.text_contador_imagens)

        // Configura o botão de voltar
        val btnVoltar = findViewById<ImageView>(R.id.btn_voltar)
        btnVoltar.setOnClickListener {
            finish() // Fecha a Activity atual e retorna à anterior
        }

        // Tenta receber o objeto Evento (que agora tem List<String>)
        val evento = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("evento_detalhes", Evento::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("evento_detalhes") as? Evento
        }

        if (evento != null) {
            // Se o objeto Evento for encontrado, preenchemos os elementos da tela

            // 1. CONFIGURAÇÃO DO CARROSSEL
            if (evento.urlsImagens.isNotEmpty()) {
                val adapter = CarrosselAdapter(evento.urlsImagens)
                viewPager.adapter = adapter

                // Configura o contador inicial
                imageCounter.text = "1/${evento.urlsImagens.size}"

                // Listener para atualizar o contador ao deslizar
                viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        imageCounter.text = "${position + 1}/${evento.urlsImagens.size}"
                    }
                })
            } else {
                // Se não houver imagens, exibe uma placeholder e esconde o contador
                // Assumindo que placeholder_volei é um Drawable/Cor
                viewPager.setBackgroundResource(R.drawable.placeholder_volei)
                imageCounter.visibility = View.GONE
            }

            // 2. Preenchimento do Cabeçalho e Preço
            findViewById<TextView>(R.id.text_titulo_evento).text = evento.titulo
            findViewById<TextView>(R.id.text_preco).text = "R$ ${"%.2f".format(evento.preco)}"


            // 3. Preenchimento do Card de Detalhes

            // Função auxiliar para preencher os itens que usam o item_detalhe_evento.xml
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


            // 4. Preenchimento do Card de Cronograma

            // Função auxiliar para preencher os itens que usam o item_cronograma.xml
            fun preencherCronograma(viewId: Int, descricao: String, data: String) {
                val view = findViewById<android.view.View>(viewId)
                view.findViewById<TextView>(R.id.text_descricao).text = descricao
                view.findViewById<TextView>(R.id.text_data).text = data
            }

            preencherCronograma(R.id.item_data_inicio_inscricoes, "Início das inscrições", evento.dataInicioInscricoes)
            preencherCronograma(R.id.item_data_fim_inscricoes, "Fim das inscrições", evento.dataFimInscricoes)
            preencherCronograma(R.id.item_inicio_competicao, "Início de competição: ${evento.inicioCompeticao}", "")
            preencherCronograma(R.id.item_fim_competicao, "Fim da competição: ${evento.fimCompeticao}", "")

        } else {
            // Caso não receba dados (erro ou navegação incorreta)
            Toast.makeText(this, "Erro: Detalhes do evento não carregados.", Toast.LENGTH_LONG).show()
            finish() // Fecha a tela e volta para a anterior
        }
    }
}