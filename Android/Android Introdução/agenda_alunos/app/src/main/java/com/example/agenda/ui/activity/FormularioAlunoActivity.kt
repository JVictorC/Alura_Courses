package com.example.agenda.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.widget.Button
import android.widget.EditText
import com.example.agenda.R
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.aluno.Aluno


//        val color = resources.getString(R.color.teal_700.toInt())
//        val actionBar: ActionBar? = supportActionBar
//        val colorDrawable = ColorDrawable(Color.parseColor(color))
//        actionBar?.setBackgroundDrawable(colorDrawable)


class FormularioAlunoActivity : AppCompatActivity() {
    private lateinit var buttonSave: Button
    private lateinit var campoNome: EditText
    private lateinit var campoTelefone: EditText
    private lateinit var compoEmail: EditText
    private lateinit var dao: AlunoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Podemos pegar os atributos da app bar com o seguinte codigo:
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = resources.getString(R.string.app_bar_add_new_aluno_name.toInt())

        setContentView(R.layout.activity_formulario_aluno)

        dao = AlunoDAO()
        inicializacaoCampos()

        buttonSave.setOnClickListener {
            salvarAluno(criaAluno())
        }


    }

    private fun inicializacaoCampos() {
        buttonSave = findViewById(R.id.activity_formalumario_aluno_button_save);
        campoNome = findViewById(R.id.activity_formalumario_aluno_nome)
        campoTelefone = findViewById(R.id.activity_formalumario_aluno_telefone)
        compoEmail = findViewById(R.id.activity_formalumario_aluno_email)
    }

    private fun salvarAluno(aluno: Aluno) {
        dao.salva(aluno)

        finish()
    }

    private fun criaAluno(): Aluno {
        val nome = campoNome.text.toString()
        val telefone = campoTelefone.text.toString()
        val campoEmail = compoEmail.text.toString()

        return Aluno(nome, telefone, campoEmail)
    }
}