package com.c0n4.user.controller.v1

import com.c0n4.user.controller.v1.dto.UserDTO
import com.c0n4.user.service.UserService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import java.security.Principal

@Controller("/v1/users")
class UserController(private val userService: UserService) {

    @Post()
    @Secured(SecurityRule.IS_ANONYMOUS)
    fun createUser(@Body user: UserDTO): UserDTO {
        return UserDTO(userService.createUser(user.toUser()))
    }

    @Get()
    @Secured(SecurityRule.IS_AUTHENTICATED)
    fun getUser(principal: Principal): UserDTO {
        return UserDTO(userService.getUser(principal.name))
    }
}