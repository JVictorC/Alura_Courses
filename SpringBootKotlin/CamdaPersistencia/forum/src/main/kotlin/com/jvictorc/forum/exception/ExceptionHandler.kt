package com.jvictorc.forum.exception

import com.jvictorc.forum.dto.ErrorView
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Validation
import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(NotFoundExeption::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(
        exeption: NotFoundExeption,
        request: HttpServletRequest,
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exeption.message,
            path = request.servletPath,
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
        exeption: Exception,
        request: HttpServletRequest,
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exeption.message,
            path = request.servletPath,
        )
    }


    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
        exeption: MethodArgumentNotValidException,
        request: HttpServletRequest,
    ): ErrorView {
        val errorMessage = HashMap<String, String?>()

        exeption.bindingResult.fieldErrors.forEach {
            errorMessage[it.field] = it.defaultMessage
        }


        return ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMessage.toString(),
            path = request.servletPath,
        )
    }


}