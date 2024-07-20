package com.pietrzak.mystation.infrastructure

import com.pietrzak.mystation.MyStationApplication
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate

@SpringBootTest(
    classes = [MyStationApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class StationControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    private var stationId: Long = 0
    private var stationName = "Charging Station 1"
    private var maxPower = 50
    private val newStation = StationRequestDTO("Charging Station 2", 20)

    @Test
    fun addStation() {
        val stationRequestDTO = StationRequestDTO(stationName, maxPower)
        val result =
            this.restTemplate.postForEntity("/station/addStation", stationRequestDTO, StationResponseDTO::class.java)
        stationId = result.body?.stationId!!
        assertTrue {
            result.body?.stationName.equals(stationName)
        }
        assertTrue {
            result.body?.maxPower == maxPower
        }
    }

    @Test
    fun getStation() {
        addStation()
        val result = this.restTemplate.getForEntity("/station/{stationId}", StationResponseDTO::class.java, stationId)
        assertTrue {
            result.body?.stationName.equals(stationName)
        }
    }

    @Test
    fun updateStation() {
        addStation()
        this.restTemplate.put("station/updateStation/{stationId}", newStation, stationId)
        val result = this.restTemplate.getForEntity("/station/{stationId}", StationResponseDTO::class.java, stationId)
        assertTrue {
            result.statusCode.is2xxSuccessful
        }
        assertTrue {
            result.body?.stationName.equals(newStation.stationName)
        }
    }

    @Test
    fun deleteStation() {
        addStation()
        this.restTemplate.delete("station/deleteStation/{stationId}", stationId)
        val result = this.restTemplate.getForEntity("/station/{stationId}", StationResponseDTO::class.java, stationId)
        assertTrue {
            result.statusCode == HttpStatus.NOT_FOUND
        }
    }
}