package com.jvictorc.volMed.domain.usuario.services;

import com.jvictorc.volMed.domain.usuario.model.Usuario;
import com.jvictorc.volMed.domain.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServices {

    @Autowired
    UsuarioRepository usuarioRepository;


    public Usuario findByLogin(String login) {
        return usuarioRepository.findByLogin(login).orElseThrow();
    }

}
