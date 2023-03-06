package com.jvictorc.notas.exeption

import com.jvictorc.notas.dto.error.ErrorView
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExeptionHandler {
    @ExceptionHandler(NotFoundNota::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlerNotFoundNota(
        exeption: NotFoundNota,
        request: HttpServletRequest
    ): ErrorView {

        return ErrorView(
            message = exeption.message,
            error = HttpStatus.NOT_FOUND.name,
            status = HttpStatus.NOT_FOUND.value(),
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorView {
        val errorMessage = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach{
                e -> errorMessage.put(e.field, e.defaultMessage)
        }
        return ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMessage.toString(),
            path = request.servletPath
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadResquest(
        exeption: HttpMessageNotReadableException,
        request: HttpServletRequest
    ) : ErrorView {
        return ErrorView(
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exeption.message,
            path = request.servletPath,
            status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerErro(
        exeption: Exception,
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exeption.message,
            path = request.servletPath,
            status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        )
    }


}