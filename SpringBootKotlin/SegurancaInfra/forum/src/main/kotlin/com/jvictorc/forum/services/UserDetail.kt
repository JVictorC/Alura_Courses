package com.jvictorc.forum.services

import com.jvictorc.forum.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    private val user: Usuario
) : UserDetails {
    override fun getAuthorities() =  user.role

    override fun getPassword() = user.password

    override fun getUsername() = user.email

    override fun isAccountNonExpired() =  true

    override fun isAccountNonLocked() =  true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}