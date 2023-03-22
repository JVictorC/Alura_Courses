package com.jvictorc.notas.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.jvictorc.notas.config.JWTUtil
import com.jvictorc.notas.dto.error.ErrorView
import com.jvictorc.notas.dto.user.TokenJwt
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthorizationFilter(
    private val jwtUtil: JWTUtil,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)

        val isTokeValid = jwtUtil.isTokenValid(jwt)

        if(isTokeValid) {
            val authentication = jwtUtil.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }


        chain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?) : String? {
        return token?.let { jwt ->
            jwt.startsWith("Bearer ")
            jwt.substring(7, jwt.length)
        }
    }
}