package com.pietrzak.mystation.application

import com.pietrzak.mystation.persistence.StationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ServiceConfiguration {

    @Bean
    fun stationService(stationRepository: StationRepository): StationService =
        StationService(stationRepository)

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}