package com.jvictorc.volMed.model;

import com.jvictorc.volMed.dto.medico.DadosAtualizarMedicos;
import com.jvictorc.volMed.dto.medico.DadosCadastroMedicos;
import com.jvictorc.volMed.dto.medico.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "MEDICOS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String telefone;
    private String email;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedicos novoMedico) {
        this.nome = novoMedico.nome();
        this.email = novoMedico.email();
        this.crm = novoMedico.crm();
        this.especialidade = novoMedico.especialidade();
        this.telefone = novoMedico.telefone();
        this.endereco = new Endereco(novoMedico.endereco());
        this.ativo = true;
    }

    public void atualizarInfo(DadosAtualizarMedicos dadosAtualizarMedicos) {
        this.nome = dadosAtualizarMedicos.nome() == null ? this.nome : dadosAtualizarMedicos.nome();

        this.telefone = dadosAtualizarMedicos.telefone() == null ? this.telefone : dadosAtualizarMedicos.telefone();

        this.email = dadosAtualizarMedicos.email() == null ? this.email : dadosAtualizarMedicos.email();

        this.endereco = dadosAtualizarMedicos.endereco() == null ? this.endereco : this.endereco.atualizaInfos(dadosAtualizarMedicos.endereco());
    }


    public void desativaConta() {
        this.ativo = false;
    }
}
