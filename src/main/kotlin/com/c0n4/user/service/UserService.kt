package com.c0n4.user.service

import com.c0n4.user.domain.User

interface UserService {

    fun createUser(user: User): User

    fun getUser(id: String): User

    fun getUserByUsername(username: String): User

    fun validateUser(username: String, password: String): User?
}