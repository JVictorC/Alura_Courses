package com.example.orgs.ui.listagem_produtos.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.orgs.constantes.Constantes
import com.example.orgs.database.AppDataBase
import com.example.orgs.entities.Usuario
import com.example.orgs.extensios.vaiPara
import com.example.orgs.preferences.dataStore
import com.example.orgs.ui.autenticacao.activity.LoginActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

abstract class UsuarioBaseActivity : AppCompatActivity() {
    private val usuarioDao by lazy {
        AppDataBase.instancia(this).usuarioDao()
    }

    private val _usuarioLogged: MutableStateFlow<Usuario?> = MutableStateFlow(null)
    protected val usuarioLogged: StateFlow<Usuario?> = _usuarioLogged


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            getPreferencesUser()
        }
    }


    private suspend fun getPreferencesUser() {
        dataStore.data.collect { preferences ->
            preferences[stringPreferencesKey(
                Constantes.keyUserLoggedPreferences
            )]?.let { id ->
                val user = buscaProdutoUsuarioId(id)

                if(user == null) vaiParaLogin()
            } ?: vaiParaLogin()
        }
    }

    private suspend fun buscaProdutoUsuarioId(id: String) : Usuario? {
        return usuarioDao.buscaPorId(id).firstOrNull().also {
            _usuarioLogged.value = it

            supportActionBar?.title = "Bem Vindo a ORGS ${_usuarioLogged.value?.id}"
        }
    }

    private fun vaiParaLogin() {
        vaiPara(LoginActivity::class.java) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        finish()
    }

    protected fun logout() {
        lifecycleScope.launch {
            dataStore.edit { preferences ->
                preferences.remove(stringPreferencesKey(Constantes.keyUserLoggedPreferences))
            }
        }
    }
}