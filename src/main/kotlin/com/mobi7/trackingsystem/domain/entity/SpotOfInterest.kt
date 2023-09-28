package com.mobi7.trackingsystem.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "spots")
data class SpotOfInterest (
    @Id
    val id: String? = null,
    val name: String,
    val radius: Double,
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    val location: Location
)