package com.mobi7.trackingsystem.api.controller.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.mobi7.trackingsystem.api.exception.advice.ErrorType
import com.mobi7.trackingsystem.config.MongoInitializer
import com.mobi7.trackingsystem.helper.buildCreateSpotRequest
import com.mobi7.trackingsystem.helper.buildRegisterPositionRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = [MongoInitializer::class])
@AutoConfigureMockMvc
class SpotOfInterestsControllerControllerImplTest {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    companion object {
        const val URI_CREATE_SPOT_OF_INTEREST = "/spots"
    }

    @Test
    fun `should create register position`() {
        val request = buildCreateSpotRequest(name = "Arena do Grêmio")

        mockMvc.perform(
            MockMvcRequestBuilders.post(URI_CREATE_SPOT_OF_INTEREST)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isCreated)

    }


    @Test
    fun `should return bad request when name is empty`() {
        val request = buildCreateSpotRequest(name = "")

        val expectedTypeError = ErrorType.MB7_000
        val expectedFieldError = "name"
        val expectedMessageFieldError = "must not be blank"

        mockMvc.perform(
            MockMvcRequestBuilders.post(URI_CREATE_SPOT_OF_INTEREST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.internalCode").value(expectedTypeError.code))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errorFields.size()").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errorFields.[0].field").value(expectedFieldError))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errorFields.[0].message").value(expectedMessageFieldError))

    }
}