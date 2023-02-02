package com.example.agenda.ui.activity

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.model.Aluno
import com.example.agenda.ui.ListaAlunoController
import com.google.android.material.floatingactionbutton.FloatingActionButton


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

    private var listaAlunoController = ListaAlunoController(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)

        inicializacaoCampos()
        configuraLista()
        setListerners()
    }

    override fun onResume() {
        super.onResume()

        listaAlunoController.atualizaLista()
    }

    private fun setListerners() {
        fabAddNovoAluno.setOnClickListener {
            listaAlunoController.abreFormularioParaInserirAluno()
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        // Inflate seria como eu pegar o XML e tacar dentro da activity
        menuInflater.inflate(R.menu.activity_lista_alunos_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo as  AdapterView.AdapterContextMenuInfo

        if(item.itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunoController.confirmaRemocao(menuInfo)

            println(menuInfo.position)
        }

        return super.onContextItemSelected(item)
    }

    private fun inicializacaoCampos() {
        fabAddNovoAluno = findViewById(R.id.activity_main_fab_novo_aluno)
        listaDeAlunos = findViewById(R.id.activity_lista_de_alunos_listview)
    }

    private fun configuraLista() {
        listaDeAlunos.setOnItemClickListener { _, _, index, _ ->
            val aluno = listaDeAlunos.getItemAtPosition(index) as Aluno

            listaAlunoController.abreFormularioParaEditarAluno(aluno)
        }

        listaDeAlunos.adapter = listaAlunoController.adaptar

        registerForContextMenu(listaDeAlunos)
    }
}