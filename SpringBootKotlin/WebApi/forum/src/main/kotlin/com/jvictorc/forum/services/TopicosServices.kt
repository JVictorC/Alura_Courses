package com.jvictorc.forum.services

import com.jvictorc.forum.dto.AtualizacaoTopicoForm
import com.jvictorc.forum.dto.NovoTopicoForm
import com.jvictorc.forum.dto.TopicoView
import com.jvictorc.forum.exception.NotFoundExeption
import com.jvictorc.forum.mapper.NovoTopicoFormMapper
import com.jvictorc.forum.mapper.TopicoViewMapper
import com.jvictorc.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicosServices(
    private val topicoViewMapper: TopicoViewMapper,
    private val novoTopicoFormMapper: NovoTopicoFormMapper,
) {

    private val allTopicos = mutableListOf<Topico>()

    fun getAllTopicos(): List<TopicoView> {
        return allTopicos.map {
            topicoViewMapper.map(it)
        }
    }

    fun getTopicoById(id: Long): TopicoView? {
        val topico = allTopicos.first { it.id == id }

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = novoTopicoFormMapper.map(form)

        topico.id = allTopicos.size.toLong()

        allTopicos.add(topico)

        return topicoViewMapper.map(topico)
    }

    fun editarNotar(id: String, topicoForm: AtualizacaoTopicoForm): TopicoView {
        var topico = allTopicos.firstOrNull { it.id == id.toLong() } ?: throw NotFoundExeption("Topico não localizado")

        topico = topico.copy(
            titulo = topicoForm.titulo,
            mensagem = topicoForm.mensagem,
        )

        val index = allTopicos.indexOfFirst { it.id == id.toLong() }

        allTopicos[index] = topico

        return topicoViewMapper.map(topico)
    }

    fun deleteNota(id: String) {
        val item = allTopicos.firstOrNull { it.id == id.toLong() } ?: throw NotFoundExeption("Topico não localizado")

        allTopicos.remove(item)
    }

}