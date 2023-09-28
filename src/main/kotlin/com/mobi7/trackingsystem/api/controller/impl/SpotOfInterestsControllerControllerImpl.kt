package com.mobi7.trackingsystem.api.controller.impl

import com.mobi7.trackingsystem.api.controller.SpotOfInterestsController
import com.mobi7.trackingsystem.api.dto.CreateSpotOfInterestRequest
import com.mobi7.trackingsystem.api.mapper.toEntity
import com.mobi7.trackingsystem.domain.service.SpotOfInterestService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/spots")
class SpotOfInterestsControllerControllerImpl(
    private val spotOfInterestService: SpotOfInterestService
) : SpotOfInterestsController {
    @PostMapping
    override fun create(@RequestBody @Valid request: CreateSpotOfInterestRequest): ResponseEntity<Any> {
        val position = spotOfInterestService.create(request.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(position)
    }

}