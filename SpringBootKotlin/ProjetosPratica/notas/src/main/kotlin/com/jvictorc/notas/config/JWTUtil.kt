package com.jvictorc.notas.config

import com.jvictorc.notas.services.UserDatailsServices
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.util.*

@Component
class JWTUtil(
    val services: UserDatailsServices,
) {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    private val expiration: Long = 600000


    fun generateToken(username: String): String {
        return Jwts
            .builder()
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .setSubject(username).compact()
    }

    private fun getClaims(token: String?) =
        Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body

    fun isTokenValid(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        val username = getClaims(jwt).subject

        val user = services.loadUserByUsername(username)

        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }

    fun isTokenExpired(jwt: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt)
            false
        } catch (e: ExpiredJwtException) {
            true
        }
    }
}