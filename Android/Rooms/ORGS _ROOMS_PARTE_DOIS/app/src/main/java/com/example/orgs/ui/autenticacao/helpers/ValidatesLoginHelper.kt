package com.example.orgs.ui.autenticacao.helpers

import android.os.Build
import android.text.InputType
import com.example.orgs.databinding.ActivityCadastroBinding
import com.example.orgs.databinding.ActivityLoginBinding

class ValidatesLoginHelper(private val binding: ActivityLoginBinding) {
    fun isValid() : Boolean {
        val validates = listOf<Boolean>(
            validateFormularioUsuario(),
            validateFormulariSenha(),
        )

        return  validates.all { it }
    }


    private fun validateFormularioUsuario() : Boolean {
       with(binding) {
           if(activityLoginTextInputNome.text.toString().isBlank()) {
               activityLoginTextInputLayoutNome.error = "O Campo Usuario Não pode ser vazio"

               return false
           }

           activityLoginTextInputLayoutNome.error = ""
           return true
       }
    }

    private fun validateFormulariSenha() : Boolean {
        with(binding) {
            if(activityLoginTextInputSenha.text.toString().isBlank()) {
                activityLoginTextInputLayoutSenha.error = "O Campo Nome Não pode ser vazio"

                return false
            }

            activityLoginTextInputLayoutSenha.error = ""
            return true
        }
    }


    fun validateUserWrong() {
        with(binding) {
            activityLoginTextInputSenha.setText("")
            activityLoginTextInputNome.setText("")
            activityLoginTextInputLayoutSenha.error = "Senha Incorreta"
            activityLoginTextInputLayoutNome.error = "Usuario Incorreto"

        }
    }


    fun clearErros() {
        with(binding) {
            activityLoginTextInputLayoutSenha.error = ""
            activityLoginTextInputLayoutNome.error = ""
        }
    }

}