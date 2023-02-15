package com.example.orgs.ui.autenticacao.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.orgs.database.AppDataBase
import com.example.orgs.databinding.ActivityCadastroBinding
import com.example.orgs.entities.Usuario

class CadastroActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AppDataBase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setButtonEnter()
    }

    private fun setButtonEnter() {
        val newUser = createUser()

        lif


        finish()
    }

    private fun createUser(): Usuario {
        with(binding) {
            val usuario = activityFormularioCadastroUsuario.text.toString()
            val nome = activityFormularioCadastroNome.text.toString()
            val senha = activityFormularioCadastroSenha.text.toString()


            return Usuario(usuario, nome, senha)
        }
    }
}