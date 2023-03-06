package com.jvictorc.notas.repository

import com.jvictorc.notas.model.Nota
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NotaRepository : JpaRepository<Nota, UUID> {
    override fun findAll(pageable: Pageable): Page<Nota>
    @Query("SELECT * FROM notas n WHERE n.titulo LIKE %:titulo%", nativeQuery = true)
    fun findByTitulo(titulo: String): List<Nota>
}
