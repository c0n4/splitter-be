package com.c0n4.group.controller.v1

import com.c0n4.group.controller.v1.dto.GroupBaseDTO
import com.c0n4.group.controller.v1.dto.GroupDTO
import com.c0n4.group.domain.Group
import com.c0n4.group.service.group.GroupService
import org.junit.Ignore
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.security.Principal

internal class GroupControllerTest {

    private val groupService = Mockito.mock(GroupService::class.java)

    private val groupController = GroupController(
        groupService
    )

    private val userId = "userId"

    private val principal = Principal { userId }


    private val group = Group.Builder()
        .id("id")
        .description("description")
        .members(emptyList())
        .expenses(emptyList())
        .build()

    private val groupUpdated = Group.Builder()
        .id("id")
        .description("description")
        .members(emptyList())
        .expenses(emptyList())
        .build()

    private val groupDTO = GroupDTO(
        group
    )

    private val groupBaseDTO = GroupBaseDTO(
        group.id,
        group.description
    )

    @Test
    fun getGroups() {
        Mockito.`when`(groupService.getGroups(userId)).thenReturn(listOf(group))
        val groupsActual = groupController.getGroups(principal)
        assertEquals(listOf(groupBaseDTO), groupsActual)
    }

    @Test
    fun getGroup() {
        Mockito.`when`(groupService.getGroup(userId, "")).thenReturn(group)
        val groupActual = groupController.getGroup(principal, "")
        assertEquals(groupDTO, groupActual)
    }

    @Test
    @Ignore
    fun createGroup() {
        Mockito.`when`(groupService.createGroup(userId, group)).thenReturn(groupUpdated)
        val groupActual = groupController.createGroup(principal, groupDTO)
        assertEquals(groupDTO, groupActual)
    }
}