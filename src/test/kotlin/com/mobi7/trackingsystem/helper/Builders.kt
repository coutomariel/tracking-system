package com.mobi7.trackingsystem.helper

import com.mobi7.trackingsystem.api.dto.RegisterPositionRequest
import com.mobi7.trackingsystem.domain.entity.Location
import com.mobi7.trackingsystem.domain.entity.Position
import java.time.LocalDateTime

fun buildPosition(
    plate: String = "XTZ1414",
    positionDate: LocalDateTime = LocalDateTime.now(),
    speed: Double = 0.0,
    ignite: Boolean = false,
    latitude: Double = -29.974182097913253,
    longitude: Double = -51.19520764717468,
) = Position(
    plate = plate,
    positionDate = positionDate,
    speed = speed,
    ignite = ignite,
    location = Location(
        latitude = latitude,
        longitude = longitude
    )
)

fun buildRegisterPositionRequest(
    plate: String = "XTZ1414",
    positionDate: LocalDateTime = LocalDateTime.now(),
    speed: Double = 0.0,
    ignite: Boolean = false,
    latitude: Double = -29.974182097913253,
    longitude: Double = -51.19520764717468,
) = RegisterPositionRequest(
    plate = plate,
    positionDate = positionDate,
    speed = speed,
    ignite = ignite,
    latitude = latitude,
    longitude = longitude

)