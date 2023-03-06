package com.jvictorc.notas.mappers.view.user

import com.jvictorc.notas.dto.user.UserView
import com.jvictorc.notas.mappers.Mapper
import com.jvictorc.notas.model.User

class MapperEntityToViewUser : Mapper<User, UserView> {
    override fun map(formaInicial: User): UserView {
        return UserView(
            id = formaInicial.id.toString(),
            username = formaInicial.username
        )
    }
}