package com.mobi7.trackingsystem.api.dto

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class RegisterPositionRequest(
    @field:NotBlank val plate: String?,
    val positionDate: LocalDateTime? = LocalDateTime.now(),
    @field:NotNull val speed: Double?,
    @field:NotNull val ignite: Boolean?,
    @field:NotNull val latitude: Double?,
    @field:NotNull val longitude: Double?
)