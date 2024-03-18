package com.pietrzak.mystation.infrastructure

import com.pietrzak.mystation.application.StationService
import com.pietrzak.mystation.persistence.Station
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StationController (val service: StationService){

    @GetMapping("/")
    fun getStations(): ResponseEntity<List<Station>> {
        //service.findAll()
        return ResponseEntity(HttpStatus.OK)
    }
}