package com.lab.sharding.domain.board

import com.lab.sharding.config.shard.aspect.ShardKey
import com.lab.sharding.config.shard.ShardingTarget
import com.lab.sharding.domain.board.persistence.Board
import com.lab.sharding.domain.board.persistence.BoardRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@Transactional(readOnly = false)
@Service
class BoardService(
    private val boardRepository: BoardRepository
) {

    @Transactional(readOnly = true)
    fun readBoard(id: Long = 0): Board {
        val orElseThrow = boardRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException("board not found") }

        println(TransactionSynchronizationManager.isCurrentTransactionReadOnly())
        return orElseThrow
    }

    @Transactional(readOnly = true)
    fun readAllBoard(): List<Board> =
        boardRepository.findAll()

    @ShardKey(
        target = ShardingTarget.BUSINESS,
        key = "#writer"
    )
    fun writeBoard(
        title: String = "",
        content: String = "",
        writer: Long = 0L
    ): Long =
        boardRepository.saveAndFlush(
            Board(title = title, content = content, writer = writer)
        ).id!!

    fun updateBoard(
        id: Long = 0L,
        title: String = "",
        content: String = ""
    ) = boardRepository.saveAndFlush(
        boardRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException("board not found") }
            .update(title = title, content = content)
    )

    fun deleteBoard(id: Long = 0L) =
        boardRepository.deleteById(id)
}
