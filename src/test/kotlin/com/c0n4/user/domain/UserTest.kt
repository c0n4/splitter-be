package com.c0n4.user.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UserTest {

    @Test
    fun `should create user`() {
        val user = User.Builder().build()
        assertNotNull(user)
    }
}