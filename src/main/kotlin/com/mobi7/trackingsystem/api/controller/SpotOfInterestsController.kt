package com.mobi7.trackingsystem.api.controller

import com.mobi7.trackingsystem.api.dto.CreateSpotOfInterestRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(
    name = "/spots",
    description = "Grupo de apis para cadastro de pontos de interesse."
)
interface SpotOfInterestsController {
    @Operation(description = "API para criar um novo ponto de interesse")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Retorno Ok com ponto de interesse criado."),
        ApiResponse(responseCode = "400", description = "Requisição com parâmentros inválidos"),
        ApiResponse(responseCode = "500", description = "Server Error")
    ])
    @RequestBody(
        description = "Modelo de objeto para criação de um novo ponto de interesse.",
        required = true,
        content = [
            Content(
                mediaType = "application/json",
                schema = Schema(implementation = CreateSpotOfInterestRequest::class)
            )
        ]
    )
    fun create(request: CreateSpotOfInterestRequest) : ResponseEntity<Any>
}