package com.example.ceep.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ceep.R
import com.example.ceep.dao.NotaDAO
import com.example.ceep.databinding.ActivityAdicionarNotaBinding
import com.example.ceep.model.Nota
import com.example.ceep.ui.Constantes

class AdicionarNotaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdicionarNotaBinding
    private var nota: Nota? = null
    private var indexNota: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarNotaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (intent.hasExtra(Constantes.keyNotaEditar)) {
            nota = intent.getSerializableExtra(Constantes.keyNotaEditar) as Nota
            indexNota = intent.getIntExtra(Constantes.keyIndexNotaEditar, 0)

            setNotaDataInView()
        }
    }

    private fun setNotaDataInView() {
        binding.formularioNotaTitulo.setText(nota?.titulo)
        binding.formularioNotaDescricao.setText(nota?.descricao)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_adicionar_nota, menu)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_formulario_nota_ic_salva) {
            if(nota == null) { onSaveNewNota() }
            else { onEditNota() }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onEditNota() {
        nota?.titulo = binding.formularioNotaTitulo.text.toString()
        nota?.descricao = binding.formularioNotaDescricao.text.toString()

        Intent().apply {
            putExtra(Constantes.keyNotaEditar, nota)
            putExtra(Constantes.keyIndexNotaEditar, indexNota.toString())

            setResult(Constantes.keyNotaRequestEditar, this)
        }

        finish()
    }

    private fun onSaveNewNota() {
        val textTitle = binding.formularioNotaTitulo.text.toString()
        val textDescription = binding.formularioNotaDescricao.text.toString()

        Intent().apply {
            putExtra(Constantes.keyNotaEditar, Nota(textTitle, textDescription))

            setResult(Constantes.keyNotaRequestSalvar, this)
        }

        finish()
    }
}