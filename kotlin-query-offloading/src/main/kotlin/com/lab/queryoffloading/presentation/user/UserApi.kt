package com.lab.queryoffloading.presentation.user

import com.lab.queryoffloading.domain.user.UserService
import com.lab.queryoffloading.presentation.user.dto.UserDeleteRequest
import com.lab.queryoffloading.presentation.user.dto.UserReadRequest
import com.lab.queryoffloading.presentation.user.dto.UserReadResponse
import com.lab.queryoffloading.presentation.user.dto.UserUpdateRequest
import com.lab.queryoffloading.presentation.user.dto.UserWriteRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/users")
@RestController
class UserApi(
    private val userService: UserService
) {

    @GetMapping
    fun readUser(@RequestBody userReadRequest: UserReadRequest): UserReadResponse =
        UserReadResponse(userService.readUser(userReadRequest.account, userReadRequest.password))

    @PostMapping
    fun writeUser(@RequestBody userWriteRequest: UserWriteRequest): URI =
        URI.create(
            "/users/${
                userService.writeUser(
                    userWriteRequest.account,
                    userWriteRequest.password,
                    userWriteRequest.nickname
                )
            }"
        )

    @PutMapping
    fun updateUser(@RequestBody userUpdateRequest: UserUpdateRequest) =
        userService.updateUser(userUpdateRequest.account, userUpdateRequest.password, userUpdateRequest.nickname)

    @DeleteMapping
    fun deleteUser(@RequestBody userDeleteRequest: UserDeleteRequest) =
        userService.deleteUser(userDeleteRequest.account, userDeleteRequest.password)
}
