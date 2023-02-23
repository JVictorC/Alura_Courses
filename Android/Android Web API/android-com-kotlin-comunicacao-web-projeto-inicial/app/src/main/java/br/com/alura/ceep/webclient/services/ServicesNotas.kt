package br.com.alura.ceep.webclient.services

import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webclient.model.NotaRequisicao
import br.com.alura.ceep.webclient.model.NotaResposta
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServicesNotas {

    @GET("notas")
    suspend fun getAllNotas(): List<NotaResposta>


    @PUT("notas/{id}")
    suspend fun salvaNota(
        @Path("id") id: String,
        @Body nota: NotaRequisicao
    ): Response<NotaResposta>


    @DELETE("notas/{id}")
    suspend fun removeNota(
        @Path("id") id: String
    ) : Response<Void>
}