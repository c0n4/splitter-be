package com.c0n4.group.respository.group

import com.c0n4.group.domain.Group
import com.c0n4.group.respository.entity.GroupsEntity
import jakarta.inject.Singleton
import java.util.*

@Singleton
class GroupRepositoryImpl(
    private val groupRepositoryJPA: GroupRepositoryJPA
) : GroupRepository {

    override fun saveGroup(group: Group) {
        groupRepositoryJPA.saveAndFlush(GroupsEntity(group))
    }

    override fun findById(id: String): Optional<Group> {
        return groupRepositoryJPA.findById(id).map { it.toGroup() }
    }

}