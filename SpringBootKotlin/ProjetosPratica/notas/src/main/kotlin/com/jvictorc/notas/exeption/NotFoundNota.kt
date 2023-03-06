package com.jvictorc.notas.exeption

import java.lang.RuntimeException

class NotFoundNota(message: String?) : RuntimeException(message) {}


fun defaultNotFoundNotas(id: String) =  NotFoundNota("Não foi possivel localizar a nota com id: $id");

fun defaultNotFoundUser(username: String) =  NotFoundNota("Não foi possivel localizar o user com o username: $username");
