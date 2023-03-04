package com.lab.graphql.domain

import org.springframework.data.jpa.repository.JpaRepository

interface FoodRepository : JpaRepository<Food, Long> {

    fun findByName(name: String): Food?
}