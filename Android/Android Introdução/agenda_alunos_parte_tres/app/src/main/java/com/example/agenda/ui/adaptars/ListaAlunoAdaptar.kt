package com.example.agenda.ui.adaptars

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.agenda.R
import com.example.agenda.model.Aluno

class ListaAlunoAdaptar(list: MutableList<Aluno>, private var context: Context) : BaseAdapter() {
    private var listAluno: MutableList<Aluno> = list


    override fun getCount(): Int {
        return listAluno.size
    }

    override fun getItem(position: Int): Aluno {
        return listAluno[position]
    }

    override fun getItemId(position: Int): Long {
        return listAluno[position].id.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemAluno = LayoutInflater.from(context).inflate(R.layout.item_aluno, parent, false)

        val textNome: TextView = itemAluno.findViewById(R.id.item_aluno_nome)
        val textTelefone: TextView = itemAluno.findViewById(R.id.item_aluno_telefone)

        textNome.text = listAluno[position].nome
        textTelefone.text = listAluno[position].telefone

        return itemAluno
    }

    fun atualizaAdapter(list: List<Aluno>) {
        listAluno.clear()
        listAluno.addAll(list)
        notifyDataSetChanged()
    }

    fun remove(aluno: Aluno) {
        listAluno.remove(aluno)
        notifyDataSetChanged()
    }

    fun getAlunoAtPosition(index: Int): Aluno {
       return listAluno[index]
    }
}