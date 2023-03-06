package com.jvictorc.notas.services

import com.jvictorc.notas.dto.notas.NotaForm
import com.jvictorc.notas.exeption.defaultNotFoundNotas
import com.jvictorc.notas.mappers.form.notas.MapperFormToEntityNota
import com.jvictorc.notas.model.Nota
import com.jvictorc.notas.repository.NotaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotasServices(
    private val notasRepository: NotaRepository
) {
    fun listAllNotas(pageable: Pageable) : Page<Nota> {
        return notasRepository.findAll(pageable)
    }

    fun createNewNote(notaForm: NotaForm): Nota {
        val nota = MapperFormToEntityNota().map(notaForm)

        println(nota)

        notasRepository.save(nota)

        return nota
    }

    fun listaPorTitulo(titulo: String): List<Nota> {
        return notasRepository.findByTitulo(titulo)
    }

    fun buscarPorId(id: String): Nota {
        val uuid = UUID.fromString(id)
        return notasRepository.findByIdOrNull(uuid) ?: throw defaultNotFoundNotas(id)
    }

    fun deleteNota(id: String) {
        val uuid = UUID.fromString(id)
        if(notasRepository.existsById(uuid)) {
            notasRepository.deleteById(uuid)
        } else {
            throw defaultNotFoundNotas(id)
        }
    }

    fun updateNota(notaForm: NotaForm, id: String): Nota {
        val uuid = UUID.fromString(id)

        if(notasRepository.existsById(uuid)) {
            notasRepository.findByIdOrNull(uuid)?.let {notaInDataBase ->
                val notaUpdated = notaInDataBase.copy(
                    titulo = notaForm.titulo,
                    descricao = notaForm.descricao
                )

                notasRepository.save(notaUpdated)

                return notaUpdated
            }

        }


        throw defaultNotFoundNotas(id)
    }

}