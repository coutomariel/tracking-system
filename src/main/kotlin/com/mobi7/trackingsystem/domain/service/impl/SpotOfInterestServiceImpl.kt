package com.mobi7.trackingsystem.domain.service.impl

import com.mobi7.trackingsystem.domain.entity.SpotOfInterest
import com.mobi7.trackingsystem.domain.repository.SpotOfInterestRepository
import com.mobi7.trackingsystem.domain.service.SpotOfInterestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SpotOfInterestServiceImpl(
    @Autowired private val spotOfInterestRepository: SpotOfInterestRepository
) : SpotOfInterestService {

    override fun create(spotOfInterest: SpotOfInterest): SpotOfInterest {
        return spotOfInterestRepository.save(spotOfInterest)
    }
}