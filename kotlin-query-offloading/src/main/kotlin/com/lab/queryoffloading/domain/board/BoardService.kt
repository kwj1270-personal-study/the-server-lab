package com.lab.queryoffloading.domain.board

import com.lab.queryoffloading.config.datasource.readwrite.BoardReadOnlyDataSourceConfiguration
import com.lab.queryoffloading.config.datasource.readwrite.BoardReadWriteDataSourceConfiguration
import com.lab.queryoffloading.domain.board.persistence.Board
import com.lab.queryoffloading.domain.board.persistence.ro.BoardReadOnlyRepository
import com.lab.queryoffloading.domain.board.persistence.rw.BoardReadWriteRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = false, transactionManager = BoardReadWriteDataSourceConfiguration.TRANSACTION_MANAGER)
@Service
class BoardService(
    private val boardReadOnlyRepository: BoardReadOnlyRepository,
    private val boardReadWriteRepository: BoardReadWriteRepository
) {

    @Transactional(readOnly = true, transactionManager = BoardReadOnlyDataSourceConfiguration.TRANSACTION_MANAGER)
    fun readBoard(id: Long = 0): Board {
        return boardReadOnlyRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException("board not found") }
    }


    @Transactional(readOnly = true)
    fun readAllBoard(): List<Board> =
        boardReadOnlyRepository.findAll()

    fun writeBoard(
        title: String = "",
        content: String = "",
        writer: Long = 0L
    ): Long =
        boardReadWriteRepository.saveAndFlush(
            Board(title = title, content = content, writer = writer)
        ).id!!

    fun updateBoard(
        id: Long = 0L,
        title: String = "",
        content: String = ""
    ) = boardReadWriteRepository.saveAndFlush(
        boardReadOnlyRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException("board not found") }
            .update(title = title, content = content)
    )

    fun deleteBoard(id: Long = 0L) =
        boardReadWriteRepository.deleteById(id)
}
