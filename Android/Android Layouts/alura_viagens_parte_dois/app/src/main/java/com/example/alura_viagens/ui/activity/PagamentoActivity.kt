package com.example.alura_viagens.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.alura_viagens.R
import com.example.alura_viagens.core.Constantes
import com.example.alura_viagens.model.PacoteModel
import java.math.BigDecimal

class PagamentoActivity : AppCompatActivity() {
    private lateinit var textPreco: TextView
    private lateinit var numeroCartaoEditText: EditText
    private lateinit var mmEditText: EditText
    private lateinit var aaEditText: EditText
    private lateinit var cvvEditText: EditText
    private lateinit var nomeCartaoEditText: EditText
    private lateinit var pacote: PacoteModel
    private lateinit var buttonFinalizar: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagamento)

        val actionBar = supportActionBar

        actionBar?.title = "Pagamento"

        iniciarCampos()
        popularView()
        setListeners()
    }

    private fun setListeners() {
        buttonFinalizar.setOnClickListener {
           val intent = Intent(this, ResumoCompraActivity::class.java)

            intent.putExtra(Constantes.keyPacoteInExtra, pacote)

            startActivity(intent)
        }
    }

    private fun iniciarCampos() {
        if(intent.hasExtra(Constantes.keyPacoteInExtra)) {
            pacote = intent.getSerializableExtra(Constantes.keyPacoteInExtra) as PacoteModel
        }

        textPreco = findViewById(R.id.activity_pagamento_text_valor_compra)
        numeroCartaoEditText = findViewById(R.id.activity_pagamento_edit_text_cartao)
        mmEditText = findViewById(R.id.activity_pagamento_edit_text_mes)
        aaEditText = findViewById(R.id.activity_pagamento_edit_text_ano)
        cvvEditText = findViewById(R.id.activity_pagamento_edit_text_cvv)
        nomeCartaoEditText = findViewById(R.id.activity_pagamento_edit_text_nome_cartao)
        buttonFinalizar = findViewById(R.id.activity_pagamento_button_finaliza_compra)
    }

    private fun popularView() {
        textPreco.text = pacote.createPrecoComBRFormart()

    }

}