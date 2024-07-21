package com.pietrzak.mystation.application

import com.pietrzak.mystation.infrastructure.StationRequestDTO
import com.pietrzak.mystation.infrastructure.StationResponseDTO
import com.pietrzak.mystation.persistence.Station
import com.pietrzak.mystation.persistence.StationRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class StationService(var stationRepository: StationRepository) {

    fun addStation(newStation: StationRequestDTO): StationResponseDTO {
        val saveStation = stationRepository.save(
            Station(
                stationId = null,
                stationName = newStation.stationName,
                maxPower = newStation.maxPower
            )
        )
        return mapToStationResponseDTO(
            Station(
                stationId = saveStation.stationId!!,
                stationName = saveStation.stationName,
                maxPower = saveStation.maxPower
            )
        )
    }

    fun getStation(stationId: Long): StationResponseDTO? {
        return stationRepository.findById(stationId)
            .map { Station(stationId = it.stationId!!, stationName = it.stationName, maxPower = it.maxPower) }
            .getOrNull()
            ?.let { mapToStationResponseDTO(it) }
    }

    fun updateStation(stationId: Long, updateStation: StationRequestDTO): StationResponseDTO? {
        return mapToStationResponseDTO(stationRepository.findById(stationId).map {
            val save = stationRepository.save(
                Station(
                    stationId = it.stationId,
                    stationName = it.stationName,
                    maxPower = it.maxPower
                )
            )
            Station(
                stationId = save.stationId,
                stationName = save.stationName,
                maxPower = save.maxPower
            )
        }.orElseGet(null))
    }

    fun deleteStationById(stationId: Long){
        stationRepository.deleteById(stationId)
    }

    private fun mapToStationResponseDTO(station: Station): StationResponseDTO =
        StationResponseDTO(
            stationId = station.stationId,
            stationName = station.stationName,
            maxPower = station.maxPower
        )
}