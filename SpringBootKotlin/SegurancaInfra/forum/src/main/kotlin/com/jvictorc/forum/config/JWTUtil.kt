package com.jvictorc.forum.config

import com.jvictorc.forum.services.UserDetailsServicesImp
import com.jvictorc.forum.services.UsuariosService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil(
    private val userService: UserDetailsServicesImp
) {

    val expiration: Long = 60000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>) : String? {
        return Jwts.builder()
            .setSubject(username)
            .claim("roles", authorities)
            .setExpiration(Date(System.currentTimeMillis() * expiration))
            .signWith(SignatureAlgorithm.HS256, secret.toByteArray())
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(secret.toByteArray()).build().parseClaimsJws(jwt)

            true
        } catch (e: IllegalAccessException) {
            false
        }
    }

    fun getAuthentication(jwt: String?) : UsernamePasswordAuthenticationToken{
        val username =  Jwts.parserBuilder().setSigningKey(secret.toByteArray()).build().parseClaimsJws(jwt).body.subject

        val user = userService.loadUserByUsername(username)

        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }


}