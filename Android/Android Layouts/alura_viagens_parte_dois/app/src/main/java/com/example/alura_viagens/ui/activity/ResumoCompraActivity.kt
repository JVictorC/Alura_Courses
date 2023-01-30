package com.example.alura_viagens.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.alura_viagens.R
import com.example.alura_viagens.core.Constantes
import com.example.alura_viagens.model.PacoteModel
import java.math.BigDecimal

class ResumoCompraActivity : AppCompatActivity() {
    private lateinit var textLocal: TextView
    private lateinit var textPreco: TextView
    private lateinit var textData: TextView
    private lateinit var imagemPacote: ImageView
    private lateinit var pacote: PacoteModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumo_compra)

        val actionBar = supportActionBar
        actionBar?.title = "Resumo Compra"


        iniciarCampos()
        popularView()
    }

    private fun iniciarCampos() {
        if(intent.hasExtra(Constantes.keyPacoteInExtra)) {
            pacote = intent.getSerializableExtra(Constantes.keyPacoteInExtra) as PacoteModel
        }

        textLocal = findViewById(R.id.activity_resumo_compra_text_nome_cidade)
        textPreco = findViewById(R.id.activity_resumo_compra_text_preco)
        textData = findViewById(R.id.activity_resumo_compra_text_data)
        imagemPacote = findViewById(R.id.activity_resumo_compra_image_cidade)
    }

    private fun popularView() {
        textLocal.text = pacote.local
        textPreco.text = pacote.createPrecoComBRFormart()
        textData.text = pacote.createDataFormatada()

        imagemPacote.setImageDrawable(pacote.createDrawableImage(this))
    }
}