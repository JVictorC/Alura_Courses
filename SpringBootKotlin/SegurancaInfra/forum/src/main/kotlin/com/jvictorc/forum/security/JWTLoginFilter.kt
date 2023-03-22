package com.jvictorc.forum.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.jvictorc.forum.config.JWTUtil
import com.jvictorc.forum.model.Credentials
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

class JWTLoginFilter(private val authManager: AuthenticationManager, private val jwtUtil: JWTUtil) : UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val(username, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)


        val token = UsernamePasswordAuthenticationToken(username, password)


        return authManager.authenticate(token)
    }


    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user = (authResult?.principal as UserDetails)

        val token = jwtUtil.generateToken(user.username, user.authorities)

        response?.addHeader("Authorization", "Bearer $token")
    }


    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        val error = BadCredentialsError()
        response?.status = error.status
        response?.contentType = "application/json"
        response?.writer?.append(error.toString())
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
