package com.example.agenda.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.AdapterView
import com.example.agenda.constantes.ContastantesActivities
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.Aluno
import com.example.agenda.ui.activity.FormularioAlunoActivity
import com.example.agenda.ui.adaptars.ListaAlunoAdaptar

class ListaAlunoController(private val context: Context) {
    private var dao = AlunoDAO()

    var adaptar = ListaAlunoAdaptar(dao.todos(), context)
        private set

    fun confirmaRemocao(menuInfo: AdapterView.AdapterContextMenuInfo) {
        val builder = AlertDialog.Builder(context)
            .setTitle("Removendo Aluno")
            .setMessage("Tem certeza que deseja remover o aluno ?")
            .setNegativeButton("Não", null)


        builder.setPositiveButton("Sim") { _, _ ->
            removeAluno(menuInfo.position)
        }

        builder.show()
    }

    private fun removeAluno(index: Int) {
        val aluno = adaptar.getAlunoAtPosition(index)

        dao.removeAluno(aluno)

        adaptar.remove(aluno)

        Log.i("aluno", "click longo")
    }

    fun atualizaLista() {
        adaptar.atualizaAdapter(dao.todos())
    }

    fun abreFormularioParaEditarAluno(aluno: Aluno) {
        val intent = Intent(context, FormularioAlunoActivity::class.java)

        intent.putExtra(ContastantesActivities.CHAVE_ALUNO, aluno)

        context.startActivity(intent)

        Log.i("listaDeAlunos", aluno.nome)
    }

    fun abreFormularioParaInserirAluno() {
        context.startActivity(Intent(context, FormularioAlunoActivity::class.java))
    }
}