package com.pietrzak.mystation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyStationApplication

fun main(args: Array<String>) {
    runApplication<MyStationApplication>(*args)
}
