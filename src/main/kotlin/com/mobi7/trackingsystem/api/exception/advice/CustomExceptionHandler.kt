package com.mobi7.trackingsystem.api.exception.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException,
        request: WebRequest,
    ): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(
            httpCode = HttpStatus.BAD_REQUEST.value(),
            message = ErrorType.MB7_000.message,
            internalCode = ErrorType.MB7_000.code,
            errorFields = exception.bindingResult.fieldErrors.map { error ->
                ErrorField(
                    message = error.defaultMessage ?: "Invalid",
                    field = error.field)
            }

        )
        return ResponseEntity.badRequest().body(errorDetails)
    }
}