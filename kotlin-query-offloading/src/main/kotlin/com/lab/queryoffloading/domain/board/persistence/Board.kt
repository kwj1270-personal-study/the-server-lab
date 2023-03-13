package com.lab.queryoffloading.domain.board.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "boards")
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
