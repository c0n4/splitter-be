package com.c0n4.group.service

import com.c0n4.common.crypto.Crypto
import com.c0n4.group.domain.Group
import com.c0n4.group.service.group.GroupService
import com.c0n4.user.domain.User
import com.c0n4.user.service.UserService
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions

@MicronautTest
class GroupServiceITest {

    @Inject
    lateinit var groupService: GroupService

    @Test
    fun createGroup() {
        groupService.createGroup("user_id", Group.Builder().description("Group description").build())
        val groups = groupService.getGroups("user_id")
        Assertions.assertEquals("Group description", groups[0].description)
    }




}