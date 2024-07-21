package com.pietrzak.mystation.persistence

import jakarta.persistence.*

@Entity
@Table(name = "Station")
data class Station (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val stationId: Long?,
    val stationName: String,
    val maxPower: Int
){
}