package com.mobi7.trackingsystem.api.controller.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.mobi7.trackingsystem.api.exception.advice.ErrorType
import com.mobi7.trackingsystem.config.MongoInitializer
import com.mobi7.trackingsystem.domain.repository.PositionRepository
import com.mobi7.trackingsystem.domain.repository.SpotOfInterestRepository
import com.mobi7.trackingsystem.helper.buildPosition
import com.mobi7.trackingsystem.helper.buildRegisterPositionRequest
import com.mobi7.trackingsystem.helper.buildSpot
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate


@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = [MongoInitializer::class])
@AutoConfigureMockMvc
class PositionControllerImplTest {

    @Autowired
    private lateinit var spotOfInterestRepository: SpotOfInterestRepository

    @Autowired
    private lateinit var positionRepository: PositionRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    companion object {
        const val URI_REGISTER_POSITION = "/positions"
        const val URI_SEARCH_STOPS = "/positions"
    }

    @BeforeEach
    fun setUp() {
        positionRepository.deleteAll()
        spotOfInterestRepository.deleteAll()
    }

    @Test
    fun `should create register position`() {
        val request = buildRegisterPositionRequest(plate = "BDD9999")

        mockMvc.perform(
            post(URI_REGISTER_POSITION)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)

    }

    @Test
    fun `should return bad request when plate is empty`() {
        val emptyPlate = ""
        val request = buildRegisterPositionRequest(plate = emptyPlate)

        val expectedTypeError = ErrorType.MB7_000
        val expectedFieldError = "plate"
        val expectedMessageFieldError = "must not be blank"

        mockMvc.perform(
            post(URI_REGISTER_POSITION)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.internalCode").value(expectedTypeError.code))
            .andExpect(jsonPath("$.errorFields.size()").value(1))
            .andExpect(jsonPath("$.errorFields.[0].field").value(expectedFieldError))
            .andExpect(jsonPath("$.errorFields.[0].message").value(expectedMessageFieldError))

    }

    @Test
    fun `should return stops list when position within spot radius`() {
        val stadiuns = listOf(
            buildSpot(
                name = "Arena do Grêmio", latitude = -29.97396367487896, longitude = -51.19479856772378, radius = 200.0
            ),
            buildSpot(
                name = "BeiraRio", latitude = -30.065444408052482, longitude = -51.235788865861835, radius = 500.0
            )
        )

        val outsideArena = buildPosition(
            plate = "GER3333", latitude = -29.68709362151769, longitude = -51.48478032181815
        )
        val nearBeiraRio = buildPosition(
            plate = "FIT1213", latitude = -30.066239147908146, longitude = -51.23457838431209
        )
        val positions = listOf(
            buildPosition(plate = "SUA9999", latitude = -29.972268191179392, longitude = -51.19371327064352),
            buildPosition(plate = "GER3333", latitude = -29.972268191179392, longitude = -51.19371327064352),
            buildPosition(plate = "GER3333", latitude = -29.972268191179392, longitude = -51.19371327064352),
            outsideArena,
            nearBeiraRio
        )

        spotOfInterestRepository.saveAll(stadiuns)
        positionRepository.saveAll(positions)

        Assertions.assertEquals(2, spotOfInterestRepository.count())
        Assertions.assertEquals(5, positionRepository.count())

        mockMvc.perform(get(URI_SEARCH_STOPS))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$.[0].plateStops.size()").value(2))
            .andExpect(jsonPath("$.[1].plateStops.size()").value(1))
    }

    @Test
    fun `should return stops filtered by plate`() {
        val filteredPlate = "SUA9999"

        val arenaDoGremio = buildSpot(
            name = "Arena do Grêmio", latitude = -29.97396367487896, longitude = -51.19479856772378, radius = 500.0
        )

        val positions = listOf(
            buildPosition(plate = filteredPlate, latitude = -29.972268191179392, longitude = -51.19371327064352),
            buildPosition(plate = "GER3333", latitude = -29.972268191179392, longitude = -51.19371327064352),
            buildPosition(plate = "GER3333", latitude = -29.972268191179392, longitude = -51.19371327064352),
            buildPosition(plate = "GER3333", latitude = -29.68709362151769, longitude = -51.48478032181815)
        )

        spotOfInterestRepository.save(arenaDoGremio)
        positionRepository.saveAll(positions)

        Assertions.assertEquals(1, spotOfInterestRepository.count())
        Assertions.assertEquals(4, positionRepository.count())

        mockMvc.perform(
            get(URI_SEARCH_STOPS)
                .param("plate", filteredPlate)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$.[0].plateStops.size()").value(1))
    }

    @Test
    fun `should return stops filtered by date`() {
        val arenaDoGremio = buildSpot(
            name = "Arena do Grêmio", latitude = -29.97396367487896, longitude = -51.19479856772378, radius = 200.0
        )

        val outOfDAte = buildPosition(latitude = -29.68709362151769, longitude = -51.48478032181815)
        val positions = listOf(
            buildPosition(plate = "SUA9999", latitude = -29.972268191179392, longitude = -51.19371327064352),
            buildPosition(plate = "GER3333", latitude = -29.972268191179392, longitude = -51.19371327064352),
            buildPosition(plate = "GER3333", latitude = -29.972268191179392, longitude = -51.19371327064352),
            buildPosition(plate = "GER3333", latitude = -29.68709362151769, longitude = -51.48478032181815),
            outOfDAte
        )

        spotOfInterestRepository.save(arenaDoGremio)
        positionRepository.saveAll(positions)

        Assertions.assertEquals(1, spotOfInterestRepository.count())
        Assertions.assertEquals(5, positionRepository.count())

        mockMvc.perform(
            get(URI_SEARCH_STOPS).param("date", LocalDate.now().toString()))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$.[0].plateStops.size()").value(2))
    }

}