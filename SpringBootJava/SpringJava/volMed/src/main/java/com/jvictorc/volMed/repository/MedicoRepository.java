package com.jvictorc.volMed.repository;

import com.jvictorc.volMed.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {

    @Override
    @Query(nativeQuery = true, value = "SELECT * FROM medicos WHERE ativo = true")
    Page<Medico> findAll(Pageable pageable);
}
