package com.jvictorc.notas.mappers.form.user

import com.jvictorc.notas.dto.user.UserForm
import com.jvictorc.notas.mappers.Mapper
import com.jvictorc.notas.model.User

class MapperFormToEntityUser : Mapper<UserForm, User> {
    override fun map(formaInicial: UserForm): User {
        return User(
            username = formaInicial.username,
            password = formaInicial.password
        )
    }
}