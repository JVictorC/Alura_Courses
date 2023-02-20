package com.example.orgs.ui.autenticacao.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.orgs.constantes.Constantes
import com.example.orgs.database.AppDataBase
import com.example.orgs.databinding.ActivityLoginBinding
import com.example.orgs.extensios.vaiPara
import com.example.orgs.preferences.dataStore
import com.example.orgs.ui.autenticacao.helpers.ValidatesLoginHelper
import com.example.orgs.ui.listagem_produtos.activity.ListaProdutosActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val usuarioDao by lazy {
        AppDataBase.instancia(this).usuarioDao()
    }
    private val validateForms by lazy {
        ValidatesLoginHelper(binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListerners()
    }

    private fun setListerners() {
        binding.activityLoginBotaoCadastrar.setOnClickListener {
            vaiParaCadastro()
        }

        binding.activityLoginButtonEntrar.setOnClickListener {
            entrarApp()
        }
    }

    private fun vaiParaCadastro() {
        Intent(this, CadastroActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun entrarApp() {
        if (validateForms.isValid()) {
            val usuarioId = binding.activityLoginTextInputNome.text.toString()
            val senha = binding.activityLoginTextInputSenha.text.toString()

            autenticaUser(usuarioId, senha)
        }
    }

    private fun autenticaUser(usuarioId: String, senha: String) {
        lifecycleScope.launch {
            val usuarioEntity = usuarioDao.autentica(usuarioId, senha)


            usuarioEntity?.let { usuarioLogado ->
                validateForms.clearErros()

                dataStore.edit { preferences ->

                    preferences[
                            stringPreferencesKey(
                                Constantes.keyUserLoggedPreferences
                            )] = usuarioLogado.id
                }

                vaiPara(ListaProdutosActivity::class.java)
                finish()
            } ?: validateForms.validateUserWrong()
        }
    }
}