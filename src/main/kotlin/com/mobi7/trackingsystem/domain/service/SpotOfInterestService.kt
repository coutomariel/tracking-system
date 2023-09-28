package com.mobi7.trackingsystem.domain.service

import com.mobi7.trackingsystem.domain.entity.SpotOfInterest

interface SpotOfInterestService {
    fun create(spotOfInterest: SpotOfInterest): SpotOfInterest
}