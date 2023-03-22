package com.jvictorc.forum.mapper

interface Mapper<T, U> {
    fun map(t: T) : U
}
