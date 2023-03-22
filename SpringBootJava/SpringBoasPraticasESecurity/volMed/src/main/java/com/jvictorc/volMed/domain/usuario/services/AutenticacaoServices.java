package com.jvictorc.volMed.domain.usuario.services;

import com.jvictorc.volMed.domain.usuario.model.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoServices implements UserDetailsService {

    @Autowired
    UsuarioServices usuarioServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInDb = usuarioServices.findByLogin(username);


        return new UserDetailsImp(userInDb);
    }
}
