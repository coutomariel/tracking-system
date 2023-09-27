package com.mobi7.trackingsystem.api.controller.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.mobi7.trackingsystem.api.exception.advice.ErrorType
import com.mobi7.trackingsystem.config.MongoInitializer
import com.mobi7.trackingsystem.helper.buildRegisterPositionRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = [MongoInitializer::class])
@AutoConfigureMockMvc
class PositionControllerImplTest{

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    companion object {
        const val URI_REGISTER_POSITION = "/positions"
    }

    @Test
    fun `should create register position`() {
        val request = buildRegisterPositionRequest(plate = "BDD9999")

        mockMvc.perform(post(URI_REGISTER_POSITION)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated)

    }

    @Test
    fun `should return bad request when plate is empty`() {
        val emptyPlate = ""
        val request = buildRegisterPositionRequest(plate = emptyPlate)

        val expectedTypeError = ErrorType.MB7_000
        val expectedFieldError = "plate"
        val expectedMessageFieldError = "must not be blank"

        mockMvc.perform(post(URI_REGISTER_POSITION)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.internalCode").value(expectedTypeError.code))
            .andExpect(jsonPath("$.errorFields.size()").value(1))
            .andExpect(jsonPath("$.errorFields.[0].field").value(expectedFieldError))
            .andExpect(jsonPath("$.errorFields.[0].message").value(expectedMessageFieldError))

    }

}