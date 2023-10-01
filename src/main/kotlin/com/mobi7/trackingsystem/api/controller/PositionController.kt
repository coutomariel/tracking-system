package com.mobi7.trackingsystem.api.controller

import com.mobi7.trackingsystem.api.dto.RegisterPositionRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import java.time.LocalDate

@Tag(
    name = "/positions",
    description = "Grupo de apis para tracking de veiculos, possibilitando registro e consultas de posicinamentos."
)
interface PositionController {
    @Operation(description = "API para registrar um posicionamento de veículo")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Retorno Ok com posicionamento registrado."),
        ApiResponse(responseCode = "400", description = "Requisição com parâmentros inválidos"),
        ApiResponse(responseCode = "500", description = "Server Error")
    ])
    @RequestBody(
        description = "Details of the position regristry to be created",
        required = true,
        content = [
            Content(
                mediaType = "application/json",
                schema = Schema(implementation = RegisterPositionRequest::class)
            )
        ]
    )
    fun register(request: RegisterPositionRequest): ResponseEntity<Any>


    @Operation(description = "API para registrar um posicionamento de veículo")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Retorno Ok com posicionamento registrado."),
            ApiResponse(responseCode = "500", description = "Server Error")
        ]
    )
    @Parameters
    fun search(
        @Parameter(name = "plate", required = false, example = "XYZ1212") plate: String?,
        @Parameter(name = "date", required = false, example = "24/12/2022") date: LocalDate?,
    ): ResponseEntity<Any>
}