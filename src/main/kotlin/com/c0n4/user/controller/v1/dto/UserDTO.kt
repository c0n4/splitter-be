package com.c0n4.user.controller.v1.dto

import com.c0n4.user.domain.User

data class UserDTO(
    val id: String?,
    val name: String,
    val username: String,
    val password: String?
) {
    constructor(user: User) : this(user.id, user.name, user.username, null)

    fun toUser() = User.Builder()
        .id(id)
        .name(name)
        .username(username)
        .password(password)
        .build()
}