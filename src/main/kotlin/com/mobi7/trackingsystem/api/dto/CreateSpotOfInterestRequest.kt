package com.mobi7.trackingsystem.api.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Schema(description = "Modelo criação de um ponto de interesse.")
data class CreateSpotOfInterestRequest(
    @field:Schema(description = "Nome do ponto de interesse.", example = "Fabrica de rações xyz")
    @field:NotBlank val name: String?,
    @field:Schema(description = "Raio em [metros] a partir das cordenadas.", example = "150.0")
    @field:NotNull val radius: Double?,
    @field:Schema(description = "Latitude capturada no momento do registro.", example = "-29.974182097913253")
    @field:NotNull val latitude: Double?,
    @field:Schema(description = "Longitude capturada no momento do registro.", example = "-51.19520764717468")
    @field:NotNull val longitude: Double?
)