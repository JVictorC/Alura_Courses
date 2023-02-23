package br.com.alura.ceep.webclient

import br.com.alura.ceep.webclient.services.ServicesNotas
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class InitRetroFit {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.15.5:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    val notasServices: ServicesNotas = retrofit.create(ServicesNotas::class.java)
}