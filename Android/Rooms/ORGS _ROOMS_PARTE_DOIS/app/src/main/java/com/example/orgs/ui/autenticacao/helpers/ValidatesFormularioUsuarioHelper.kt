package com.example.orgs.ui.autenticacao.helpers

import com.example.orgs.databinding.ActivityCadastroBinding

class ValidatesFormularioUsuarioHelper(val binding: ActivityCadastroBinding) {

    fun isValid() : Boolean {
        val validates = listOf<Boolean>(
            validateFormularioUsuario(),
            validateFormularioNome(),
            validateFormularioSenha()
        )

        return  validates.all { it }
    }


    private fun validateFormularioUsuario() : Boolean {
       with(binding) {
           if(activityFormularioCadastroUsuario.text.toString().isBlank()) {
               textInputLayoutUsuario.error = "O Campo Usuario Não pode ser vazio"

               return false
           }

           textInputLayoutUsuario.error = ""
           return true
       }
    }

    private fun validateFormularioNome() : Boolean {
        with(binding) {
            if(activityFormularioCadastroNome.text.toString().isBlank()) {
                textInputlayoutNome.error = "O Campo Nome Não pode ser vazio"

                return false
            }

            textInputlayoutNome.error = ""
            return true
        }
    }

    private fun validateFormularioSenha() : Boolean {
        with(binding) {
            if(activityFormularioCadastroSenha.text.toString().isBlank()) {
                textInputlayoutSenha.error = "O Campo Senha pode ser vazio"

                return false
            }

            textInputlayoutSenha.error = ""
            return true
        }
    }


}