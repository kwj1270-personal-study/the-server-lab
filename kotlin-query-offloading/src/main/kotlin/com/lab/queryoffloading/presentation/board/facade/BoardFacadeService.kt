package com.lab.queryoffloading.presentation.board.facade

import com.lab.queryoffloading.domain.board.BoardService
import com.lab.queryoffloading.domain.board.persistence.Board
import com.lab.queryoffloading.domain.user.UserService
import com.lab.queryoffloading.presentation.board.dto.BoardReadResponse
import org.springframework.stereotype.Service

@Service
class BoardFacadeService(
    private val boardService: BoardService,
    private val userService: UserService
) {

    fun readBoard(id: Long): Board =
        boardService.readBoard(id)

    fun readAllBoard(id: Long): List<Board> =
        boardService.readAllBoard()

    fun writeBoard(account: String, password: String, title: String, content: String): Long {
        val user = userService.readUser(account, password)
        return boardService.writeBoard(title, content, user.id!!)
    }

    fun updateBoard(id: Long, title: String, content: String) =
        boardService.updateBoard(id, title, content)


    fun deleteBoard(id: Long) =
        boardService.deleteBoard(id)
}
