package com.lab.queryoffloading.domain.board.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
}