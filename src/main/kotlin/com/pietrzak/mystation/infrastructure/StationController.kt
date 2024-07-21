package com.pietrzak.mystation.infrastructure

import com.pietrzak.mystation.application.StationService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/station")
class StationController(val stationService: StationService) {

    @PostMapping("/addStation")
    fun addStation(
        @RequestBody
        newStation: StationRequestDTO
    ): StationResponseDTO {
        return stationService.addStation(newStation)
    }

    @GetMapping("/{stationId}")
    fun getStationById(
        @PathVariable
        stationId: Long
    ): StationResponseDTO {
        return stationService.getStation(stationId) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Station not found"
        )
    }

    @PutMapping("/updateStation/{stationId}")
    fun updateStation(
        @PathVariable
        stationId: Long,
        @RequestBody
        updateStation: StationRequestDTO
    ): StationResponseDTO {
        return stationService.updateStation(stationId, updateStation)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found")
    }

    @DeleteMapping("/deleteStation/{stationId}")
    fun deleteStationById(
        @PathVariable
        stationId: Long
    ) =
        stationService.deleteStationById(stationId)
}

data class StationRequestDTO(
    var stationName: String,
    var maxPower: Int
)

data class StationResponseDTO(
    var stationId: Long?,
    var stationName: String,
    var maxPower: Int
)