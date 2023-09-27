package com.mobi7.trackingsystem.domain.service.impl

import com.mobi7.trackingsystem.domain.repository.PositionRepository
import com.mobi7.trackingsystem.helper.buildPosition
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*


@ExtendWith(MockKExtension::class)
class PositionServiceImplTest {


    @MockK
    private lateinit var positionRepository: PositionRepository

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
}