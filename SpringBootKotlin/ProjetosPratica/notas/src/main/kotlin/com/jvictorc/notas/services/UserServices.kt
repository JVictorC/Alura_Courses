package com.jvictorc.notas.services

import com.jvictorc.notas.dto.user.UserForm
import com.jvictorc.notas.dto.user.UserView
import com.jvictorc.notas.exeption.defaultNotFoundUser
import com.jvictorc.notas.mappers.form.user.MapperFormToEntityUser
import com.jvictorc.notas.mappers.view.user.MapperEntityToViewUser
import com.jvictorc.notas.model.UserDetailsImp
import com.jvictorc.notas.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServices() {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var encoder:PasswordEncoder

    fun createNewUser(newUser: UserForm) : UserView {
        newUser.password = encoder.encode(newUser.password)

        val user = MapperFormToEntityUser().map(
            newUser
        )

        userRepository.save(user)

        return MapperEntityToViewUser().map(user)
    }


    fun validatedUser(userForm: UserForm): Boolean {
        userRepository.findByEmail(userForm.email)?.let {
            val passwordInDb = it.password
            val passwordPassed = userForm.password


            return  encoder.matches(passwordPassed, passwordInDb)
        } ?: throw defaultNotFoundUser(userForm.email)
    }

}
