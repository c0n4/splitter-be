package com.c0n4.user.controller.v1

import com.c0n4.group.controller.v1.dto.UserDTO
import com.c0n4.user.service.UserService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/v1/user")
class UserController(private val userService: UserService) {

    @Post()
    fun createUser(@Body user: UserDTO): UserDTO {
        return UserDTO(userService.createUser(user.toUser()))
    }
}