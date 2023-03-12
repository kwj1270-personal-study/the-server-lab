package com.lab.sharding.presentation.board

import com.lab.sharding.presentation.board.dto.BoardReadResponse
import com.lab.sharding.presentation.board.dto.BoardUpdateRequest
import com.lab.sharding.presentation.board.dto.BoardWriteRequest
import com.lab.sharding.presentation.board.facade.BoardFacadeService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/boards")
@RestController
class BoardApi(
    private val boardFacadeService: BoardFacadeService
) {
    @GetMapping("/{id}")
    fun readBoard(@PathVariable id: Long): BoardReadResponse =
        BoardReadResponse(boardFacadeService.readBoard(id))

    @PostMapping
    fun writeUser(@RequestBody boardWriteRequest: BoardWriteRequest): URI =
        URI.create(
            "/boards/${
                boardFacadeService.writeBoard(
                    boardWriteRequest.account,
                    boardWriteRequest.password,
                    boardWriteRequest.title,
                    boardWriteRequest.content
                )
            }"
        )

    @PutMapping("/{id}")
    fun updateBoard(
        @PathVariable id: Long,
        @RequestBody boardUpdateRequest: BoardUpdateRequest
    ) = boardFacadeService.updateBoard(id, boardUpdateRequest.title, boardUpdateRequest.content)

    @DeleteMapping("/{id}")
    fun deleteBoard(@PathVariable id: Long) =
        boardFacadeService.deleteBoard(id)
}
