package com.mobi7.trackingsystem.domain.service

import com.mobi7.trackingsystem.domain.entity.Position

interface PositionService {
    fun create(position: Position): Position
}