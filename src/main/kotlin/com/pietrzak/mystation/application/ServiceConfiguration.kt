package com.pietrzak.mystation.application

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfiguration {

    @Bean
    fun stationService(): StationService =
        StationService()
}