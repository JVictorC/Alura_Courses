package com.example.ceep.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ceep.R
import com.example.ceep.dao.NotaDAO
import com.example.ceep.databinding.ActivityAdicionarNotaBinding
import com.example.ceep.model.Nota

class AdicionarNotaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdicionarNotaBinding
    private var dao = NotaDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarNotaBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_adicionar_nota, menu)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_formulario_nota_ic_salva) {
            onSaveNewNota()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onSaveNewNota() {
        val textTitle = binding.formularioNotaTitulo.text.toString()
        val textDescription = binding.formularioNotaDescricao.text.toString()

        val nota = Nota(textTitle, textDescription)

        dao.insere(nota)

        finish()
    }
}