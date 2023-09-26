package com.mobi7.trackingsystem.api.controller.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.mobi7.trackingsystem.config.MongoInitializer
import com.mobi7.trackingsystem.helper.buildPosition
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
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

    @Test
    fun `should create register position`() {
        val request = buildPosition(plate = "BDD9999")

        mockMvc.perform(post("/positions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated)

    }

}