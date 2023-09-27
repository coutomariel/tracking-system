package com.mobi7.trackingsystem.api.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Schema(description = "Modelo criação de um register postition.")
data class RegisterPositionRequest(
    @field:Schema(description = "Placa do veículo.", example = "POS1234")
    @field:NotBlank val plate: String?,
    @field:Schema(description = "Data e horário do registro.")
    val positionDate: LocalDateTime? = LocalDateTime.now(),
    @field:Schema(description = "Velocidade do veículo no momento do registro.", example = "50.0")
    @field:NotNull val speed: Double?,
    @field:Schema(description = "Representação para ignição ligada ou desligada no momento do registro.", example = "false")
    @field:NotNull val ignite: Boolean?,
    @field:Schema(description = "Latitude capturada no momento do registro.", example = "-29.974182097913253")
    @field:NotNull val latitude: Double?,
    @field:Schema(description = "Longitude capturada no momento do registro.", example = "-51.19520764717468")
    @field:NotNull val longitude: Double?
)