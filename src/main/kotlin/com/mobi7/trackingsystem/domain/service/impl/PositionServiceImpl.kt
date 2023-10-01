package com.mobi7.trackingsystem.domain.service.impl

import com.mobi7.trackingsystem.domain.entity.Location
import com.mobi7.trackingsystem.domain.entity.Position
import com.mobi7.trackingsystem.domain.repository.PositionRepository
import com.mobi7.trackingsystem.domain.repository.SpotOfInterestRepository
import com.mobi7.trackingsystem.domain.service.PositionService
import com.mobi7.trackingsystem.domain.vo.PlateStopCountsBySpot
import com.mobi7.trackingsystem.domain.vo.PlateStops
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.Distance
import org.springframework.data.geo.Metrics
import org.springframework.data.geo.Point
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PositionServiceImpl(
    @Autowired private val positionRepository: PositionRepository,
    @Autowired private val spotOfInterestRepository: SpotOfInterestRepository
) : PositionService {

    override fun create(position: Position): Position {
        return positionRepository.save(position)
    }

    override fun searchStopsBySpot(plate: String?, date: LocalDate?): List<PlateStopCountsBySpot> {
        val spots = spotOfInterestRepository.findAll()
        return spots.map { spot ->

            val stopCountsByPlate = getPositions(spot.location, spot.getRadiusInKilometers())
                .filter { plate == null || it.plate == plate }
                .filter { date == null || it.positionDate.toLocalDate() == date }
                /**
                 * Esse filtro deveria ficar diretamente na consulta para otimização de performance, para .
                 * Por estar nos primeiros passo com mongo, ficou como ponto de melhoria.
                 */
                .groupingBy { it.plate }
                .eachCount()
                .map { (plate, count) -> PlateStops(plate, count) }
            PlateStopCountsBySpot(spot, stopCountsByPlate)
        }
    }

    private fun getPositions(location: Location, radiusInKilometers: Double): List<Position>{
        return positionRepository.findAllByLocationNear(
            Point(location.latitude, location.longitude), Distance(radiusInKilometers, Metrics.KILOMETERS))
    }
}