package com.example.alura_viagens.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.alura_viagens.R
import com.example.alura_viagens.core.Constantes
import com.example.alura_viagens.dao.PacoteDAO
import com.example.alura_viagens.ui.adapters.ListaPacotesAdapter


class ListaPacotesActivity : AppCompatActivity() {
    private lateinit var litaPacotes: ListView
    private var dao = PacoteDAO()
    private var adapterList = ListaPacotesAdapter(dao.lista(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pacotes)

        val actionBar = supportActionBar

        actionBar?.title = "Pacotes"
        inicializarCampos()
        popularLista()
        setListeners()
    }


    private fun setListeners() {
        litaPacotes.setOnItemClickListener { _, _, position, _ ->
            handleClickItemLista(position)
        }

    }

    private fun inicializarCampos() {
        litaPacotes = findViewById(R.id.activity_lista_pacotes_list_view)
    }

    private fun popularLista() {
        litaPacotes.adapter = adapterList
    }



   private fun handleClickItemLista(position: Int) {
       val pacote = adapterList.getItem(position)

       val intent = Intent(this, ResumoPacoteActivity::class.java)

       intent.putExtra(Constantes.keyPacoteInExtra, pacote)

       startActivity(intent)
   }



}