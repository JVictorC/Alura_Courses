package com.jvictorc.notas.services

import com.jvictorc.notas.dto.user.UserForm
import com.jvictorc.notas.dto.user.UserView
import com.jvictorc.notas.exeption.defaultNotFoundUser
import com.jvictorc.notas.mappers.form.user.MapperFormToEntityUser
import com.jvictorc.notas.mappers.view.user.MapperEntityToViewUser
import com.jvictorc.notas.model.User
import com.jvictorc.notas.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServices(
    private val encoder: PasswordEncoder,
    private val repository: UserRepository,
) {
    fun createNewUser(newUser: UserForm) : UserView {
        newUser.password = encoder.encode(newUser.password)

        val user = MapperFormToEntityUser().map(newUser)

        repository.save(user)

        return MapperEntityToViewUser().map(user)
    }

    fun validatedUser(userForm: UserForm): Boolean {
        repository.findByUsername(userForm.username)?.let {
            return  encoder.matches(it.password, userForm.password)
        } ?: throw defaultNotFoundUser(userForm.username)
    }
}
