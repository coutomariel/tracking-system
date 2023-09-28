package com.mobi7.trackingsystem.domain.repository

import com.mobi7.trackingsystem.domain.entity.SpotOfInterest
import org.springframework.data.mongodb.repository.MongoRepository

interface SpotOfInterestRepository: MongoRepository<SpotOfInterest, String>