package com.mobi7.trackingsystem.domain.service.impl

import com.mobi7.trackingsystem.domain.entity.Position
import com.mobi7.trackingsystem.domain.repository.PositionRepository
import com.mobi7.trackingsystem.domain.service.PositionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PositionServiceImpl(
    @Autowired private val positionRepository: PositionRepository
) : PositionService {

    override fun create(position: Position): Position {
        return positionRepository.save(position)
    }
}