package com.c0n4.group.service

import com.c0n4.group.domain.Group

interface GroupService {

    fun getGroups(userID: String): List<Group>

    fun getGroup(userID: String, idGroup: String): Group

    fun createGroup(userID: String, group: Group): Group

    fun updateGroup(userID: String, group: Group): Group
}