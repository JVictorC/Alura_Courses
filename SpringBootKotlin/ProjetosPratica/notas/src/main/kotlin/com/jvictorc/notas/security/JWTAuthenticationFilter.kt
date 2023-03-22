package com.jvictorc.notas.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.jvictorc.notas.dto.user.TokenJwt
import com.jvictorc.notas.model.Credentials
import com.jvictorc.notas.model.UserDetailsImp
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

class JWTAuthenticationFilter(
    private val authenticationManagerBean: AuthenticationManager,
    private val jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val (email, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)

        val token = UsernamePasswordAuthenticationToken(email, password, Collections.singleton(SimpleGrantedAuthority("user")))

        return authenticationManagerBean.authenticate(token)
    }


    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        try {
            val username = (authResult?.principal as UserDetailsImp).username
            val token: String = jwtUtil.generateToken(username)


            response?.addHeader("Authorization", token)
            response?.addHeader("Access-Control-Expose-Headers", "Authorization")


            response?.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            response?.outputStream?.print( ObjectMapper().writeValueAsString(TokenJwt(token)));
            response?.flushBuffer();
        } catch (e: Exception) {
            print(e)
        }
    }


    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        val error = BadCredentialsError()
        response.status = error.status
        response.contentType = "application/json"
        response.writer.append(error.toString())
    }

    private data class BadCredentialsError(
        val timestamp: Long = Date().time,
        val status: Int = 401,
        val message: String = "Email or password incorrect",
    ) {
        override fun toString(): String {
            return ObjectMapper().writeValueAsString(this)
        }
    }

}

