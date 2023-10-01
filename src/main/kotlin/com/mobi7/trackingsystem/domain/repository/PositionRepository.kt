package com.mobi7.trackingsystem.domain.repository

import com.mobi7.trackingsystem.domain.entity.Position
import org.springframework.data.geo.Distance
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.repository.MongoRepository

interface PositionRepository : MongoRepository<Position, String> {
    fun findAllByLocationNear(point: Point, distance: Distance): List<Position>
}