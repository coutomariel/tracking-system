package com.mobi7.trackingsystem.domain.service

import com.mobi7.trackingsystem.domain.entity.Position
import com.mobi7.trackingsystem.domain.vo.PlateStopCountsBySpot
import java.time.LocalDate

interface PositionService {
    fun create(position: Position): Position
    fun searchStopsBySpot(name: String?, date: LocalDate?): List<PlateStopCountsBySpot>
}