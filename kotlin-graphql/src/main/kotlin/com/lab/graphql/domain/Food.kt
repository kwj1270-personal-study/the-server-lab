package com.lab.graphql.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "foods")
@Entity
class Food(
    var name: String = "",

    @Id  @GeneratedValue
    var id: Long? = null
) {
}