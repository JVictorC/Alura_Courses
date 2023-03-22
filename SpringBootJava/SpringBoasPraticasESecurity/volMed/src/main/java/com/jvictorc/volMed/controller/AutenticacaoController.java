package com.jvictorc.volMed.controller;

import com.jvictorc.volMed.domain.usuario.dto.autenticacao.DadosAutenticacao;
import com.jvictorc.volMed.domain.usuario.dto.autenticacao.DadosAutenticacaoView;
import com.jvictorc.volMed.domain.usuario.model.UserDetailsImp;
import com.jvictorc.volMed.domain.usuario.model.Usuario;
import com.jvictorc.volMed.infra.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<DadosAutenticacaoView> login(
            @RequestBody @Valid DadosAutenticacao usuario
    ) {
        var token = new UsernamePasswordAuthenticationToken(usuario.login(), usuario.senha());

        var auth = manager.authenticate(token);


        var jwt = jwtUtil.generateToken((UserDetailsImp) auth.getPrincipal());

        return ResponseEntity.ok(new DadosAutenticacaoView(jwt));
    }



}
