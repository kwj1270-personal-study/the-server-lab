package com.lab.sharding.presentation.board.dto

import com.lab.sharding.domain.board.persistence.Board

data class BoardReadResponse(
    var title: String = "",
    var content: String = "",
    var writer: Long = 0L,
) {
    constructor(board: Board) : this(board.title, board.content, board.writer)
}
