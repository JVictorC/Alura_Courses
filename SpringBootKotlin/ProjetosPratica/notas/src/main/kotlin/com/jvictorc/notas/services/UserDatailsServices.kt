package com.jvictorc.notas.services

import com.jvictorc.notas.exeption.defaultNotFoundUser
import com.jvictorc.notas.model.UserDetailsImp
import com.jvictorc.notas.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDatailsServices : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository
    override fun loadUserByUsername(email: String?): UserDetails {
        val user = userRepository.findByEmail(email) ?: throw defaultNotFoundUser(email ?: "")

        return UserDetailsImp(user)
    }

}