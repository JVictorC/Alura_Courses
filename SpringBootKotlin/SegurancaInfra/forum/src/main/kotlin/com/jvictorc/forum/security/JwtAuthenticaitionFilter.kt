package com.jvictorc.forum.security

import com.jvictorc.forum.config.JWTUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticaitionFilter(
    private val jwtUtil: JWTUtil,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val token = request.getHeader("Authorization")

        getTokenDetail(token)?.let { jwt ->
            val isTokeValid = jwtUtil.isValid(jwt)

            if(isTokeValid) {
                val authentication = jwtUtil.getAuthentication(jwt)
                SecurityContextHolder.getContext().authentication = authentication
            }
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