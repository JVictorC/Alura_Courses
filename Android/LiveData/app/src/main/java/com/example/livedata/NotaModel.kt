package com.example.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotaModel : ViewModel() {

    val listaNotas: MutableLiveData<MutableList<Nota>> by lazy {
        MutableLiveData<MutableList<Nota>>()
    }


    init {
        listaNotas.value = mutableListOf(Nota("1", "2"))
    }

    fun inserirNota(nota:Nota) {
        val list =listaNotas.value?.toMutableList()
        list?.add(nota)

        listaNotas.value =  list


        val teste = listaNotas.value

        print(teste)
    }

}