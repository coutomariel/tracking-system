package com.mobi7.trackingsystem.domain.entity

import com.mobi7.trackingsystem.domain.constants.Constants
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "spots")
data class SpotOfInterest (
    @field:Id
    val id: String? = null,
    val name: String,
    val radius: Double,
    @field:GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    val location: Location
) {
    fun getRadiusInKilometers() = radius.div(Constants.METERS_IN_KILOMETERS)
}