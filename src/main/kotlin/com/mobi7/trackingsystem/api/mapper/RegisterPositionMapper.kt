package com.mobi7.trackingsystem.api.mapper

import com.mobi7.trackingsystem.api.dto.RegisterPositionRequest
import com.mobi7.trackingsystem.domain.entity.Location
import com.mobi7.trackingsystem.domain.entity.Position

fun RegisterPositionRequest.toEntity(): Position = Position(
    plate = this.plate,
    positionDate = this.positionDate,
    speed = this.speed,
    ignite = this.ignite,
    location = Location(this.latitude, this.latitude)
)