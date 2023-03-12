package com.lab.sharding.domain.board.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Board(

    var title: String = "",
    var content: String = "",
    var writer: Long = 0L,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {
    fun update(title: String, content: String): Board {
        this.title = title
        this.content = content
        return this
    }
}
