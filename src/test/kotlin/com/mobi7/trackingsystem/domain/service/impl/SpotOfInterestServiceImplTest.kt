package com.mobi7.trackingsystem.domain.service.impl

import com.mobi7.trackingsystem.domain.repository.SpotOfInterestRepository
import com.mobi7.trackingsystem.helper.buildSpot
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class SpotOfInterestServiceImplTest {

    @MockK
    private lateinit var spotOfInterestRepository: SpotOfInterestRepository

    @InjectMockKs
    private lateinit var spotOfInterestService: SpotOfInterestServiceImpl

    @Test
    fun `should create spot of interest`() {
        val mockSpot = buildSpot()
        val mockSavedSpot = mockSpot.copy(id = UUID.randomUUID().toString())

        every { spotOfInterestRepository.save(any()) } returns mockSavedSpot
        spotOfInterestService.create(mockSpot)

        verify(exactly = 1) { spotOfInterestRepository.save(any()) }
    }

}