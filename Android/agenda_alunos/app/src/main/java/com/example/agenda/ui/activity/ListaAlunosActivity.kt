package com.example.agenda.ui.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.agenda.R
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.aluno.Aluno

//        Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show()


//        val color = resources.getString(R.color.teal_700.toInt())
//
//        val actionBar: ActionBar? = supportActionBar
//
//        val colorDrawable = ColorDrawable(Color.parseColor(color))
//        actionBar?.setBackgroundDrawable(colorDrawable)


class ListaAlunosActivity : AppCompatActivity() {

    private lateinit var fabAddNovoAluno:  FloatingActionButton
    private lateinit var listaDeAlunos:  ListView
    private lateinit var dao: AlunoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)

        dao = AlunoDAO()

        inicializacaoCampos()

        fabAddNovoAluno.setOnClickListener {
            startActivity(Intent(this, FormularioAlunoActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()


        popularListView()
    }


    private fun inicializacaoCampos() {
        fabAddNovoAluno = findViewById(R.id.activity_main_fab_novo_aluno)
        listaDeAlunos = findViewById<ListView>(R.id.activity_lista_de_alunos_listview)
    }


    private fun popularListView() {
        listaDeAlunos.adapter = ArrayAdapter<Aluno>(
            this,
            android.R.layout.simple_list_item_1,
            dao.todos()
        )
    }


}