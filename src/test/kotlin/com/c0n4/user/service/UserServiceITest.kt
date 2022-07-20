package com.c0n4.user.service

import com.c0n4.common.crypto.Crypto
import com.c0n4.group.domain.Group
import com.c0n4.group.service.group.GroupService
import com.c0n4.user.domain.User
import com.c0n4.user.service.UserService
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions

@MicronautTest
class UserServiceITest {

    @Inject
    lateinit var crypto: Crypto

    @Inject
    lateinit var userService: UserService

    @Test
    fun createUser() {
        val user = userService.createUser(
            User.Builder()
                .name("name_1")
                .username("username_1")
                .password("pass_1")
                .build()
        )
        val actualUser = userService.getUser(user.id)
        Assertions.assertEquals("username_1", actualUser.username)
        Assertions.assertEquals("name_1", actualUser.name)
        Assertions.assertEquals(crypto.hash("pass_1"), actualUser.password)
    }




}