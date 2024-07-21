package com.pietrzak.mystation.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository

@Repository
interface StationRepository: JpaRepository<Station, Long>