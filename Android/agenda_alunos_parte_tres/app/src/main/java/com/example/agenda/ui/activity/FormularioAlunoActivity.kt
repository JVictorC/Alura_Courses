package com.example.agenda.ui.activity

import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.constantes.ContastantesActivities
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.Aluno


//        val color = resources.getString(R.color.teal_700.toInt())
//        val actionBar: ActionBar? = supportActionBar
//        val colorDrawable = ColorDrawable(Color.parseColor(color))
//        actionBar?.setBackgroundDrawable(colorDrawable)


class FormularioAlunoActivity : AppCompatActivity() {
    private lateinit var campoNome: EditText
    private lateinit var campoTelefone: EditText
    private lateinit var compoEmail: EditText
    private lateinit var dao: AlunoDAO
    private lateinit var aluno: Aluno

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_formulario_aluno)

        inicializacaoCampos()

        // Podemos pegar os atributos da app bar com o seguinte codigo:
//        buttonSave.setOnClickListener {
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_formulario_aluno_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.activity_formulario_aluno_salvar) {
            finalizaFormulario()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun finalizaFormulario() {
        criaAlunoAtualizado()
        if(intent.hasExtra(ContastantesActivities.CHAVE_ALUNO)) {
            editarAluno()
        } else {
            salvarAluno()
        }
    }

    private fun inicializacaoCampos() {
        campoNome = findViewById(R.id.activity_formalumario_aluno_nome)
        campoTelefone = findViewById(R.id.activity_formalumario_aluno_telefone)
        compoEmail = findViewById(R.id.activity_formalumario_aluno_email)

        dao = AlunoDAO()
        val actionBar: ActionBar? = supportActionBar


        if(intent.hasExtra(ContastantesActivities.CHAVE_ALUNO)) {
            actionBar?.title = resources.getString(R.string.app_bar_edit_aluno_name)

            aluno = intent.getSerializableExtra(
                ContastantesActivities.CHAVE_ALUNO
            ) as Aluno

            iniciarCamposComAlunoSelecionado()
        } else {
            actionBar?.title = resources.getString(R.string.app_bar_add_new_aluno_name)
            aluno = Aluno("", "", "")
        }
    }

    private fun iniciarCamposComAlunoSelecionado() {
        campoNome.text =  Editable.Factory.getInstance().newEditable(aluno.nome)
        campoTelefone.text = Editable.Factory.getInstance().newEditable(aluno.telefone)
        compoEmail.text = Editable.Factory.getInstance().newEditable(aluno.email)
    }

    private fun editarAluno() {
        dao.editAluno(aluno)

        finish()
    }

    private fun salvarAluno() {
        dao.salva(aluno)

        finish()
    }

    private fun criaAlunoAtualizado() {
        val nome = campoNome.text.toString()
        val telefone = campoTelefone.text.toString()
        val campoEmail = compoEmail.text.toString()

        aluno.editarAluno(
            nome, telefone, campoEmail
        )
    }
}