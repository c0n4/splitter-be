package com.c0n4.user.repository

import com.c0n4.user.domain.User

interface UserRepository {

    fun createUser(user: User)

    fun getUser(id: String): User
}