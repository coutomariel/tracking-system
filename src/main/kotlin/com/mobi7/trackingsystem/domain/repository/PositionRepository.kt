package com.mobi7.trackingsystem.domain.repository

import com.mobi7.trackingsystem.domain.entity.Position
import org.springframework.data.mongodb.repository.MongoRepository

interface PositionRepository : MongoRepository<Position, String> {
}