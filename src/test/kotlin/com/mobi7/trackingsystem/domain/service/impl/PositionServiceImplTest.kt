package com.mobi7.trackingsystem.domain.service.impl

import com.mobi7.trackingsystem.domain.repository.PositionRepository
import com.mobi7.trackingsystem.domain.repository.SpotOfInterestRepository
import com.mobi7.trackingsystem.helper.buildPosition
import com.mobi7.trackingsystem.helper.buildSpot
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*


@ExtendWith(MockKExtension::class)
class PositionServiceImplTest {


    @MockK
    private lateinit var positionRepository: PositionRepository

    @MockK
    private lateinit var spotOfInterestRepository: SpotOfInterestRepository

    @InjectMockKs
    private lateinit var positionService: PositionServiceImpl

    @Test
    fun `should create register position`() {
        val mockPosition = buildPosition()
        val mockSavedPosition = mockPosition.copy(id = UUID.randomUUID().toString())

        every { positionRepository.save(any()) } returns mockSavedPosition
        positionService.create(mockPosition)

        verify(exactly = 1) { positionRepository.save(any()) }

    }

    @Test
    fun `should be search stops by spot`() {

        // mock spots of interest
        val arena = buildSpot(
            name = "Arena do Grêmio", latitude = -29.97396367487896, longitude = -51.19479856772378, radius = 200.0
        )
        val beiraRio = buildSpot(
            name = "BeiraRio", latitude = -30.065444408052482, longitude = -51.235788865861835, radius = 500.0
        )
        every { spotOfInterestRepository.findAll() } returns listOf(arena, beiraRio)


        //mock stops
        val outsideArena = buildPosition(latitude = -29.68709362151769, longitude = -51.48478032181815)
        val nearBeiraRio = buildPosition(latitude = -30.066239147908146, longitude = -51.23457838431209)

        val stops = listOf(
            buildPosition(plate = "SUA9999", latitude = -29.972268191179392, longitude = -51.19371327064352),
            buildPosition(plate = "GER3333", latitude = -29.972268191179392, longitude = -51.19371327064352),
            buildPosition(plate = "GER3333", latitude = -29.972268191179392, longitude = -51.19371327064352),
            outsideArena,
            nearBeiraRio
        )
        every { positionRepository.findAllByLocationNear(any(), any())} returns stops

        val result = positionService.searchStopsBySpot(null, null)

        // create assertions
        Assertions.assertEquals(result.size, 2)

        Assertions.assertEquals("Arena do Grêmio", result.first().spot.name)
        Assertions.assertEquals(3, result.first().plateStops.size)
        Assertions.assertEquals("BeiraRio", result.last().spot.name)
    }

}