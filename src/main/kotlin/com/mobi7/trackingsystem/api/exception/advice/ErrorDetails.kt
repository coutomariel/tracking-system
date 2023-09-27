package com.mobi7.trackingsystem.api.exception.advice

data class ErrorDetails(
    val httpCode: Int,
    val message: String,
    val internalCode: String,
    var errorFields: List<ErrorField>?
)