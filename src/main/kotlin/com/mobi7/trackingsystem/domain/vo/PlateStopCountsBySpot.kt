package com.mobi7.trackingsystem.domain.vo

import com.mobi7.trackingsystem.domain.entity.SpotOfInterest

data class PlateStopCountsBySpot(val spot: SpotOfInterest, val plateStops: List<PlateStops>)
data class PlateStops(val plate: String, val count: Int)
