package com.pietrzak.mystation.persistence

import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository

@Repository
interface StationRepository: CrudRepository<Station, Long>