package com.lab.queryoffloading.domain.board.persistence.ro

import com.lab.queryoffloading.domain.board.persistence.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardReadOnlyRepository: JpaRepository<Board, Long> {
}