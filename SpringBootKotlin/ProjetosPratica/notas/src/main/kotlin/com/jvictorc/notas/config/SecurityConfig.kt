package com.jvictorc.notas.config

import com.jvictorc.notas.security.JWTAuthenticationFilter
import com.jvictorc.notas.security.JwtAuthorizationFilter
import com.jvictorc.notas.services.UserDatailsServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.util.logging.Filter


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    private lateinit var userServices: UserDatailsServices

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    @Bean
    @Autowired
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        val authManager = authManager(http)

       return http
           .authorizeHttpRequests()

           .requestMatchers("/user/create-account").permitAll()
           .requestMatchers("/user/validate-user").permitAll()
           .requestMatchers("/login").permitAll()
           .anyRequest()
           .authenticated()
           .and()

           .exceptionHandling().accessDeniedHandler(ErrorHandler())
           .and()
           .csrf()
           .disable()

           .authenticationManager(authManager)

           .addFilterBefore(JwtAuthorizationFilter(jwtUtil),  UsernamePasswordAuthenticationFilter::class.java)
           .addFilterBefore(JWTAuthenticationFilter(authManager, jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)

           .sessionManagement()
           .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

           .and()
           .build()
    }


    @Bean
    @Autowired
    fun enconder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Autowired
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )


        authenticationManagerBuilder.userDetailsService(userServices).passwordEncoder(enconder())

        return authenticationManagerBuilder.build()
    }

}