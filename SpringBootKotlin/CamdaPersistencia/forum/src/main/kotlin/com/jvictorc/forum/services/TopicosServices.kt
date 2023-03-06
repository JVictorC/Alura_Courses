package com.jvictorc.forum.services

import com.jvictorc.forum.dto.AtualizacaoTopicoForm
import com.jvictorc.forum.dto.NovoTopicoForm
import com.jvictorc.forum.dto.TopicoPorCategoriaDto
import com.jvictorc.forum.dto.TopicoView
import com.jvictorc.forum.exception.NotFoundExeption
import com.jvictorc.forum.mapper.NovoTopicoFormMapper
import com.jvictorc.forum.mapper.TopicoViewMapper
import com.jvictorc.forum.model.Topico
import com.jvictorc.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicosServices(
    private val topicoViewMapper: TopicoViewMapper,
    private val novoTopicoFormMapper: NovoTopicoFormMapper,
    private val topicoRepository: TopicoRepository,
) {


    fun getAllTopicos(
        nomeCurso: String?,
        paginacacao: Pageable
    ): Page<TopicoView?> {
        val listTopicos = if (nomeCurso != null) {
            topicoRepository.findByCursoNome(nomeCurso, paginacacao).map { topico ->
                topico?.let {
                    topicoViewMapper.map(it)
                }
            }
        } else {
            topicoRepository.findAll(paginacacao).map {
                topicoViewMapper.map(it)
            }
        }


        return listTopicos
    }

    fun getTopicoById(id: Long): TopicoView? {
        val topico = topicoRepository.getReferenceById(id)

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView? {
        val topico = novoTopicoFormMapper.map(form)

        topicoRepository.save(topico)

        return topicoViewMapper.map(topico)

        return null
    }

    fun editarTopico(id: String, topicoForm: AtualizacaoTopicoForm): TopicoView {
        var topico = topicoRepository.getReferenceById(id.toLong()) ?: throw NotFoundExeption("Topico não localizado")

        topico = topico.copy(
            titulo = topicoForm.titulo,
            mensagem = topicoForm.mensagem,


            )

        topicoRepository.save(topico)

        return topicoViewMapper.map(topico)
    }

    fun deleteTopico(id: String) {
        val idLong = id.toLong()

        if (topicoRepository.existsById(idLong)) topicoRepository.deleteById(idLong)
        else throw NotFoundExeption("Topico Não Localizado")
    }

    fun relatorio() : List<TopicoPorCategoriaDto> {
        return topicoRepository.relatorio()
    }

}