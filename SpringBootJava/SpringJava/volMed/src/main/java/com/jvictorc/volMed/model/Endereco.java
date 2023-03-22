package com.jvictorc.volMed.model;

import com.jvictorc.volMed.dto.endereco.DadosEndereco;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String uf;
    private String cidade;

    private String complemento;
    private String numero;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.uf = endereco.uf();
        this.complemento = endereco.complemento();
        this.numero = endereco.numero();
        this.cidade = endereco.cidade();
    }

    public Endereco atualizaInfos(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro() == null ? this.logradouro : endereco.logradouro();

        this.bairro = endereco.bairro() == null ? this.bairro : endereco.bairro();

        this.cep = endereco.cep() == null ? this.cep : endereco.cep();

        this.uf = endereco.uf() == null ? this.uf : endereco.uf();

        this.complemento = endereco.complemento() == null ? this.complemento : endereco.complemento();

        this.numero = endereco.numero() == null ? this.numero : endereco.numero();

        this.cidade = endereco.cidade() == null ? this.cidade : endereco.cidade();

        return this;
    }
}
