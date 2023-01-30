package com.example.alura_viagens.ui.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.alura_viagens.R
import com.example.alura_viagens.dao.PacoteDAO
import com.example.alura_viagens.ui.adapters.ListaPacotesAdapter


class ListaPacotesActivity : AppCompatActivity() {
    lateinit var litaPacotes: ListView
    var dao = PacoteDAO()
    var adapterList = ListaPacotesAdapter(dao.lista(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pacotes)

        val actionBar = supportActionBar

        actionBar?.title = "Pacotes"
        inicializarCampos()
        popularLista()
    }



    private fun inicializarCampos() {
        litaPacotes = findViewById(R.id.activity_lista_pacotes_list_view)
    }

    private fun popularLista() {
        litaPacotes.adapter = adapterList
    }




}