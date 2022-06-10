package com.c0n4.group.service

import com.c0n4.common.uuid.UUIDGenerator
import com.c0n4.group.domain.Group
import com.c0n4.user.domain.User
import jakarta.inject.Singleton

@Singleton
class GroupServiceImpl(private val uuidGenerator: UUIDGenerator) : GroupService {

    override fun getGroups(userID: String): List<Group> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getGroup(userID: String, idGroup: String): Group {
        TODO("Not yet implemented")
    }

    override fun createGroup(userID: String, group: Group): Group {
        val newGroup = Group.Builder()
            .id(uuidGenerator.getUUID())
            .description(group.description)
            .members(listOf(User.Builder().id(userID).build()))
            .build()


        return newGroup
    }

    override fun updateGroup(userID: String, group: Group): Group {
        TODO("Not yet implemented")
    }
}