package com.jvictorc.notas.exeption

import java.lang.RuntimeException

class NotAuthorization(message: String?)  : RuntimeException(message) {}


fun defaultNotAuthorization() = NotAuthorization("Usuario Não Autorizado")