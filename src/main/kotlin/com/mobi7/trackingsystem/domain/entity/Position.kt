package com.mobi7.trackingsystem.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "positions")
data class Position(
    @Id
    val id: String? = null,
    val plate: String,
    val positionDate: LocalDateTime,
    val speed: Double,
    val ignite: Boolean,
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    val location: Location
)