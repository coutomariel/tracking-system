package com.mobi7.trackingsystem.api.controller.impl

import com.mobi7.trackingsystem.api.controller.PositionController
import com.mobi7.trackingsystem.api.dto.RegisterPositionRequest
import com.mobi7.trackingsystem.api.mapper.toEntity
import com.mobi7.trackingsystem.domain.service.PositionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/positions")
class PositionControllerImpl(
    private val positionService: PositionService
) : PositionController {
    @PostMapping
    override fun register(@RequestBody @Valid request: RegisterPositionRequest): ResponseEntity<Any> {
        val position = positionService.create(request.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(position)
    }

}
