package com.jvictorc.forum.config

import com.jvictorc.forum.security.JWTLoginFilter
import com.jvictorc.forum.security.JwtAuthenticaitionFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecorityConfiguration(
    private val jwtUtil: JWTUtil,
    private val userServices: UserDetailsService,
)  {

    @Bean
    fun bCryptPasswordEnconder() : BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer? {
        return WebSecurityCustomizer { web: WebSecurity -> web.ignoring().requestMatchers("/h2-console/**") }
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val authManager = authManager(http)

         http
             .authorizeHttpRequests()

             .requestMatchers("/topicos").permitAll()
             .requestMatchers("/login").permitAll()
             .requestMatchers("/usuarios/**").permitAll()
             .anyRequest()
             .authenticated()
             .and()

             .csrf()
             .disable()

             .authenticationManager(authManager)

             .addFilterBefore(JWTLoginFilter(authManager = authManager, jwtUtil = jwtUtil),  UsernamePasswordAuthenticationFilter().javaClass)
             .addFilterBefore(JwtAuthenticaitionFilter(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter::class.java)

             .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)







        return http.build()
    }


    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )

        authenticationManagerBuilder.userDetailsService(userServices).passwordEncoder(bCryptPasswordEnconder())

        return authenticationManagerBuilder.build()
    }

}