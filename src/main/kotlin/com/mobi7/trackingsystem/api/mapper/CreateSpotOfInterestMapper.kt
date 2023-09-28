package com.mobi7.trackingsystem.api.mapper

import com.mobi7.trackingsystem.api.dto.CreateSpotOfInterestRequest
import com.mobi7.trackingsystem.domain.entity.Location
import com.mobi7.trackingsystem.domain.entity.SpotOfInterest

fun CreateSpotOfInterestRequest.toEntity() = SpotOfInterest(
    name = this.name!!,
    radius = this.radius!!,
    location = Location(this.latitude!!, this.longitude!!)
)
