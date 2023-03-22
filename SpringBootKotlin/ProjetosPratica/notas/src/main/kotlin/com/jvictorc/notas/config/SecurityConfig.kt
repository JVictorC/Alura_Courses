package com.jvictorc.notas.securityConfig

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {

       return http
           .csrf()
           .disable()

           .authorizeHttpRequests()
           .requestMatchers("/user/create-account").permitAll()
           .requestMatchers("/user/validate-user").permitAll()
           .requestMatchers("/user/login").permitAll()

           .anyRequest()
           .authenticated()

           .and()
           .addFilter(JWTAuthenticationFilter(authenticationManagerBean(http), jwtUtil = jwtUtil))

           .httpBasic()
           .and()

           .build()
    }


    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Bean
    fun authenticationManagerBean(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )


        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
        return authenticationManagerBuilder.build()
    }

}