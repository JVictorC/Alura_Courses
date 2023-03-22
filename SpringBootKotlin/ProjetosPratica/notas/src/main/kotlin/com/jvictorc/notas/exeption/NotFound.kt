package com.jvictorc.notas.exeption

import java.lang.RuntimeException

class NotFound(message: String?) : RuntimeException(message) {}


fun defaultNotFoundNotas(id: String) =  NotFound("Não foi possivel localizar a nota com id: $id");

fun defaultNotFoundUser(username: String) =  NotFound("Não foi possivel localizar o user com o username: $username");
