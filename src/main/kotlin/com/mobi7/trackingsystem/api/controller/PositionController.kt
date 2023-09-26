package com.mobi7.trackingsystem.api.controller

import com.mobi7.trackingsystem.api.dto.RegisterPositionRequest
import org.springframework.http.ResponseEntity

interface PositionController {
    fun register(request: RegisterPositionRequest) : ResponseEntity<Any>
}