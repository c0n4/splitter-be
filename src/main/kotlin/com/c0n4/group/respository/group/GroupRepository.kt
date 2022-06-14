package com.c0n4.group.respository.group

import com.c0n4.group.domain.Group
import java.util.*


interface GroupRepository {

    fun saveGroup(group: Group)

    fun findById(id: String): Optional<Group>

    companion object {
        const val GROUP_ENTITY_NAME = "Group"
    }
}