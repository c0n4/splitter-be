package com.c0n4.group.controller.v1.dto

import com.c0n4.user.domain.User

data class UserDTO(val id: String?, val name: String?, val email: String?, val password: String?) {
    constructor(user: User) : this(user.id, user.name, user.email, null)

    fun toUser() = User.Builder()
        .id(id)
        .name(name)
        .email(email)
        .password(password)
        .build()
}