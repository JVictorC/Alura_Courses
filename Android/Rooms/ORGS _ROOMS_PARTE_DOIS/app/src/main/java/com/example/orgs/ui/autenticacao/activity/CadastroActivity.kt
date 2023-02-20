package com.example.orgs.ui.autenticacao.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.orgs.database.AppDataBase
import com.example.orgs.databinding.ActivityCadastroBinding
import com.example.orgs.entities.Usuario
import com.example.orgs.ui.autenticacao.helpers.ValidatesFormularioUsuarioHelper
import kotlinx.coroutines.launch

class CadastroActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AppDataBase.instancia(this).usuarioDao()
    }

    private val validator by lazy {
        ValidatesFormularioUsuarioHelper(binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setButtonEnter()
    }

    private fun setButtonEnter() {
        binding.activityFormularioCadastroBotaoCadastrar.setOnClickListener {
            if(validator.isValid()) {
                val newUser = createUser()

                lifecycleScope.launch {
                    try {
                        dao.salva(newUser)
                        finish()
                    } catch (E: Exception) {
                        Toast.makeText(
                            this@CadastroActivity,
                            "Usuario Já Cadastrado",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
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