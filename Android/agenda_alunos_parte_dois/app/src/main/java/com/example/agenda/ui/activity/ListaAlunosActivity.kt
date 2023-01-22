package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.agenda.R
import com.example.agenda.constantes.ContastantesActivities
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.Aluno


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
    private lateinit var adaptar: ArrayAdapter<Aluno>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)

        inicializacaoCampos()
        configuraLista()

        fabAddNovoAluno.setOnClickListener {
            abreFormularioParaInserirAluno()
        }

        dao.salva(Aluno("Fran", "1122223333", "fran@gmail.com"))
        dao.salva(Aluno("Teste", "1122223333", "fran@gmail.com"))

    }

    override fun onResume() {
        super.onResume()

        atualizaLista()
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
            removeAluno(menuInfo.position)

            println(menuInfo.position)
        }

        return super.onContextItemSelected(item)
    }

    private  fun atualizaLista() {
        adaptar.clear()

        adaptar.addAll( dao.todos())
    }

    private fun inicializacaoCampos() {
        fabAddNovoAluno = findViewById(R.id.activity_main_fab_novo_aluno)
        listaDeAlunos = findViewById(R.id.activity_lista_de_alunos_listview)

        dao = AlunoDAO()
    }

    private fun configuraLista() {
        listaDeAlunos.setOnItemClickListener { _, _, index, _ ->
            abreFormularioParaEditarAluno(index)
        }

        adaptar =  ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            dao.todos()
        )

        listaDeAlunos.adapter = adaptar

        registerForContextMenu(listaDeAlunos)
    }

    private fun abreFormularioParaEditarAluno(index: Int) {
        val aluno = listaDeAlunos.getItemAtPosition(index) as Aluno

        val intent = Intent(this, FormularioAlunoActivity::class.java)

        intent.putExtra(ContastantesActivities.CHAVE_ALUNO, aluno)

        startActivity(intent)

        Log.i("listaDeAlunos", aluno.nome)
    }

    private fun abreFormularioParaInserirAluno() {
        startActivity(Intent(this, FormularioAlunoActivity::class.java))
    }

    private fun removeAluno(index: Int) {
        val aluno = listaDeAlunos.getItemAtPosition(index) as Aluno

        dao.removeAluno(aluno)

        adaptar.remove(aluno)

        Log.i("aluno", "click longo")
    }
}