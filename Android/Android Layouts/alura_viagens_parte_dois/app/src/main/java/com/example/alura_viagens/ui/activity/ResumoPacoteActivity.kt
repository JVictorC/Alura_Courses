package com.example.alura_viagens.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.alura_viagens.R
import com.example.alura_viagens.core.Constantes
import com.example.alura_viagens.model.PacoteModel
import java.math.BigDecimal

class ResumoPacoteActivity : AppCompatActivity() {
    private lateinit var textLocal: TextView
    private lateinit var textPreco: TextView
    private lateinit var textDias: TextView
    private lateinit var textData: TextView
    private lateinit var imagemPacote: ImageView
    private lateinit var buttonComprar: AppCompatButton
    private lateinit var pacote: PacoteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumo_pacote)

        val actionBar = supportActionBar
        actionBar?.title = "Resumo Pacote"

        iniciarCampos()
        popularView()
        setListeners()
    }

    private fun setListeners() {
        buttonComprar.setOnClickListener {
            val intent = Intent(this, PagamentoActivity::class.java)

            intent.putExtra(Constantes.keyPacoteInExtra, pacote)


            startActivity(intent)
        }
    }

    private fun iniciarCampos() {
        if(intent.hasExtra(Constantes.keyPacoteInExtra)) {
            pacote = intent.getSerializableExtra(Constantes.keyPacoteInExtra) as PacoteModel
        }

        textLocal = findViewById(R.id.resumo_pacote_local_pacote)
        textPreco = findViewById(R.id.resumo_pacote_preco_pacote)
        textDias = findViewById(R.id.resumo_pacote_dias_pacote)
        textData = findViewById(R.id.resumo_pacote_data_pacote)
        imagemPacote = findViewById(R.id.resumo_pacote_imagem_pacote)
        buttonComprar = findViewById(R.id.resumo_pacote_button_pacote)
    }

    private fun popularView() {
        textLocal.text = pacote.local
        textPreco.text = pacote.createPrecoComBRFormart()
        textDias.text = pacote.diasString
        textData.text = pacote.createDataFormatada()

        imagemPacote.setImageDrawable(pacote.createDrawableImage(this))
    }
}